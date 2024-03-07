package com.example.collegebuddy.adapters;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegebuddy.R;
import com.example.collegebuddy.model.SubjectListModel;


import java.util.ArrayList;

public class subjectAdapter extends RecyclerView.Adapter <MyViewHolder>{
    private ArrayList<SubjectListModel> data;
    public subjectAdapter(ArrayList<SubjectListModel> data) {
        this.data = data;
    }
    public void updateData(ArrayList<SubjectListModel> newData){
        this.data= newData;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_subject_item,parent,false);
        MyViewHolder viewHolder= new MyViewHolder(view);
        viewHolder.tvSubjectName= view.findViewById(R.id.tvSubjectName);
        viewHolder.tvBranch=view.findViewById(R.id.tvBranch);
        viewHolder.imgOptions=view.findViewById(R.id.imgOptions);
        viewHolder.imgSingleSubjectImage=view.findViewById(R.id.imgSingleSubjectImage);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SubjectListModel subject = data.get(position);
        holder.tvSubjectName.setText(subject.getSubjectName());
        holder.tvBranch.setText(subject.getBranchSem());
        holder.imgSingleSubjectImage.setImageResource(subject.getImage());
        holder.imgOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.showPopUpMenu(view);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tvSubjectName,tvBranch;
    ImageButton imgOptions;
    ImageView imgSingleSubjectImage;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
        tvBranch=itemView.findViewById(R.id.tvBranch);
        imgSingleSubjectImage=itemView.findViewById(R.id.imgSingleSubjectImage);
        imgOptions=itemView.findViewById(R.id.imgOptions);
        imgOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpMenu(view);
            }
        });
    }
    public void showPopUpMenu(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
        popupMenu.inflate(R.menu.subjectoption);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId==R.id.editSubject){
                    //Handle the options of editing the subject name
                    return true;
                }
                if (itemId==R.id.deleteSubject){
                    //Handle the options of deleting that particular subject
                    return true;
                }
                if (itemId==R.id.downloadAttendance){
                    //Handle the options of downloading the attendance in the form of a excel sheet for that particular month
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();

    }
}

