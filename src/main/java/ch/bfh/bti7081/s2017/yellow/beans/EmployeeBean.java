package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import org.hibernate.validator.constraints.NotEmpty;

import java.beans.Transient;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
/**
 * Created by simon on 23.05.17.
 */
public class EmployeeBean extends PersonBean {

    @NotEmpty
    private Date since;

    private ScheduleBean schedule;

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public LocalDate getLdSince() {
        return since.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setLdSince(LocalDate since) {
        this.since = Date.from(since.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public ScheduleBean getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleBean schedule) {
        this.schedule = schedule;
    }
}

