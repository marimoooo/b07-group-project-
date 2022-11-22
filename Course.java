package timeline;

public class Course {
	String course;
    String[] session;
    String[] prerequisites;
    
    public Course() {
		course = "";
		session = null;
		prerequisites = null;
	}
	
	public Course(String course, String[] session, String[] prerequisites) {
		this.course = course;
		this.session = session;
		this.prerequisites = prerequisites;
	}
	
	
}