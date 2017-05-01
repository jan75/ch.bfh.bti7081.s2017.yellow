package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.entities.Employee;

import java.util.List;

/**
 * Created by samuel on 30.04.17.
 */
public interface ContactView {

    void setContacts(List<Employee> employees);
}
