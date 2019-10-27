import java.util.ArrayList;

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
    Loops over every teacher, assigning the first-available driver to each teacher round-robin style.
     */
    public void assignDrivers() {
        ArrayList<Driver> driversRemaining = (ArrayList) drivers.clone(); // clone the list of drivers so we don't mutate the original source

        int currentTeacher = 0;
        while(driversRemaining.size() > 0) {
            for(Driver d : driversRemaining) {
                TimeBlockAndDay overlap;
                if((overlap = d.getAvailability().findOverlap(teachers.get(currentTeacher % teachers.size()).getAvailability())) != null) {
                    currentTeacher += 1;
                    teachers.get(currentTeacher % teachers.size()).assignVolunteer(d);
                    d.setAssignedTime(overlap.getDay(), overlap.getTimeBlock());
                    driversRemaining.remove(d);
                    break;
                }
            }
        }
    }
}
