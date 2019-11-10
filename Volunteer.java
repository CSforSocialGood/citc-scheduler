
public class Volunteer extends Person implements Comparable {
	private boolean isSpanishSpeaking; 
	private String preferredSchool;
	private boolean isCurry;
	private Availability availability;
	private DayOfWeek assignedDay;
	private TimeBlock assignedTime;

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
		this.availability = new Availability();
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

	public TimeBlock getAssignedTime() {
		return this.assignedTime;
	}
	public DayOfWeek getAssignedDayOfWeek() {
		return this.assignedDay;
	}

	public Availability getAvailability() {
		return this.availability;
	}

	public void setAssignedTime(DayOfWeek day, TimeBlock timeBlock) {
		this.assignedDay = day;
		this.assignedTime = timeBlock;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Volunteer) {
			Volunteer x = (Volunteer) o;
			if(this.isCurry && x.isCurry==false) {
				return -1;
			}
			if(this.isCurry == x.isCurry) {
				return 0; 
			}
		}
		return 1;
	}
	
	
	@Override
	public String toString() {
		return super.toString() + " Preferred-School: " + preferredSchool + " Curry: " + isCurry +" Spanish-Speaking: " + isSpanishSpeaking;
	}
}
