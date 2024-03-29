import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

/*
Assigns Drivers and Volunteers to relevant time slots.
 */
public class Scheduler {
    private ArrayList<Driver> drivers;
    private ArrayList<Volunteer> volunteers;
    private ArrayList<Teacher> teachers;

    public Scheduler(ArrayList<Driver> drivers, ArrayList<Volunteer> volunteers, ArrayList<Teacher> teachers) {
        this.drivers = drivers;
        this.volunteers = volunteers;
        this.teachers = teachers;
    }

    /*
    Loops over every driver, assigning the first-available rider to a teacher at the school this driver is driving to.
     */
    public void assignRiders() {
        ArrayList<Volunteer> ridersRemaining = (ArrayList) volunteers.clone();
        int currentDriver = 0;
        int lastAssigned = 0;

        while (ridersRemaining.size() > 0 && (currentDriver - lastAssigned) <= drivers.size()) {
            Driver driver = this.drivers.get(currentDriver % this.drivers.size());
            if(driver.getTeacher() == null) {
                currentDriver += 1;
                continue;
            }
            boolean driverHasRoom = driver.getSeats() - driver.getRiders().size() > 1;
            if(!driverHasRoom) {
                currentDriver += 1;
                continue;
            }
            for(Volunteer rider : ridersRemaining) {
            	if ( ( (rider.getPreferredSchool() != (null)) && rider.getPreferredSchool().equals(driver.getTeacher().getSchool())) || rider.getPreferredSchool() == null) {
            		ArrayList<TimeBlock> overlaps = rider.getAvailability().findOverlaps(driver.getAssignment());
                    if(overlaps.size() != 0) {
                        TimeBlock overlap = overlaps.get(0);
                        // there is overlap
                        // assign this rider to this driver
                        ArrayList<Teacher> teachersAtTime = new ArrayList<>();
                        for(Teacher t : this.teachers) {
                            if(t.getSchool().equals(driver.getTeacher().getSchool()) && t.getAvailability().findOverlaps(driver.getAssignment()) != null) {
                                // this teacher is an option for this rider
                                teachersAtTime.add(t);
                            }
                        }
                        if(teachersAtTime.size() != 0) {
                            teachersAtTime.get(0).assignVolunteer(rider);
                            rider.setAssignedTime(overlap, driver.getAssignment().getDay());
                            ridersRemaining.remove(rider);
                            driver.addRider(rider);
                            lastAssigned = currentDriver;
                            break;
                        }
                    }
                }
            }
            currentDriver += 1;
        }
    }

    /*
    Loops over every teacher, assigning the first-available driver to each teacher round-robin style.
     */
    public void assignDrivers() {
        ArrayList<Driver> driversRemaining = (ArrayList) drivers.clone(); // clone the list of drivers so we don't mutate the original source
        ArrayList<Driver> driversAssigned = new ArrayList<>();

        int currentTeacher = 0;
        int lastAssigned = 0;
        while(driversRemaining.size() > 0 && (currentTeacher - lastAssigned) <= drivers.size()) {
            Teacher teacher = teachers.get(currentTeacher % teachers.size());
            for(Driver d : driversRemaining) {
                boolean driverAssigned = false;
                ArrayList<TimeBlockAndDay> overlaps;
                if((overlaps = d.getAvailability().findOverlaps(teacher.getAvailability())).size() != 0) {
                	if ( ( (d.getPreferredSchool() != null) && d.getPreferredSchool().equals(teacher.getSchool())) || d.getPreferredSchool() == null) {
	                    for(TimeBlockAndDay overlap : overlaps) {
	                            // we can safely assign this driver to this time
	                            teacher.assignVolunteer(d);
	                            d.setAssignedTime(overlap.getTimeBlock(), overlap.getDay());
	                            driversRemaining.remove(d);
	                            driversAssigned.add(d);
	                            driverAssigned = true;
	                            lastAssigned = currentTeacher;
	                            break;
	                    }
                	}
                }
                if(driverAssigned) break;
            }
            currentTeacher += 1;
        }
    }
}
