
public abstract class Person {
	protected String firstName; 
	protected String lastName; 
	protected String email;
	protected String phoneNo;
	
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
}
