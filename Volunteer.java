
public class Volunteer extends Person {
	private boolean isSpanishSpeaking; 
	private String preferredSchool;
	private boolean isCurry; 
	private String teacher;
	
	public Volunteer(String firstName, String lastName, String email, String phoneNo, String teacher, 
			String preferredSchool, boolean isCurry, boolean isSpanishSpeaking) {
		super(firstName, lastName, email, phoneNo);
		this.isSpanishSpeaking = isSpanishSpeaking; 
		this.preferredSchool = preferredSchool;
		this.isCurry = isCurry; 
		this.teacher = teacher;
	}
	public boolean getSpanish() {
		return isSpanishSpeaking;
	}
	public String getPreferredSchool() {
		return preferredSchool;
	}
	public boolean getCurry() {
		return isCurry;
	}
	public String getTeacher() {
		return teacher;
	}
	
}
