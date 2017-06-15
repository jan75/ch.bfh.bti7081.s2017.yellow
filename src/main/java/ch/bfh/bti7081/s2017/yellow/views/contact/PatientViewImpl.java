package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.*;
import com.vaadin.annotations.PropertyId;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Concrete PatientView implementation contains components to display data of one patient
 * @author iSorp
 */
public class PatientViewImpl extends ContactDetailViewImpl {

    // Components
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField phoneNr = new TextField("Phone number");
    private DateField checkInDate = new DateField("checkInDate");
    private DateField checkOutDate = new DateField("checkOutDate");

    // Validator
    private BeanValidationBinder<ContactBookEntryBean> beanValidationBinder = new BeanValidationBinder(ContactBookEntryBean.class);

    /**
     * Default ContactViewImpl PatientViewImpl.
     */
    public PatientViewImpl() {
        createLayout();
    }

    @Override
    protected void createLayout() {
        title = "Patient";
        VerticalLayout textFields = new VerticalLayout(firstName, lastName, phoneNr, checkInDate, checkOutDate);
        content.addComponent(textFields);

        beanValidationBinder.bindInstanceFields(this);
        //beanValidationBinder.bind(lastName, "person.lastName"); will be work in further vaadin releases - > see vaadin doc
        beanValidationBinder.bind(firstName, a->a.getPerson().getFirstName(), (a, b)->a.getPerson().setFirstName(b));
        beanValidationBinder.bind(lastName, a->a.getPerson().getLastName(), (a, b)->a.getPerson().setLastName(b));
        beanValidationBinder.bind(checkInDate, a->((PatientBean)a.getPerson()).getLdCheckInDate(), (a, b)->((PatientBean)a.getPerson()).setLdCheckInDate(b));
        beanValidationBinder.bind(checkOutDate, a->((PatientBean)a.getPerson()).getLdCheckOutDate(), (a, b)->((PatientBean)a.getPerson()).setLdCheckOutDate(b));

        firstName.setReadOnly(true);
        lastName.setReadOnly(true);
        checkInDate.setReadOnly(true);
        checkOutDate.setReadOnly(true);

        super.createLayout();
    }

    /**
     * Binds a contactBookEntryBean to the view and displays the data.
     * @param contactBookEntryBean
     */
    @Override
    public void setContact(ContactBookEntryBean contactBookEntryBean) {
        beanValidationBinder.setBean(contactBookEntryBean);
    }

    /**
     * Returns the current contactBookEntryBean
     * @return contactBookEntryBean
     */
    @Override
    public ContactBookEntryBean getContact() {
        return beanValidationBinder.getBean();
    }

    /**
     * Validates the content of all textFields
     * @return
     */
    @Override
    public boolean validate() {
        beanValidationBinder.validate();
        return beanValidationBinder.isValid();
    }
}



