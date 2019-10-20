
public class Driver extends Person {
	private int seatsInCar;

	public Driver(String firstName, String lastName, String email, String phoneNo, int seatsInCar) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.seatsInCar = seatsInCar;
	}
	public int getSeats() {
		return seatsInCar;
	}

}
