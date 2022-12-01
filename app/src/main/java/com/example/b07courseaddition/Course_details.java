package com.example.b07courseaddition;

public class Course_details {
    private String course_name;
    private String course_code;
    private String course_offering;
    private String course_pre_req;

    public Course_details(){

    }

    public Course_details(String course_name, String course_code, String course_offering, String course_pre_req) {
        this.course_name = course_name;
        this.course_code = course_code;
        this.course_offering = course_offering;
        this.course_pre_req = course_pre_req;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_offering() {
        return course_offering;
    }

    public void setCourse_offering(String course_offering) {
        this.course_offering = course_offering;
    }

    public String getCourse_pre_req() {
        return course_pre_req;
    }

    public void setCourse_pre_req(String course_pre_req) {
        this.course_pre_req = course_pre_req;
    }
}
