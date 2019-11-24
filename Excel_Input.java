import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Excel_Input {
	
	public static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	public static ArrayList<Volunteer> volunteers = new ArrayList<Volunteer>();
	public static ArrayList<Driver> drivers = new ArrayList<Driver>();

	public static String firstName = "";
	public static String lastName = "";
	public static String email = "";
	public static String phone = "";
	public static String preferredSchool = "";
	public static boolean isDriver = false;
	public static boolean isCurry = false;
	public static boolean isSpanish = false;
	public static String seatInfo = "";

	public static String monday = "";
	public static String tuesday = "";
	public static String wednesday = "";
	public static String thursday = "";
	public static String friday = "";
	
	
	//constructor
	public Excel_Input () {
		
	}
		
	// add availability in 30 min blocks for one day of the week
	public static void addAvailability(Person v, String timesPerDay, DayOfWeek day) {
		String[] timeBlock = timesPerDay.split(",");
		for (String time : timeBlock) {
			String[] hoursThenMin = time.split(":");
			try {
			int hour = Integer.parseInt(hoursThenMin[0].strip());
			int min = Integer.parseInt(hoursThenMin[1].strip());
			v.getAvailability().addTimeBlock(day, new TimeBlock(LocalTime.of(hour, min), Duration.ofMinutes(30)));
			} catch (NumberFormatException e){
				//System.out.println("No Availability on " + day);
			}
		}
	}

	// based on driverInfo, will make a driver with assumed max seats or make a
	// volunteer if driving self or no car
	public static void makeDriverOrVolunteer() {
		if (isDriver) {
			int seatsInCar = 0;
			if (seatInfo.equals("5-7")) seatsInCar = 7;
			else if (seatInfo.equals("1-4")) seatsInCar = 4;
			if(preferredSchool.equals("I'll take any school!")) preferredSchool = null;
			Driver d = new Driver(firstName, lastName, email, phone, preferredSchool, isCurry, isSpanish, seatsInCar);
			addAvailability(d, monday, DayOfWeek.Monday);
			addAvailability(d, tuesday, DayOfWeek.Tuesday);
			addAvailability(d, wednesday, DayOfWeek.Wednesday);
			addAvailability(d, thursday, DayOfWeek.Thursday);
			addAvailability(d, friday, DayOfWeek.Friday);
			drivers.add(d);
		} else {
			Volunteer v = new Volunteer(firstName, lastName, email, phone, preferredSchool, isCurry, isSpanish);
			addAvailability(v, monday, DayOfWeek.Monday);
			addAvailability(v, tuesday, DayOfWeek.Tuesday);
			addAvailability(v, wednesday, DayOfWeek.Wednesday);
			addAvailability(v, thursday, DayOfWeek.Thursday);
			addAvailability(v, friday, DayOfWeek.Friday);
			volunteers.add(v);
		}
	}

	public static void makeReturnees(String returneeFilePath) {
		try {
			BufferedReader TSVFile = new BufferedReader(new FileReader(returneeFilePath));
			String dataRow;

			dataRow = TSVFile.readLine();
			dataRow = TSVFile.readLine();
			// Skips row of headers

			while (dataRow != null) {
				ArrayList<String> dataArray = new ArrayList<String>();
				//System.out.println(dataRow);
				firstName = "";
				lastName = "";
				email = "";
				phone = "";
				preferredSchool = "";
				isDriver = false;
				isCurry = false;
				isSpanish = false;
				seatInfo = "";

				monday = "";
				tuesday = "";
				wednesday = "";
				thursday = "";
				friday = "";

				String[] split = dataRow.split("\t");
				//System.out.println(st.countTokens());
				// skips column 0 of timestamp
				for (int column = 1; column < 15; column++) {
					//System.out.println("c "+ column);
					if (split.length > column) {
						String dataToken = split[column];
						dataArray.add(dataToken);

						switch (column) {
						case 1:
							firstName = dataToken;
							break;
						case 2:
							lastName = dataToken;
							break;
						case 3:
							email = dataToken;
							break;
						case 4:
							phone = dataToken;
							break;
						case 5:
							isCurry = dataToken.equals("Yes") ? true : false;
							break;
						case 6:
							monday = dataToken;
							break;
						case 7:
							tuesday = dataToken;
							break;
						case 8:
							wednesday = dataToken;
							break;
						case 9:
							thursday = dataToken;
							break;
						case 10:
							friday = dataToken;
							break;
						case 11:
							preferredSchool = dataToken;
							break;
						case 12:
							isDriver = dataToken.equals("Yes") ? true : false;
							break;
						case 13:
							seatInfo = dataToken;
							break;
						case 14:
							isSpanish = dataToken.equals("Yes") ? true : false;
							break;
						default:
							System.out.println("Error");
						}
					}
				}
				

				makeDriverOrVolunteer();
				
				dataRow = TSVFile.readLine();
				
			} // Stop reading file

			TSVFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("Returnee File not found. Check if file is named returnee.tsv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void makeNewParticipants(String newVolunteerFilePath) {
		try {
			BufferedReader TSVFile = new BufferedReader(new FileReader(newVolunteerFilePath));
			String dataRow;

			dataRow = TSVFile.readLine();
			dataRow = TSVFile.readLine();
			// Skips row of headers

			while (dataRow != null) {
				ArrayList<String> dataArray = new ArrayList<String>();
				//System.out.println(dataRow);
				firstName = "";
				lastName = "";
				email = "";
				phone = "";
				isDriver = false;
				isCurry = false;
				isSpanish = false;

				monday = "";
				tuesday = "";
				wednesday = "";
				thursday = "";
				friday = "";

				String[] split = dataRow.split("\t");
				//System.out.println(st.countTokens());
				// skips column 0 of timestamp
				for (int column = 1; column < 13; column++) {
					//System.out.println("c "+ column);
					if (split.length > column) {
						String dataToken = split[column];
						dataArray.add(dataToken);
				
						switch (column) {
						case 1:
							firstName = dataToken;
							break;
						case 2:
							lastName = dataToken;
							break;
						case 3:
							email = dataToken;
							break;
						case 4:
							phone = dataToken;
							break;
						case 5:
							isCurry = dataToken.equals("Yes") ? true : false;
							break;
						case 6:
							monday = dataToken;
							break;
						case 7:
							tuesday = dataToken;
							break;
						case 8:
							wednesday = dataToken;
							break;
						case 9:
							thursday = dataToken;
							break;
						case 10:
							friday = dataToken;
							break;
						case 11:
							isDriver = dataToken.equals("Yes") ? true : false;
							break;
						case 12:
							seatInfo = dataToken;
							break;
						case 13:
							isSpanish = dataToken.equals("Yes") ? true : false;
							break;
						default:
							System.out.println("Error");
						}
					}
				
					}
				makeDriverOrVolunteer();
				
				dataRow = TSVFile.readLine();
				
			} // Stop reading file

			TSVFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("New Volunteer File not found. Check if file is named newVolunteer.tsv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void makeTeachers(String teacherFilePath) {
		try {
			BufferedReader TSVFile = new BufferedReader(new FileReader(teacherFilePath));
			String dataRow;

			dataRow = TSVFile.readLine();
			dataRow = TSVFile.readLine();
			// Skips row of headers

			while (dataRow != null) {
				ArrayList<String> dataArray = new ArrayList<String>();
				//System.out.println(dataRow);
				
				firstName = "";
				lastName = "";
				email = "";
				phone = "";
				preferredSchool = "";
				isDriver = false;
				isCurry = false;
				isSpanish = false;

				monday = "";
				tuesday = "";
				wednesday = "";
				thursday = "";
				friday = "";

				String[] split = dataRow.split("\t");
				//System.out.println(st.countTokens());
				// skips column 0 of timestamp
					for (int column = 1; column < 10; column++) {
						//System.out.println("c "+ column);
						if (split.length > column) {
						String dataToken = split[column];
						dataArray.add(dataToken);
				
						switch (column) {
						case 1:
							String [] fullName = dataToken.split(" ");
							firstName = fullName[0];
							if (fullName.length >= 2) lastName = fullName[1] ;
							break;
						case 2:
							preferredSchool = dataToken;
							break;
						case 3:
							email = dataToken;
							break;
						case 4:
							phone = dataToken;
							break;
						case 5:
							monday = dataToken;
							break;
						case 6:
							tuesday = dataToken;
							break;
						case 7:
							wednesday = dataToken;
							break;
						case 8:
							thursday = dataToken;
							break;
						case 9:
							friday = dataToken;
							break;
							
						default:
							System.out.println("Error");
						}
					}
					
					}
					
					Teacher t = new Teacher (firstName, lastName, email, phone, preferredSchool);
					
					addAvailability(t, monday, DayOfWeek.Monday);
					addAvailability(t, tuesday, DayOfWeek.Tuesday);
					addAvailability(t, wednesday, DayOfWeek.Wednesday);
					addAvailability(t, thursday, DayOfWeek.Thursday);
					addAvailability(t, friday, DayOfWeek.Friday);
					
					teachers.add(t);
					
					dataRow = TSVFile.readLine();
				
			} 

			TSVFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("Teacher File not found. Check if file is named teacher.tsv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void showDrivers(boolean showAvailability) {
		System.out.println("Drivers: ");
		for (Driver d : drivers) {
			System.out.println(d);
			if (showAvailability) d.showAvailability();
		}
	}
	
	public static void showVolunteers (boolean showAvailability) {
		System.out.println("Volunteers: ");
		for (Volunteer v : volunteers) {
			System.out.println(v);
			if (showAvailability) v.showAvailability();
		}
	}
	
	public static void showTeachers (boolean showAvailability) {
		System.out.println("Teachers: ");
		for (Teacher t : teachers) {
			System.out.println(t);
			if (showAvailability) t.showAvailability();
		}
	}

	public static ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public static ArrayList<Volunteer> getVolunteers() {
		return volunteers;
	}

	public static ArrayList<Driver> getDrivers() {
		return drivers;
	}
	
	/*public static void main (String [] args) {
		//makeReturnees("returnee.tsv");
		//makeNewParticipants("newVolunteer.tsv");
		//makeTeachers("teacher.tsv");
		//showVolunteers(false);
		//showDrivers(false);
		//showTeachers(true);
	}*/
	
}
