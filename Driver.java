import java.util.ArrayList;

public class Driver extends Volunteer implements Exportable {
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


	@Override
	public int getNumColumns() {
		return 8;
	}

	@Override
	public int getNumRows() {
		return 2 + this.getRiders().size();
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
	    Volunteer v;
	    String driverRiderTitle;
		if(row == 0) {
			return super.getValueForCell(0, column);
		} else if (row == this.getRiders().size() + 1) {
		    return ""; // blank row
		} else {
			v = this.getRiders().get(row - 1);
			driverRiderTitle = "Rider";
		}
		return v.getValueForCell(0, column);
	}
}
