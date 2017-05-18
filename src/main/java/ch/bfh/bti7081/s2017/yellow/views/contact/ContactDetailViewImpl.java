package ch.bfh.bti7081.s2017.yellow.views.contact;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by simon on 15.05.17.
 */
public class ContactDetailViewImpl extends FormLayout implements ContactDetailView {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField phoneNumber = new TextField("Phone number");
    private Button btnSave = new Button("Save");
    private Button btnDelete = new Button("Delete");
    private Button btnCancel = new Button("Cancel");
    private Binder<ContactBookEntryBean> binder = new Binder<>(ContactBookEntryBean.class);

    public ContactDetailViewImpl() {
        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(btnSave, btnDelete, btnCancel);
        VerticalLayout textBoxes = new VerticalLayout(firstName, lastName, phoneNumber);
        addComponents(textBoxes, buttons);

        binder.bindInstanceFields(this);

        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnDelete.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnCancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
    }

    @Override
    public void setContact(ContactBookEntryBean contactBookEntryBean) {
        binder.setBean(contactBookEntryBean);
    }

    @Override
    public ContactBookEntryBean getContact() {
        return new ContactBookEntryBean(firstName.getValue(), lastName.getValue(), phoneNumber.getValue());
    }

    @Override
    public void addListener(ContactDetailViewListener listener) {
        btnSave.addClickListener(event -> listener.save());
        btnDelete.addClickListener(event -> listener.delete());
        btnCancel.addClickListener(event -> listener.cancel());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
