package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import com.vaadin.annotations.PropertyId;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

/**
 * Created by simon on 21.05.17.
 */
public class PersonBean extends BaseBean<Person> {

    @NotBlank
    private String firstName = "";
    @NotBlank
    private String lastName = "";

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
