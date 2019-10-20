
public class Driver extends Volunteer {
	private int seatsInCar;

	public Driver(String firstName, String lastName, String email, String phoneNo, String preferredSchool, boolean isCurry, boolean isSpanishSpeaking, int seatsInCar) {
		super(firstName, lastName, email, phoneNo, preferredSchool, isCurry, isSpanishSpeaking);
		this.seatsInCar = seatsInCar;
	}
	public int getSeats() {
		return seatsInCar;
	}

}
