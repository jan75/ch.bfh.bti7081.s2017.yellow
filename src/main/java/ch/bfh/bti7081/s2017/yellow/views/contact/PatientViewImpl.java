package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.beans.ContactEntryType;
import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;
import ch.bfh.bti7081.s2017.yellow.beans.PersonBean;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by simon on 15.05.17.
 */
public class PatientViewImpl extends ContactDetailViewImpl<PatientBean> {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField phoneNr = new TextField("Phone number");
    private DateField checkInDate = new DateField("checkInDate");
    private DateField checkOutDate = new DateField("checkOutDate");

    private BeanValidationBinder<ContactBookEntryBean<PatientBean>> beanValidationBinder = new BeanValidationBinder(ContactBookEntryBean.class);

    public PatientViewImpl() {
        createLayout();
    }

    @Override
    protected void createLayout() {
        setSizeUndefined();
        VerticalLayout textBoxes = new VerticalLayout(firstName, lastName, phoneNr, checkInDate, checkOutDate);
        addComponents(textBoxes);

        beanValidationBinder.bindInstanceFields(this);
        super.createLayout();
    }

    @Override
    public void setContact(ContactBookEntryBean<PatientBean> contactBookEntryBean) {
        beanValidationBinder.setBean(contactBookEntryBean);
    }

    @Override
    public ContactBookEntryBean<PatientBean> getContact() {
        return beanValidationBinder.getBean();
    }

    @Override
    public boolean validate() {
        beanValidationBinder.validate();
        return beanValidationBinder.isValid();
    }

}



