package ch.bfh.bti7081.s2017.yellow.views.planning;

import org.joda.time.LocalDate;

import java.util.*;

/**
 * Created by jan on 28.05.2017.
 */
public class ScheduleEntryTest {
    private LocalDate date;
    private HashMap<Integer, String> scheduleDay;

    public ScheduleEntryTest(LocalDate date) {
        this.date = date;
        scheduleDay = new HashMap<>();
        for(int i = 1; i <= 24; i++) {
            scheduleDay.put(i, null);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public HashMap<Integer, String> getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(HashMap<Integer, String> scheduleDay) {
        this.scheduleDay = scheduleDay;
    }
}