package com.example.root.course_api_reader;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CourseListAdapter mCourseListAdapter;

    Course[] mCourses;

    private ProgressBar mLoadingIndicator;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mTextView = (TextView) findViewById(R.id.tv_json_display);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.loadingIndicatorProgressBar);

        mRecyclerView = (RecyclerView) findViewById(R.id.contactListRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true); // Tells RecyclerView that all list items have same size

        mCourseListAdapter = new CourseListAdapter();

        mRecyclerView.setAdapter(mCourseListAdapter);

        String query = "android";
        new FetchWeatherTask().execute(query);
    }

    public void onSearchClicked(View view) {
        String query = "arabic";
        new FetchWeatherTask().execute(query);
    }

    // COMPLETED (5) Create a class that extends AsyncTask to perform network requests
    public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

        // COMPLETED (6) Override the doInBackground method to perform your network requests
        @Override
        protected String[] doInBackground(String... params) {

            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String location = params[0];
            URL weatherRequestUrl = CourseraJsonUtils.buildUrl(location);

            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(weatherRequestUrl);


                // return simpleJsonWeatherData;
                return new String[]{jsonWeatherResponse};
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // COMPLETED (7) Override the onPostExecute method to display the results of the network request
        @Override
        protected void onPostExecute(String[] queryData) {
            if (queryData != null) {
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
                Course[] courses = null;
                try {
                    courses = CourseraJsonUtils.parsejsonstring(queryData[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (courses != null) {
                    mCourseListAdapter.setCourses(courses);
                }

            }
        }
    }
}
























