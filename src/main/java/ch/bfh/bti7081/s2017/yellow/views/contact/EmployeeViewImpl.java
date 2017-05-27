package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.beans.ContactEntryType;
import ch.bfh.bti7081.s2017.yellow.beans.EmployeeBean;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by simon on 15.05.17.
 */
public class EmployeeViewImpl extends ContactDetailViewImpl<EmployeeBean> {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField phoneNr = new TextField("Phone number");
    private DateField since = new DateField("Since");

    private BeanValidationBinder<ContactBookEntryBean<EmployeeBean>> beanValidationBinder = new BeanValidationBinder(ContactBookEntryBean.class);

    public EmployeeViewImpl() {
        createLayout();
    }

    @Override
    protected void createLayout() {
        setSizeUndefined();
        VerticalLayout textBoxes = new VerticalLayout(firstName, lastName, phoneNr, since);
        addComponents(textBoxes);

        beanValidationBinder.bindInstanceFields(this);
        super.createLayout();
    }

    @Override
    public void setContact(ContactBookEntryBean<EmployeeBean> contactBookEntryBean) {
        beanValidationBinder.setBean(contactBookEntryBean);
    }

    @Override
    public ContactBookEntryBean<EmployeeBean> getContact() {
        return beanValidationBinder.getBean();
    }

    @Override
    public boolean validate() {
        beanValidationBinder.validate();
        return beanValidationBinder.isValid();
    }

}



