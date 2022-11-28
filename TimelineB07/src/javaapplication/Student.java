/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import java.util.List;


public class Student {
    
    public String name;
    public List<String> courses; //los que yo estudiante he cursado ['csb32', 'csss32']

    public Student(String name, List<String> courses) {
        this.name = name;
        this.courses = courses;
    }
    
}
