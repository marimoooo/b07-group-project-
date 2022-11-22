package timeline;

import java.util.List;

public class TimelineGenerator {
	List<Course> futureCourses;
	List<Course> takenCourses;
	
	public TimelineGenerator(List<Course> futureCourses, List<Course> takenCourses) {
		this.futureCourses = futureCourses;
		this.takenCourses = takenCourses;
	}
	
	public List<Course> validFutureCourses(List<Course> futureCourses, List<Course> takenCourses) {
		// filter taken courses out of the future courses list
		// filter null case
		//futureCourses = futureCourses.stream().filter(takenCourses);
		return futureCourses;
	 
	}
	
	public List<Course> foundCourses(List<Course> futureCourses, List<Course> takenCourse) {
		//check in var i is equal to future courses length
		
		//check if there are pending pre-requisites
		
		//if there are call found Courses
		
		// if not add course to timetable
		return takenCourse;
	    
	}


}