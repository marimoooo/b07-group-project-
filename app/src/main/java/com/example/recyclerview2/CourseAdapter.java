package com.example.recyclerview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {

    private static class ViewHolder{
        TextView tvCode;
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
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(convertView);
        }
        else{
            holder = (ViewHolder) convertView.getTag();// cache the viewHolder object
        }

        holder.tvCode.setText(course.code);
        holder.tvName.setText(course.session);

        return convertView;
    }
}
