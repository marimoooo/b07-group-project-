/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package javaapplication.timeline;


public enum Season {
    WINTER("Winter", 1, 4, "Summer"),
    SUMMER("Summer", 5, 8, "Fall"),
    FALL("Fall", 9, 12, "Winter");
    
    private final String name;
    private final int monthStart;
    private final int monthEnd;
    private final String nextSeason;
    
    private Season(String name, int monthStart, int monthEnd, String nextSeason) {    
        this.name = name;
        this.monthStart = monthStart;
        this.monthEnd = monthEnd;
        this.nextSeason = nextSeason;
    }

    public String getName() {
        return name;
    }

    public int getMonthStart() {
        return monthStart;
    }

    public int getMonthEnd() {
        return monthEnd;
    }

    public String getNextSeason() {
        return nextSeason;
    }
}
