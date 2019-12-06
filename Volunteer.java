public class Volunteer extends Person implements Comparable, Exportable {
	private boolean isSpanishSpeaking;
	private String preferredSchool;
	private boolean isCurry;
	private TimeBlockAndDay assignment;

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

	public TimeBlockAndDay getAssignment() {
		return this.assignment;
	}

	public void setAssignedTime(TimeBlock timeBlock, DayOfWeek day) {
	    this.assignment = new TimeBlockAndDay(timeBlock, day);
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
	public int getNumColumns() {
		return 8;
	}

	@Override
	public int getNumRows() {
		return 1;
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {
				"Driver/Rider?",
				"First Name",
				"Last Name",
				"Phone #",
				"Email",
				"School",
				"Teacher",
				"Assignment"
		};
	}

	@Override
	public String getValueForCell(int row, int column) {
		switch(column) {
			case 0:
				return (this.getCurry() ? "Curry " : " ") + (Driver.class.isInstance(this) ? "Driver" : "Rider");
			case 1:
				return this.getFirstName();
			case 2:
				return this.getLastName();
			case 3:
				return this.getPhoneNo();
			case 4:
				return this.getEmail();
			case 5:
				return getTeacher() != null ? getTeacher().getSchool() : "(none)";
			case 6:
				return getTeacher() != null ? getTeacher().getLastName() : "(none)";
			case 7:
				return this.getAssignment() != null ? this.getAssignment().toString() : "(none)";
			default:
				return "";
		}
	}
}
