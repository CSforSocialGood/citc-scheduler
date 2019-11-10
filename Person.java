
public abstract class Person {
	protected String firstName; 
	protected String lastName; 
	protected String email;
	protected String phoneNo;
	protected Availability availability;
	
	public Person(String firstName, String lastName, String email, String phoneNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String toString() {
		return "" + firstName + " " + lastName + " " + email + " " + phoneNo;
	}
	public Availability getAvailability() {
		return this.availability;
	}
	public void showAvailability() {
		System.out.println("Monday");
		  for (TimeBlock t: getAvailability().getAvailabilityForDay(DayOfWeek.Monday)) {
	    	   System.out.println(t.toString());
	       }
		System.out.println("Tuesday");
		  for (TimeBlock t: getAvailability().getAvailabilityForDay(DayOfWeek.Tuesday)) {
	    	   System.out.println(t.toString());
	       }
		System.out.println("Wednesday");
		  for (TimeBlock t: getAvailability().getAvailabilityForDay(DayOfWeek.Wednesday)) {
	    	   System.out.println(t.toString());
	       }
		System.out.println("Thursday");
		  for (TimeBlock t: getAvailability().getAvailabilityForDay(DayOfWeek.Thursday)) {
	    	   System.out.println(t.toString());
	       }
		System.out.println("Friday");
		  for (TimeBlock t: getAvailability().getAvailabilityForDay(DayOfWeek.Friday)) {
	    	   System.out.println(t.toString());
	       }
	}
}
