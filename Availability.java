import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;

enum DayOfWeek {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday;

    public static DayOfWeek fromDayName(String dayName) throws IllegalArgumentException {
        switch(dayName.toLowerCase()) {
            case "monday":
                return DayOfWeek.Monday;
            case "tuesday":
                return DayOfWeek.Tuesday;
            case "wednesday":
                return DayOfWeek.Wednesday;
            case "thursday":
                return DayOfWeek.Thursday;
            case "friday":
                return DayOfWeek.Friday;
            default:
                throw new IllegalArgumentException(String.format("Unrecognized day of week '%s'", dayName));
        }
    }
}

public class Availability {
    private HashMap<DayOfWeek, ArrayList<TimeBlock>> weeklyAvailability = new HashMap<DayOfWeek, ArrayList<TimeBlock>>();

    public Availability() {
    }

    public ArrayList<TimeBlockAndDay> findOverlaps(Availability other) {
        ArrayList<TimeBlockAndDay> out = new ArrayList<>();

        for(DayOfWeek d : DayOfWeek.values()) {
            for(TimeBlock otherBlock : other.getAvailabilityForDay(d)) {
                for(TimeBlock thisBlock : this.getAvailabilityForDay(d)) {
                    // check if overlap exists between these two blocks
                    TimeBlock overlap = thisBlock.overlapWith(otherBlock);
                    if(overlap != null) out.add(new TimeBlockAndDay(overlap, d));
                }
            }
        }

        return out;
    }

    public ArrayList<TimeBlock> findOverlaps(TimeBlockAndDay when) {
        ArrayList<TimeBlock> out = new ArrayList<>();
        ArrayList<TimeBlock> availabilityForDay = this.getAvailabilityForDay(when.getDay());
        for(TimeBlock block : availabilityForDay) {
            TimeBlock overlap = block.overlapWith(when.getTimeBlock());
            if(overlap != null) out.add(overlap);
        }
        return out;
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

/*
Helper class to represent a TimeBlock paired with a given DayOfWeek.
 */
class TimeBlockAndDay {
    private TimeBlock timeBlock;
    private DayOfWeek day;

    public TimeBlockAndDay(TimeBlock timeBlock, DayOfWeek dayOfWeek) {
        this.timeBlock = timeBlock;
        this.day = dayOfWeek;
    }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    public DayOfWeek getDay() {
        return day;
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

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof TimeBlock) && ((TimeBlock)obj).getStartTime().equals(this.getStartTime()) && ((TimeBlock)obj).getEndTime().equals(this.getEndTime());
    }

    public TimeBlock overlapWith(TimeBlock other) {
        if(this.equals(other)) return new TimeBlock(this.getStartTime(), this.getEndTime());

        boolean otherStartsWithin = (other.getStartTime().isAfter(this.getStartTime()) && other.getStartTime().isBefore(this.getEndTime()));
        boolean thisStartsWithin = (this.getStartTime().isAfter(other.getStartTime()) && this.getStartTime().isBefore(other.getEndTime()));
        if (otherStartsWithin || thisStartsWithin) {
            LocalTime overlapStartTime;
            LocalTime overlapEndTime;
            if (otherStartsWithin) {
                overlapStartTime = other.getStartTime();
            } else {
                overlapStartTime = this.getStartTime();
            }
            if (other.getEndTime().isBefore(this.getEndTime())) {
                overlapEndTime = other.getEndTime();
            } else {
                overlapEndTime = this.getEndTime();
            }

            return new TimeBlock(overlapStartTime, overlapEndTime);
        }
        return null;
    }

    /*
    Creates a new TimeBlock with a given startTime and endTime. Throws RuntimeException if endTime
    is before startTime.
     */
    public TimeBlock(LocalTime startTime, LocalTime endTime) throws RuntimeException {
        this.startTime = startTime;
        if(endTime.isBefore(startTime)) {
            throw new RuntimeException("endTime cannot be before startTime");
        }
        this.duration = Duration.between(startTime, endTime);
    }

    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return startTime.plus(this.duration);
    }

    public Duration getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return this.getStartTime().format(DateTimeFormatter.ofPattern("hh:mma")) + "-" + this.getEndTime().format(DateTimeFormatter.ofPattern("hh:mma"));
    }
}
