package pojos;

public class BGetCourse {

	private String url;
	private String services;
	private String automation;
	private CCourses Courses;
	private String instructor;
	private String linkedIn;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getAutomation() {
		return automation;
	}
	public void setAutomation(String automation) {
		this.automation = automation;
	}
	public CCourses getCourses() {
		return Courses;
	}
	public void setCourses(CCourses courses) {
		Courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
}
