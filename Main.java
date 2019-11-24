import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Excel_Input.makeReturnees("/Users/joe/Desktop/tsvs/Cavs In Classroom Returnee Interest Form (Responses) - Form Responses 1.tsv");
        Excel_Input.makeTeachers("/Users/joe/Desktop/tsvs/TEST Cavs in the Classroom Teacher Interest Form (Responses) - Form Responses 1.tsv");
        Excel_Input.makeNewParticipants("/Users/joe/Desktop/tsvs/Cavs In Classroom Curry and New Volunteer Interest Form (Responses) - Form Responses 1.tsv");


        ArrayList<Teacher> teachers = Excel_Input.getTeachers();
        ArrayList<Volunteer> volunteers = Excel_Input.getVolunteers();
        ArrayList<Driver> drivers = Excel_Input.getDrivers();

        Scheduler myScheduler = new Scheduler(drivers, volunteers, teachers);
        myScheduler.assignDrivers();
        myScheduler.assignRiders();
    }
}
