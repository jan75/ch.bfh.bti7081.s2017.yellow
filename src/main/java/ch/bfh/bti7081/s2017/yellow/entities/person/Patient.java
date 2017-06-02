package ch.bfh.bti7081.s2017.yellow.entities.person;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="PATIENT")
public class Patient extends Person implements Storable {

    @Column(name="CHECK_IN")
    private Date checkInDate;

    @Column(name="CHECK_OUT")
    private Date checkOutDate;

    @Column(name="REMAKRS")
    private String remakrs;

    public Patient() {
    }

    public Patient(String firstName, String lastName) {
        super(firstName, lastName);
    }

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

    public String getRemakrs() {
        return remakrs;
    }

    public void setRemakrs(String remakrs) {
        this.remakrs = remakrs;
    }
}
