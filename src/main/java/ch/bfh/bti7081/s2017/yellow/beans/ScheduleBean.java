package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;
import java.time.LocalDate;

import java.util.HashMap;

/**
 * Bean for usage in planning view and logic
 */
public class ScheduleBean extends BaseBean<Schedule> {
    private Long id;
    private HashMap<LocalDate, HashMap<Integer, String>> scheduleDayMap = new HashMap<>();

    /**
     * Creates a new empty ScheduleBean for usage with an EmployeeBean
     */
    public ScheduleBean () {

    }

    /**
     * Adds a schedule for a day into the scheduleDayMap. Returns false if there already is a Schedule for the chosen date, otherwise returns true
     * @param date the date for which to add a schedule entry
     * @return boolean returns true if the schedule entry could be added
     */
    public boolean addScheduleEntry(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            return false;
        } else {
            scheduleDayMap.put(date, initializeDaySchedule());
            return true;
        }
    }

    /**
     * This method initializes a schedule for a day, e.g. it fills the HashMap for the day with the keys 0 to 23 and the string values with ""
     * @return returns a HashMap with a schedule
     */
    private HashMap<Integer, String> initializeDaySchedule() {
        HashMap<Integer, String> dayMap = new HashMap<>();
        for(int i = 0; i <= 23; i++) {
            dayMap.put(i, "");
        }
        return dayMap;
    }

    /**
     * This method returns an entry for a specific day. If no entry exists for the chosen day null is returned
     * @param date the date for which to return the day schedule
     * @return returns a HashMap with the schedule for the chosen day
     */
    public HashMap<Integer, String> getEntryForDay(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            return scheduleDayMap.get(date);
        } else {
            return null;
        }
    }

    /**
     * This method sets a schedule for a specific date
     * @param date the date for which to set the day schedule
     * @param scheduleDay the day schedule which is to put into the schedule
     */
    public void setScheduleForDay(LocalDate date, HashMap<Integer, String> scheduleDay) {
        scheduleDayMap.put(date, scheduleDay);
    }

    /**
     * This method deletes a schedule for a specific date
     * @param date the date which is to delete from the schedule
     * @return returns true if schedule day could be deleted, returns false otherwise
     */
    public boolean deleteScheduleEntryDay(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            scheduleDayMap.remove(date);
            return true;
        } else {
            return false;
        }
    }

    public HashMap<LocalDate, HashMap<Integer, String>> getScheduleDayMap() {
        return scheduleDayMap;
    }

    public void setScheduleDayMap(HashMap<LocalDate, HashMap<Integer, String>> scheduleDayMap) {
        this.scheduleDayMap = scheduleDayMap;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
