/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javaapplication.timeline.Session;
import javaapplication.timeline.Timeline;
import javax.swing.JPanel;


public class JavaApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Student student = new Student("Pepito", Arrays.asList("CSCA08", "CSCA48", "CSCA67"));
        List<String> desiredCourses = new ArrayList<>(Arrays.asList("CSCC24", "CSCC63", "CSCA67"));
        
        Timeline timeline = new Timeline(new CourseRepository());
        List<Session> sessions = timeline.sessions(desiredCourses, student.courses);
        
        System.out.println(sessions);
        
    }
    
}
