import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
   /*     ArrayList<Driver> sampleDrivers = new ArrayList<Driver>();
        ArrayList<Teacher> sampleTeachers = new ArrayList<Teacher>();
        Teacher t1 = new Teacher("Kevin", "Smith", "email89@example.com", "1234567890", "Virginia");
        t1.getAvailability().addTimeBlock(DayOfWeek.Monday, new TimeBlock(LocalTime.of(7,00), LocalTime.of(15, 00)));
        t1.getAvailability().addTimeBlock(DayOfWeek.Tuesday, new TimeBlock(LocalTime.of(7,00), LocalTime.of(15, 00)));
        t1.getAvailability().addTimeBlock(DayOfWeek.Wednesday, new TimeBlock(LocalTime.of(7,00), LocalTime.of(15, 00)));
        Teacher t2 = new Teacher("Fake", "Name", "email92@example.com", "1234567890", "Virginia");
        t2.getAvailability().addTimeBlock(DayOfWeek.Monday, new TimeBlock(LocalTime.of(7,00), LocalTime.of(15, 00)));
        t2.getAvailability().addTimeBlock(DayOfWeek.Tuesday, new TimeBlock(LocalTime.of(7,00), LocalTime.of(15, 00)));
        t2.getAvailability().addTimeBlock(DayOfWeek.Wednesday, new TimeBlock(LocalTime.of(7,00), LocalTime.of(15, 00)));
        sampleTeachers.add(t1);
        sampleTeachers.add(t2);

        Driver d = new Driver("Jim", "Ryan", "email@example.com", "1234567890", "Agnor Hurt", false, false, 5);
        d.getAvailability().addTimeBlock(DayOfWeek.Monday, new TimeBlock(LocalTime.of(8, 00), LocalTime.of(9, 30)));
        Driver d1 = new Driver("Jim", "Jim", "email2@example.com", "1234567890", "Agnor Hurt", false, false, 5);
        d1.getAvailability().addTimeBlock(DayOfWeek.Monday, new TimeBlock(LocalTime.of(8, 00), LocalTime.of(9, 30)));
        d1.getAvailability().addTimeBlock(DayOfWeek.Tuesday, new TimeBlock(LocalTime.of(8, 00), LocalTime.of(9, 30)));
        Driver d2 = new Driver("Jim", "Jim2", "email3@example.com", "1234567890", "Agnor Hurt", false, false, 5);
        d2.getAvailability().addTimeBlock(DayOfWeek.Wednesday, new TimeBlock(LocalTime.of(7, 40), LocalTime.of(10, 00)));

        sampleDrivers.add(d);
        sampleDrivers.add(d1);
        sampleDrivers.add(d2);

        ArrayList<Volunteer> sampleRiders = new ArrayList<>();
        Volunteer v = new Volunteer("John", "Appleseed", "jappleseed@example.com", "12345689", "Agnor Hurt", false, false);
        v.getAvailability().addTimeBlock(DayOfWeek.Monday, new TimeBlock(LocalTime.of(7, 30), LocalTime.of(8, 30)));
        sampleRiders.add(v);

        Scheduler myScheduler = new Scheduler(sampleDrivers, sampleRiders, sampleTeachers);
        myScheduler.assignDrivers();
        myScheduler.assignRiders();
    */
    	
    	SchedulerGUI gui = new SchedulerGUI();
    	gui.setVisible(true);
    	
    	//Scheduler GUI needs to pass on 3 data paths for Excel_Input. Then make and call excel_input
    	
    	
    }
}
