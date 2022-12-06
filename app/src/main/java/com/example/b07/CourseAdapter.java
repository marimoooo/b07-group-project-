package com.example.b07;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {
    private static class ViewHolder{
        TextView tvCode;
        TextView tvSession;
        TextView tvName;
    }
    private int resource;

    public CourseAdapter(Context context, int resource, List<Course> objects) {
        super(context, resource, objects);
        this.resource = resource;
        //context = ui; resource = id
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Course course = getItem(position);
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resource,parent,false);
            holder = new ViewHolder();
            holder.tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            holder.tvSession = (TextView) convertView.findViewById(R.id.tvSession);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(convertView);
        }
        else{
            holder = (ViewHolder) convertView.getTag();// cache the viewHolder object
        }

        holder.tvCode.setText(course.code);
        holder.tvSession.setText(course.session);
        holder.tvName.setText(course.name);

        return convertView;
    }
}