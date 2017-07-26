package com.example.root.course_api_reader;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by root on 7/26/17.
 */

public final class CourseraJsonUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://api.coursera.org/api/courses.v1";
    private static final String COURSE_BASE_URL= "https://www.coursera.org/learn/";


    /*
     * NOTE: These values only effect responses from OpenWeatherMap, NOT from the fake weather
     * server. They are simply here to allow us to teach you how to build a URL if you were to use
     * a real API.If you want to connect your app to OpenWeatherMap's API, feel free to! However,
     * we are not going to show you how to do so in this course.
     */

    /* The format we want our API to return */
    private static final String format = "json";
    /* The units we want our API to return */
    private static final String units = "metric";
    /* The number of days we want our API to return */
    private static final int numDays = 14;

    final static String QUERY_PARAM = "q";
    final static String QUERY_STRING_PARAM = "query";
    final static String INCLUDE_STRING_PARAM = "includes";
    final static String FIELDS_STRING_PARAM = "fields";





    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     * @param courseQuery The location that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String courseQuery) {
        // COMPLETED (1) Fix this method to return the URL used to query Open Weather Map's API
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, "search")
                .appendQueryParameter(QUERY_STRING_PARAM, courseQuery)
                .appendQueryParameter(INCLUDE_STRING_PARAM, "v1Details")
                .appendQueryParameter(FIELDS_STRING_PARAM, "description,photoUrl")



                .build();


        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param coursesJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static Course [] parsejsonstring(String coursesJsonStr)
            throws JSONException {

        /* Weather information. Each day's forecast info is an element of the "list" array */
        final String OWM_LIST = "list";

        /* All temperatures are children of the "temp" object */
        final String OWM_TEMPERATURE = "temp";

        /* Max temperature for the day */
        final String OWM_MAX = "max";
        final String OWM_MIN = "min";

        final String OWM_WEATHER = "weather";
        final String OWM_DESCRIPTION = "main";

        final String OWM_MESSAGE_CODE = "cod";

        /* String array to hold each day's weather String */

        JSONObject coursesJson = new JSONObject(coursesJsonStr);

        JSONArray courseArray = coursesJson.getJSONArray("elements");

        Course[] parsedCoursesData = new Course[courseArray.length()];


        for (int i = 0; i < courseArray.length(); i++) {
            String name ;
            String slug ;
            String description;
            String photoUrlstring  ;

            String previewLink ;

            /* These are the values that will be collected */

            /* Get the JSON object representing the day */
            JSONObject courseObject = courseArray.getJSONObject(i);

            /*
             * We ignore all the datetime values embedded in the JSON and assume that
             * the values are returned in-order by day (which is not guaranteed to be correct).
             */

            /*
             * Description is in a child array called "weather", which is 1 element long.
             * That element also contains a weather code.
             */
            name = courseObject.getString("name");
            slug = courseObject.getString("slug");
            description = courseObject.getString("description");
            photoUrlstring = courseObject.getString("photoUrl");

            //previewLink= courseObject.getString("previewLink");
            URL photoUrl = null;

            try {

                photoUrl = new URL(photoUrlstring);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            URL courseUrl = null;

            try {

                courseUrl = new URL(COURSE_BASE_URL+slug );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }



            /*
             * Temperatures are sent by Open Weather Map in a child object called "temp".
             *
             * Editor's Note: Try not to name variables "temp" when working with temperature.
             * It confuses everybody. Temp could easily mean any number of things, including
             * temperature, temporary and is just a bad variable name.
             */

            parsedCoursesData[i] = new Course(name,courseUrl,description,photoUrl) ;
        }

        return parsedCoursesData;
    }

    /**
     * Parse the JSON and convert it into ContentValues that can be inserted into our database.
     *
     * @param context         An application context, such as a service or activity context.
     * @param forecastJsonStr The JSON to parse into ContentValues.
     *
     * @return An array of ContentValues parsed from the JSON.
     */
    public static ContentValues[] getFullWeatherDataFromJson(Context context, String forecastJsonStr) {
        /** This will be implemented in a future lesson **/
        return null;
    }
}
