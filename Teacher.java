<<<<<<< HEAD
import java.util.ArrayList;

public class Teacher extends Person {
	private String school;
	/*
	An ArrayList of volunteers who have been assigned to this teacher.
	 */

	private ArrayList<Volunteer> assignedVolunteers;
	//private Availability availability;

	public Teacher(String firstName, String lastName, String email, String phoneNo, String school) {
		super(firstName, lastName, email, phoneNo);
		this.school = school;
		this.assignedVolunteers = new ArrayList<Volunteer>();
		this.availability = new Availability();
	}
	public String getSchool() {
		return school;
	}
	public ArrayList<Volunteer> getAssignedVolunteers() {
	    return this.assignedVolunteers;
	}
	public Availability getAvailability() {
		return this.availability;
	}

	/*
	Adds a Volunteer to this Teacher's list of assigned volunteers and updates the Volunteer's Teacher field
	to reference this Teacher.
	 */
	public void assignVolunteer(Volunteer v) {
		this.assignedVolunteers.add(v);
		v.setTeacher(this);
	}
	
	@Override
	public String toString() {
		return "Teacher: " + super.toString() + " School: " + school;
	}
=======
import java.util.ArrayList;

public class Teacher extends Person {
	private String school;
	/*
	An ArrayList of volunteers who have been assigned to this teacher.
	 */

	private ArrayList<Volunteer> assignedVolunteers;
	private Availability availability;

	public Teacher(String firstName, String lastName, String email, String phoneNo, String school) {
		super(firstName, lastName, email, phoneNo);
		this.school = school;
		this.assignedVolunteers = new ArrayList<Volunteer>();
		this.availability = new Availability();
	}
	public String getSchool() {
		return school;
	}
	public ArrayList<Volunteer> getAssignedVolunteers() {
	    return this.assignedVolunteers;
	}
	public int getNumVolunteers() {
		return this.getAssignedVolunteers().size();
	}
	public Availability getAvailability() {
		return this.availability;
	}

	/*
	Adds a Volunteer to this Teacher's list of assigned volunteers and updates the Volunteer's Teacher field
	to reference this Teacher.
	 */
	public void assignVolunteer(Volunteer v) {
		this.assignedVolunteers.add(v);
		v.setTeacher(this);
	}
>>>>>>> f51e390a5443b88c144df8c4bb7273853fe1c4f4
}