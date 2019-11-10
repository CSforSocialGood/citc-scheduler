import java.util.ArrayList;

public class Driver extends Volunteer {
	private int seatsInCar;
	private ArrayList<Volunteer> riders;

	public Driver(String firstName, String lastName, String email, String phoneNo, String preferredSchool, boolean isCurry, boolean isSpanishSpeaking, int seatsInCar) {
		super(firstName, lastName, email, phoneNo, preferredSchool, isCurry, isSpanishSpeaking);
		this.seatsInCar = seatsInCar;
		this.riders = new ArrayList<Volunteer>();
	}
	public int getSeats() {
		return seatsInCar;
	}
	public void addRider(Volunteer v) {
		this.riders.add(v);
	}
	public ArrayList<Volunteer> getRiders() {
	    return this.riders;
	}
	
	@Override
	public String toString() {
		return "Driver: " + super.toString() + " Seats: " + seatsInCar;
	}
	

}
