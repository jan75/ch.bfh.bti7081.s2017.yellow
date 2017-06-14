package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;

import java.util.Date;

/**
 * Bean specific for usage in planning view and logic
 */
public class EmployeePlanningBean extends BaseBean<Employee> {
    private Date since;
    private ScheduleBean schedule;
    private String firstName;
    private String lastName;

    /**
     * Creates an EmployeePlanningBean for usage in the planning part of the application
     */
    public EmployeePlanningBean() {

    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public ScheduleBean getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleBean schedule) {
        this.schedule = schedule;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
