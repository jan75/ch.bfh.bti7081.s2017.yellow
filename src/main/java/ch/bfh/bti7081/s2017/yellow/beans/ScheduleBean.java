package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;
import org.joda.time.LocalDate;

import java.util.HashMap;

public class ScheduleBean extends BaseBean<Schedule> {
    private Long id;
    private HashMap<LocalDate, HashMap<Integer, String>> scheduleDayMap;

    public ScheduleBean () {
        scheduleDayMap = new HashMap<>();
    }

    /**
     * Adds a schedule for a day into the scheduleDayMap. Returns false if there already is a Schedule for the chosen date, otherwise returns true
     * @param date
     * @return boolean
     */
    public boolean addScheduleEntry(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            return false;
        } else {
            scheduleDayMap.put(date, initializeDaySchedule());
            return true;
        }
    }

    private HashMap<Integer, String> initializeDaySchedule() {
        HashMap<Integer, String> dayMap = new HashMap<>();
        for(int i = 0; i <= 23; i++) {
            dayMap.put(i, null);
        }
        return dayMap;
    }

    public HashMap<Integer, String> getEntryForDay(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            return scheduleDayMap.get(date);
        } else {
            return null;
        }
    }

    public void setScheduleForDay(LocalDate date, HashMap<Integer, String> scheduleDay) {
        scheduleDayMap.put(date, scheduleDay);
    }

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
