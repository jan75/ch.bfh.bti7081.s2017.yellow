package ch.bfh.bti7081.s2017.yellow.views;

import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

import java.util.ArrayList;

/**
 * Container for DashboardItems, Vertically layouted.
 * @author iSorp
 */
public class ContactDashboardViewImpl extends HorizontalLayout implements ContactDashboardView {

    class Contact{
        String prename, surname, phone, email;

        Contact(String prename, String surname) {
            this.prename = prename;
            this.surname = surname;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getPrename() {
            return prename;
        }

        public String getSurname() {
            return surname;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setPrename(String prename) {
            this.prename = prename;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
    }

    public ContactDashboardViewImpl() {
        ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
        Contact contact1 = new Contact("Tim", "Fischer");
        Contact contact2 = new Contact("Lukas", "Spring");
        contact2.setEmail("lukas@gmail.com");
        contact2.setPhone("+41 (0)31 824 43 21");
        Contact contact3 = new Contact("Tom", "Fuller");
        contact3.setEmail("tommy@yahoo.com");
        Contact contact4 = new Contact("Lars", "Ulrich");
        contact4.setEmail("ulrich@bluewin.ch");
        contact4.setPhone("031 473 24 32");
        Contact contact5 = new Contact("Esther", "Schulz");
        contact5.setEmail("esthi@blabla.ch");
        contact5.setPhone("0041 79 843 23 43");

        contactArrayList.add(contact1);
        contactArrayList.add(contact2);
        contactArrayList.add(contact3);
        contactArrayList.add(contact4);
        contactArrayList.add(contact5);

        Grid<Contact> grid = new Grid<>();
        grid.setItems(contactArrayList);
        grid.asSingleSelect();
        grid.addColumn(Contact::getPrename).setCaption("Prename");
        grid.addColumn(Contact::getSurname).setCaption("Surname");
        grid.addColumn(Contact::getEmail).setCaption("Email");
        grid.addColumn(Contact::getPhone).setCaption("Phone");
        
        addComponent(grid);
    }
}
