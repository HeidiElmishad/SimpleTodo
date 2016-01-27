package com.codepath.simpletodo.com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import com.codepath.simpletodo.R;
import java.util.List;

/**
 * Created by snapfish on 1/25/16.
 */
public class ItemsAdapter extends ArrayAdapter<TodoItem> {

    private ViewHolder mViewHolder;

    public ItemsAdapter(Context context, int resource, List<TodoItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TodoItem item = getItem(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.todo_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.mDate = (TextView) convertView.findViewById(R.id.date_text);
            mViewHolder.mTaskName = (CheckedTextView) convertView.findViewById(R.id.todo_text);
            mViewHolder.mPriority = (TextView) convertView.findViewById(R.id.priority_text);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        if(item.getmDueDate() != null) {
            mViewHolder.mDate.setText(item.getmDueDate());
        }
        mViewHolder.mTaskName.setText(item.getmTaskName());
        if(item.getmPriority() != null) {
            mViewHolder.mPriority.setText(item.getmPriority());
        }


        return convertView;
    }

    private class ViewHolder{
        private TextView mDate;
        private CheckedTextView mTaskName;
        private TextView mPriority;

    }
}
