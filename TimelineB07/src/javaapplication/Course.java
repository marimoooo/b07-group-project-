/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import java.util.List;


public class Course {
    
    public String code;
    public List<String> session;
    public List<String> prerequisites;

    public Course(String code, List<String> session, List<String> prerequisites) {
        this.code = code;
        this.session = session;
        this.prerequisites = prerequisites;
    }
    
    
}
