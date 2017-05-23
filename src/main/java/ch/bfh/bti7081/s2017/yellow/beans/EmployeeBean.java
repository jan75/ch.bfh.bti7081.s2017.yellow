package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;

import javax.persistence.Column;
import java.time.LocalDate;
/**
 * Created by simon on 23.05.17.
 */
public class EmployeeBean extends PersonBean {

    private LocalDate since;

    public LocalDate getSince() {
        return since;
    }

    public void setSince(LocalDate since) {
        this.since = since;
    }
}

