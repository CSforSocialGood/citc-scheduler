
public class Teacher extends Person {
	private String school;

	public Teacher(String firstName, String lastName, String email, String phoneNo, String school) {
		super(firstName, lastName, email, phoneNo);
		this.school = school;
	}
	public String getSchool() {
		return school;
	}
}