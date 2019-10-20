
public class Driver extends Person {
	private int seatsInCar;

	public Driver(String firstName, String lastName, String email, String phoneNo, int seatsInCar) {
		super(firstName, lastName, email, phoneNo);
		this.seatsInCar = seatsInCar;
	}
	public int getSeats() {
		return seatsInCar;
	}

}
