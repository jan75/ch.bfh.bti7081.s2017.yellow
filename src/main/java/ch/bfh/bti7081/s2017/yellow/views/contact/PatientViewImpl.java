package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.*;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by simon on 15.05.17.
 */
public class PatientViewImpl extends ContactDetailViewImpl {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField phoneNr = new TextField("Phone number");
    private DateField checkInDate = new DateField("checkInDate");
    private DateField checkOutDate = new DateField("checkOutDate");

    private BeanValidationBinder<ContactBookEntryBean> beanValidationBinder = new BeanValidationBinder(ContactBookEntryBean.class);

    public PatientViewImpl() {
        createLayout();
    }

    @Override
    protected void createLayout() {
        VerticalLayout textBoxes = new VerticalLayout(firstName, lastName, phoneNr, checkInDate, checkOutDate);
        addComponents(textBoxes);

        beanValidationBinder.bindInstanceFields(this);
        beanValidationBinder.bind(firstName, a->a.getPerson().getFirstName(), (a, b)->a.getPerson().setFirstName(b));
        beanValidationBinder.bind(lastName, a->a.getPerson().getLastName(), (a, b)->a.getPerson().setLastName(b));
        beanValidationBinder.bind(checkInDate, a->((PatientBean)a.getPerson()).getLdCheckInDate(), (a, b)->((PatientBean)a.getPerson()).setLdCheckInDate(b));
        beanValidationBinder.bind(checkOutDate, a->((PatientBean)a.getPerson()).getLdCheckOutDate(), (a, b)->((PatientBean)a.getPerson()).setLdCheckOutDate(b));

        super.createLayout();
    }

    @Override
    public void setContact(ContactBookEntryBean contactBookEntryBean) {
        beanValidationBinder.setBean(contactBookEntryBean);
    }

    @Override
    public ContactBookEntryBean getContact() {
        return beanValidationBinder.getBean();
    }

    @Override
    public boolean validate() {
        beanValidationBinder.validate();
        return beanValidationBinder.isValid();
    }

}



