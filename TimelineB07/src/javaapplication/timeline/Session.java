/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication.timeline;

import java.util.List;


public class Session {
    public String season;
    public int year;
    public List<String> courses;

    public Session(String season, int year, List<String> courses) {
        this.season = season;
        this.year = year;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Session{" + "season=" + season + ", year=" + year + ", courses=" + courses + '}';
    }
    
}
