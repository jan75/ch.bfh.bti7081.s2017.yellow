package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.TextField;
import java.util.List;
import java.util.Set;

/**
 * Concrete ContactView implementation displays a list of Contacts and a detailed contact view.
 * @author iSorp
 */
public class ContactViewImpl extends CustomComponent implements ContactView {

    private ContactViewListener listener;
    private ContactDetailView contactDetailView = new ContactDetailViewImpl();
    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private VerticalLayout verticalLayout = new VerticalLayout();
    private Grid<ContactBookEntry> grid = new Grid<>();
    private TextField textFieldFilter = new TextField();

    public ContactViewImpl() {

        // Grid for all contact entries
        grid.addColumn((a) -> a.getPerson().getFirstName()).setCaption("First name");
        grid.addColumn((a) -> a.getPerson().getLastName()).setCaption("Last name");
        grid.addColumn(ContactBookEntry::getPhoneNr).setCaption("Telephone number");


        // TextField valueChangedListener for a filter attribute
        textFieldFilter.addValueChangeListener(valueChangeEvent -> listener.setFilter(valueChangeEvent.getValue()));

        // Add components to the layout
        verticalLayout.addComponent(textFieldFilter);
        verticalLayout.addComponent(grid);
        horizontalLayout.addComponent(verticalLayout);
        horizontalLayout.addComponent(contactDetailView);

        setCompositionRoot(horizontalLayout);
    }

    @Override
    public void setContacts(List<ContactBookEntry> contact) {
        grid.setItems(contact);
    }

    @Override
    public Set<ContactBookEntry> getSelectedContactBookEntry() {
        return grid.getSelectedItems();
    }

    @Override
    public ContactDetailView getContactDetailView() {
        return contactDetailView;
    }

    @Override
    public void addListener(ContactViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listener.changeView(viewChangeEvent);
    }

}
