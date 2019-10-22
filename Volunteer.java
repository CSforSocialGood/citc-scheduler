
public class Volunteer extends Person implements Comparable {
	private boolean isSpanishSpeaking; 
	private String preferredSchool;
	private boolean isCurry;

	/*
	A reference to the Teacher to whom this volunteer is assigned.
	This will be null if the Volunteer has not yet been assigned to a Teacher.
	 */
	private Teacher teacher;

	public Volunteer(String firstName, String lastName, String email, String phoneNo,
			String preferredSchool, boolean isCurry, boolean isSpanishSpeaking) {
		super(firstName, lastName, email, phoneNo);
		this.isSpanishSpeaking = isSpanishSpeaking; 
		this.preferredSchool = preferredSchool;
		this.isCurry = isCurry; 
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
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Volunteer) {
			Volunteer x = (Volunteer) o;
			if(this.isCurry && x.isCurry==false) {
				return 1;
			}
		}
		return 0;
	}
}
