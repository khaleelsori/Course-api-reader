package com.example.root.course_api_reader;

import java.net.URL;

/**
 * Created by root on 7/26/17.
 */

public class Course {
    String name;
    String description;
    URL photoUrl;
    URL courseUrl;

    public Course(String name,URL courseUrl , String description, URL photoUrl){
        this.name =name;
        this.courseUrl =courseUrl;
        this.description =description;
        this.photoUrl =photoUrl;



    }



}

