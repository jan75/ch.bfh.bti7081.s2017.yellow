package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import com.vaadin.ui.*;

/**
 * Created by simon on 15.05.17.
 */
public class ContactDetailViewImpl extends CustomComponent implements ContactDetailView {

    TextField tfFirstName = new TextField();
    TextField tfLastName = new TextField();
    TextField tfPhoneNumber = new TextField();

    public ContactDetailViewImpl() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(new HorizontalLayout(new Label("Vorname"), tfFirstName));
        verticalLayout.addComponents(new HorizontalLayout(new Label("Nachname"), tfLastName));
        verticalLayout.addComponents(new HorizontalLayout(new Label("Telefonnummer"), tfPhoneNumber));
        setCompositionRoot(verticalLayout);
    }

    @Override
    public void setFirstName(String firstName) {
        tfFirstName.setData(firstName);
    }

    @Override
    public void setLastname(String lastName) {
        tfLastName.setData(lastName);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        tfPhoneNumber.setData(phoneNumber);
    }
}
