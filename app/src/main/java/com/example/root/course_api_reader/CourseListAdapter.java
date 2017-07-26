package com.example.root.course_api_reader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 7/26/17.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListAdapterViewHolder> {

    private static final String TAG = CourseListAdapter.class.getSimpleName();

    private Course[] mCourse;

    public interface ContactListItemClickListener {
        void onListItemClick(int clickedItemIndex, Course course);
    }

    public class CourseListAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView photoImageView;
        TextView coursetitleTextView;
        TextView lastNameTextView;

        public CourseListAdapterViewHolder(View itemView) {
            super(itemView);

            photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
            coursetitleTextView = (TextView) itemView.findViewById(R.id.coursetitleTextView);
            lastNameTextView = (TextView) itemView.findViewById(R.id.lastNameTextView);

        }


    }

    @Override
    public CourseListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.course_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        CourseListAdapterViewHolder viewHolder = new CourseListAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (mCourse == null) {
            return 0;
        }
        return mCourse.length;
    }

    @Override
    public void onBindViewHolder(CourseListAdapterViewHolder courseListAdapterViewHolder,
                                 int position) {
        Log.d(TAG, "#" + position);

        Course course = mCourse[position];
        // contactListAdapterViewHolder.photoImageView = // TODO: Set photo for contact in list

        courseListAdapterViewHolder.coursetitleTextView.setText(course.name);
    }

    public void setCourses(Course[] course) {
        mCourse = course;
        notifyDataSetChanged();
    }
}

