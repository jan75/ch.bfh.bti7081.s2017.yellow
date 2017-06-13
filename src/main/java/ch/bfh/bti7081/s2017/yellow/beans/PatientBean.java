package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by simon on 23.05.17.
 */
public class PatientBean extends PersonBean implements Comparable<PatientBean> {

    @NotEmpty
    private Date checkInDate;

    @NotEmpty
    private Date checkOutDate;

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getLdCheckInDate() {
        return checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setLdCheckInDate(LocalDate checkInDate) {
        this.checkInDate = Date.from(checkInDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public LocalDate getLdCheckOutDate() {
        return checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setLdCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = Date.from(checkOutDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public int compareTo(PatientBean o) {
        if (getFirstName().equals(o.getFirstName()) && getLastName().equals(o.getLastName())) {
            return 0;
        }
        return -1;
    }
}
