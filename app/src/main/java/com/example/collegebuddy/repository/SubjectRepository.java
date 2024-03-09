package com.example.collegebuddy.repository;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.collegebuddy.model.SubjectListModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectRepository {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private  final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final FirebaseUser user = auth.getCurrentUser();

    private final CollectionReference subjectCollectionRef = db.collection("subjects");
    private final CollectionReference userCollectionRef = db.collection("Users");

    private  MutableLiveData<List<SubjectListModel>> _subjectList = new MutableLiveData<>();


    public LiveData<List<SubjectListModel>> getSubjects(Context context) {
        List<SubjectListModel> subjectList = new ArrayList<>();

        if (user!=null) {
            String teacherId = user.getUid();
            db.collection("users").document(teacherId).get().addOnCompleteListener(
                    task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                // Extract subject IDs from the user document
                                List<String> subjectIds = (List<String>) document.get("Subjects");
                                assert subjectIds != null;
                                subjectCollectionRef
                                        .whereIn(FieldPath.documentId(), subjectIds)
                                        .get()
                                        .addOnCompleteListener(taski -> {
                                            if (taski.isSuccessful()) {
                                                QuerySnapshot querySnapshot = taski.getResult();
                                                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                                                        // Extract data from each document
                                                        // Add more fields as needed
                                                        SubjectListModel subject = doc.toObject(SubjectListModel.class);
                                                        subjectList.add(subject);
                                                        // ...

                                                        // Do something with the data
                                                        // Print more fields as needed
                                                        // ...
                                                    }
                                                } else {
                                                    // Handle the case when no documents match the query
                                                    System.out.println("No matching documents found.");
                                                }
                                            } else {
                                                // Handle errors while fetching documents
                                                Exception exception = task.getException();
                                                if (exception != null) {
                                                    System.out.println("Error fetching documents: " + exception.getMessage());
                                                }
                                            }
                                        });
                            }


                        }

                    });



        };
        _subjectList = new MutableLiveData<>(subjectList);
        return _subjectList;
    }






    public void addSubject( String subjectName, String password, String semBranch) {
        if (user!=null){
            String teacherId= user.getUid();
            String subjectId = teacherId + "_" + subjectName;
            Map<String ,Object> data = new HashMap<>();
            data.put("subjectName",subjectName);
            data.put("password",password);
            data.put("semBranch",semBranch);
            subjectCollectionRef
                    .document(subjectId)
                    .set(data)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            System.out.println("Document updated/created successfully.");
                        } else {
                            Exception exception = task.getException();
                            if (exception != null) {
                                System.out.println("Error updating/creating document: " + exception.getMessage());
                            }
                        }
                    });
            userCollectionRef.document(teacherId)
                    .update("Subjects", FieldValue.arrayUnion(subjectId))
                    .addOnCompleteListener(task -> {
                        // Handle the exception
                        Exception exception =task.getException();
                        System.out.println("Error updating user document: " + exception.getMessage());
                        if (!task.isSuccessful()) {
                            exception = task.getException();
                            if (exception != null) {
                            }
                        }
                    });

        }}



//        if (user!=nu){
//
//            subjectCollectionRef.document(subjectId).set(subject);
//
//            userCollectionRef.document(teacherId).update("Subjects", FieldValue.arrayUnion(subjectId))
//                   ll .addOnCompleteListener(task -> {
//                        if (!task.isSuccessful()) {
//                            Exception exception = task.getException();
//                            if (exception != null) {
//                                // Handle the exception
//                            }
//                        }
//                    });


//        }




    public void editSubject( String subjectName, String password, String semBranch) {
        if (user!=null){
            String teacherId= user.getUid();
            String subjectId = teacherId + "_" + subjectName;
            subjectCollectionRef.document(subjectId)
                    .update("subjectName", subjectName, "password", password, "branchSem", semBranch);
        }
    }

    public void deleteSubject(String subjectName) {
        if (user!=null){
            String teacherId= user.getUid();
            String subjectId = teacherId + "_" + subjectName;

            subjectCollectionRef.document(subjectId).delete();


        }
    }
}
