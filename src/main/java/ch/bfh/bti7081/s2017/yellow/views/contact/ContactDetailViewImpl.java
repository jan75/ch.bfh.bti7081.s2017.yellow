package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.beans.ContactEntryType;
import ch.bfh.bti7081.s2017.yellow.beans.PersonBean;
import com.sun.org.apache.xml.internal.utils.DefaultErrorHandler;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValueProvider;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Concrete ContactDetailViewImpl implementation contains components for all detail views.
 * @author iSorp
 */
public abstract class ContactDetailViewImpl extends FormLayout implements ContactDetailView {

    protected ContactDetailViewListener listener;
    protected Button btnSave = new Button("Save");
    protected Button btnCancel = new Button("Cancel");

    protected void createLayout() {
        setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout(btnSave, btnCancel);
        addComponents(buttons);

        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnCancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
    }

    @Override
    public void addListener(ContactDetailViewListener listener) {
        this.listener = listener;
        btnSave.addClickListener(event ->   listener.saveClicked());
        btnCancel.addClickListener(event -> listener.cancelClicked());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        if (listener != null) {
            listener.changeView(viewChangeEvent);
        }
    }

    public abstract void setContact(ContactBookEntryBean  contactBookEntryBean);


    public abstract ContactBookEntryBean getContact();

}



