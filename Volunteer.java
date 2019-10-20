
public class Volunteer extends Person {
	private boolean isSpanishSpeaking; 
	private String preferredSchool;
	private boolean isCurry; 
	private String teacher;
	
	public Volunteer(String firstName, String lastName, String email, String phoneNo, String teacher, 
			String preferredSchool, boolean isCurry, boolean isSpanishSpeaking) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.isSpanishSpeaking = isSpanishSpeaking; 
		this.preferredSchool = preferredSchool;
		this.isCurry = isCurry; 
		this.teacher = teacher;
	}
}
