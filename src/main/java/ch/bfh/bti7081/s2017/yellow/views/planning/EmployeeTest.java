package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;

/**
 * Created by jan on 28.05.2017.
 */
public class EmployeeTest {
    private String firstName, lastName;

    private ScheduleTest schedule;

    public EmployeeTest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName =  lastName;
        schedule = new ScheduleTest("Simon");
    }

    public ScheduleTest getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleTest schedule) {
        this.schedule = schedule;
    }
}
