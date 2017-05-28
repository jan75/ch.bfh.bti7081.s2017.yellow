package ch.bfh.bti7081.s2017.yellow.views.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jan on 28.05.2017.
 */
public class ScheduleEntryTest {
    private Date date;
    private List<String> scheduleDay = null;

    public ScheduleEntryTest(Date date) {
        this.date = date;
        scheduleDay = new ArrayList<String>();
        for(int i = 0; i < 24; i++) {
            scheduleDay.add("null");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(List<String> scheduleDay) {
        this.scheduleDay = scheduleDay;
    }
}
