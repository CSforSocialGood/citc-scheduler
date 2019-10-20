
public class Teacher extends Person {
	private String school;

	public Teacher(String firstName, String lastName, String email, String phoneNo, String school) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.school = school;
	}
	public String getSchool() {
		return school;
	}
}