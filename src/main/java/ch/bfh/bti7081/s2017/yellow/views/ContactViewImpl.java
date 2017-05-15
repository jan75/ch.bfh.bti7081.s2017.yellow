package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;

import java.util.List;

/**
 * Created by samuel on 30.04.17.
 */
public class ContactViewImpl extends CustomComponent implements ContactView {

    private Grid<Person> grid;

    public ContactViewImpl() {
        grid = new Grid<>();
        grid.addColumn(Person::getFirstName).setCaption("First name");
        grid.addColumn(Person::getLastName).setCaption("Last name");
        setCompositionRoot(grid);
    }

    @Override
    public void setContacts(List<Person> employees) {
        grid.setItems(employees);
    }
}
