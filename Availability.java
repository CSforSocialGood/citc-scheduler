import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;

enum DayOfWeek {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday
}

public class Availability {
    private HashMap<DayOfWeek, ArrayList<TimeBlock>> weeklyAvailability = new HashMap<DayOfWeek, ArrayList<TimeBlock>>();

    public Availability() {
    }

    public ArrayList<TimeBlock> getAvailabilityForDay(DayOfWeek day) {
        if(!this.weeklyAvailability.containsKey(day)) this.weeklyAvailability.put(day, new ArrayList<TimeBlock>());
        return this.weeklyAvailability.get(day);
    }

    public void addTimeBlock(DayOfWeek day, TimeBlock block) {
        if(!this.weeklyAvailability.containsKey(day)) this.weeklyAvailability.put(day, new ArrayList<TimeBlock>());
        this.weeklyAvailability.get(day).add(block);
    }
}

class TimeBlock {
    /*
    The time (inclusive) at which this TimeBlock begins.
     */
    private LocalTime startTime;
    /*
    The duration of this TimeBlock.
     */
    private Duration duration;

    /*
    Creates a new TimeBlock with a given startTime and duration.
     */
    public TimeBlock(LocalTime startTime, Duration duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    /*
    Creates a new TimeBlock with a given startTime and endTime. Throws RuntimeException if endTime
    is before startTime.
     */
    public TimeBlock(LocalTime startTime, LocalTime endTime) throws RuntimeException {
        this.startTime = startTime;
        if(!endTime.isBefore(startTime)) {
            throw new RuntimeException("endTime cannot be before startTime");
        }
        this.duration = Duration.between(startTime, endTime);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return this.duration;
    }
}
