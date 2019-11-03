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
        ArrayList<Driver> driversAssigned = new ArrayList<>();

        int currentTeacher = 0;
        Teacher teacher = teachers.get(currentTeacher % teachers.size());
        while(driversRemaining.size() > 0) {
            for(Driver d : driversRemaining) {
                boolean driverAssigned = false;
                ArrayList<TimeBlockAndDay> overlaps;
                if((overlaps = d.getAvailability().findOverlaps(teachers.get(currentTeacher % teachers.size()).getAvailability())).size() != 0) {
                    for(TimeBlockAndDay overlap : overlaps) {
                        boolean conflictFound = false;
                        for(Driver assigned : driversAssigned) {
                            if(assigned.getTeacher().getSchool() == teacher.getSchool() && assigned.getAssignedDayOfWeek() == overlap.getDay() && assigned.getAssignedTime().overlapWith(overlap.getTimeBlock()) != null) {
                                // these drivers are assigned on the same day
                                conflictFound = true;
                                break;
                            }
                        }
                        if(!conflictFound) {
                            // we can safely assign this driver to this time
                            currentTeacher += 1;
                            teacher.assignVolunteer(d);
                            d.setAssignedTime(overlap.getDay(), overlap.getTimeBlock());
                            driversRemaining.remove(d);
                            driversAssigned.add(d);
                            driverAssigned = true;
                            break;
                        }
                    }
                }
                if(driverAssigned) break;
            }
        }
    }
}
