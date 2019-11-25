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
	public static String preferredSchool = null;
	public static boolean prefersBaker = false;
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
		
	// add availability in 1 hour blocks for one day  
	public static void addAvailability(Person v, String timesPerDay, DayOfWeek day) {
		String[] timeBlock = timesPerDay.split(",");
		
		for (int i = 0; i < timeBlock.length-1 ; i++) {
			try {
				String time1 = timeBlock[i].split("-")[0].strip();
				String time2 = timeBlock[i+1].split("-")[0].strip();
			
				int hour1 = Integer.parseInt(time1.split(":")[0]);
				int min1 = Integer.parseInt(time1.split(":")[1]);
				
				int hour2 = Integer.parseInt(time2.split(":")[0]);
				int min2 = Integer.parseInt(time2.split(":")[1]);
				
				hour1 = (hour1 == 1) ? 13 : hour1;
				hour2 = (hour2 == 1) ? 13 : hour2;
				hour1 = (hour1 == 2) ? 14 : hour1;
				hour2 = (hour2 == 2) ? 14 : hour2;
							
				if (hour1 == hour2 && (min1 == 0 && min2 == 30)) {
					v.getAvailability().addTimeBlock(day, new TimeBlock(LocalTime.of(hour1, min1), Duration.ofMinutes(60)));
				}
				else if (hour1+1 == hour2 && (min1 == 30 && min2 == 0)) {
					v.getAvailability().addTimeBlock(day, new TimeBlock(LocalTime.of(hour1, min1), Duration.ofMinutes(60)));
				}
			}
			catch (NumberFormatException e){
				//System.out.println("No Availability on " + day);
			}
		}
	}

	//Baker Butler than 2 hour time blocks
	public static void addAvailabilityBaker(Person v, String timesPerDay, DayOfWeek day) {
		String[] timeBlock = timesPerDay.split(",");
		
		for (int i = 0; i < timeBlock.length-3 ; i++) {
			try {
				String time1 = timeBlock[i].split("-")[0].strip();
				String time2 = timeBlock[i+1].split("-")[0].strip();
				String time3 = timeBlock[i+2].split("-")[0].strip();
				String time4 = timeBlock[i+3].split("-")[0].strip();
				
				int hour1 = Integer.parseInt(time1.split(":")[0]);
				int min1 = Integer.parseInt(time1.split(":")[1]);
				
				int hour2 = Integer.parseInt(time2.split(":")[0]);
				int min2 = Integer.parseInt(time2.split(":")[1]);
				
				int hour3 = Integer.parseInt(time3.split(":")[0]);
				int min3 = Integer.parseInt(time3.split(":")[1]);
				
				int hour4 = Integer.parseInt(time4.split(":")[0]);
				int min4 = Integer.parseInt(time4.split(":")[1]);
				
				hour1 = (hour1 == 1) ? 13 : hour1;
				hour2 = (hour2 == 1) ? 13 : hour2;
				hour3 = (hour3 == 1) ? 13 : hour3;
				hour4 = (hour4 == 1) ? 13 : hour4;
	
				hour1 = (hour1 == 2) ? 14 : hour1;
				hour2 = (hour2 == 2) ? 14 : hour2;
				hour3 = (hour3 == 2) ? 14 : hour3;
				hour4 = (hour4 == 2) ? 14 : hour4;
				
				if ((hour1 == hour2) && (hour3 == hour4) && (min1 == 0 && min3 == 0) && (min2 == 30 && min4 == 30)) {
					v.getAvailability().addTimeBlock(day, new TimeBlock(LocalTime.of(hour2, min2), Duration.ofMinutes(60)));
				}
				else if ((hour2 == hour3) && (hour1+1 == hour2) && (hour3+1 == hour4) && (min1 == 30 && min3 == 30) && (min2 == 0 && min4 == 0)) {
					v.getAvailability().addTimeBlock(day, new TimeBlock(LocalTime.of(hour2, min2), Duration.ofMinutes(60)));
					
				}
			} catch (NumberFormatException e){
				//System.out.println("No Availability on " + day);
			}
		}
		
	}
	// based on driverInfo, will make a driver with assumed max seats or make a
	// volunteer if driving self or no car
	public static void makeDriverOrVolunteer() {
		if (preferredSchool.equals("I'll take any school!")) preferredSchool = null;
		int seatsInCar = 0;
		
		if (isDriver) {
			if (prefersBaker) {
				Driver d = new Driver(firstName, lastName, email, phone, preferredSchool, isCurry, isSpanish, seatsInCar);
				addAvailabilityBaker(d, monday, DayOfWeek.Monday);
				addAvailabilityBaker(d, tuesday, DayOfWeek.Tuesday);
				addAvailabilityBaker(d, wednesday, DayOfWeek.Wednesday);
				addAvailabilityBaker(d, thursday, DayOfWeek.Thursday);
				addAvailabilityBaker(d, friday, DayOfWeek.Friday);
				drivers.add(d);
			}
		else {
			if (seatInfo.equals("5-7")) seatsInCar = 7;
			else if (seatInfo.equals("1-4")) seatsInCar = 4;
			Driver d = new Driver(firstName, lastName, email, phone, preferredSchool, isCurry, isSpanish, seatsInCar);
			addAvailability(d, monday, DayOfWeek.Monday);
			addAvailability(d, tuesday, DayOfWeek.Tuesday);
			addAvailability(d, wednesday, DayOfWeek.Wednesday);
			addAvailability(d, thursday, DayOfWeek.Thursday);
			addAvailability(d, friday, DayOfWeek.Friday);
			drivers.add(d);
			}
			
		} else if (prefersBaker) {
			//System.out.println("Baker");
			Volunteer v = new Volunteer(firstName, lastName, email, phone, preferredSchool, isCurry, isSpanish);
			addAvailabilityBaker(v, monday, DayOfWeek.Monday);
			addAvailabilityBaker(v, tuesday, DayOfWeek.Tuesday);
			addAvailabilityBaker(v, wednesday, DayOfWeek.Wednesday);
			addAvailabilityBaker(v, thursday, DayOfWeek.Thursday);
			addAvailabilityBaker(v, friday, DayOfWeek.Friday);
			volunteers.add(v);
			
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
				preferredSchool = null;
				prefersBaker = false;
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
							prefersBaker = (dataToken.equals("Baker Butler Elementary School")) ? true : false;
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
				prefersBaker = false;

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
				preferredSchool = null;
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
			System.out.println("\n" + d);
			if (showAvailability) d.showAvailability();
		}
	}
	
	public static void showVolunteers (boolean showAvailability) {
		System.out.println("Volunteers: ");
		for (Volunteer v : volunteers) {
			System.out.println("\n" + v);
			if (showAvailability) v.showAvailability();
		}
	}
	
	public static void showTeachers (boolean showAvailability) {
		System.out.println("Teachers: ");
		for (Teacher t : teachers) {
			System.out.println("\n" + t);
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
	
	public static void main (String [] args) {
		makeReturnees("newReturnee2.tsv");
		makeNewParticipants("newVolunteer2.tsv");
		makeTeachers("newTeacher.tsv");
		showVolunteers(true);
		showDrivers(true);
		showTeachers(true);
	}
	
}