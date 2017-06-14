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
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Concrete ContactDetailViewImpl implementation contains components for all detail views.
 * @author iSorp
 */
public abstract class ContactDetailViewImpl extends CustomComponent implements ContactDetailView {

    protected ContactDetailViewListener listener;
    protected Button btnSave = new Button("Save");
    protected Button btnCancel = new Button("Cancel");
    protected VerticalLayout content = new VerticalLayout();
    protected String title = "";
    /**
     * Initialize the components for the layout
     */
    protected void createLayout() {
        HorizontalLayout buttons = new HorizontalLayout(btnSave, btnCancel);

        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnCancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);

        content.addComponents(buttons);
        content.setComponentAlignment(buttons, Alignment.BOTTOM_CENTER);
        content.setWidth(null);
        setCompositionRoot(content);
    }

    /**
     * Wires a listener to the view
     * @param listener
     */
    @Override
    public void addListener(ContactDetailViewListener listener) {
        this.listener = listener;
        btnSave.addClickListener(event ->   listener.saveClicked());
        btnCancel.addClickListener(event -> listener.cancelClicked());
    }

    /**
     * This Method is called when the view navigator calls this view and
     * @param viewChangeEvent
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Page.getCurrent().setTitle(title);
        if (listener != null) {
            listener.changeView(viewChangeEvent);
        }
    }

    /**
     * Wires a ContactBookEntry to the views component
     * @param contactBookEntryBean
     */
    public abstract void setContact(ContactBookEntryBean  contactBookEntryBean);

    /**
     * @return ContactBookEntryBean
     */
    public abstract ContactBookEntryBean getContact();

}



