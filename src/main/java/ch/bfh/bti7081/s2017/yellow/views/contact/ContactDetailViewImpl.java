package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by simon on 15.05.17.
 */
public class ContactDetailViewImpl extends FormLayout implements ContactDetailView {
	State state;
	abstract class State {
		abstract void inputChanged();
	}
	class InvalidState extends State {

		public InvalidState() {
			//mark input field red
			System.out.println("Mark input field red");
		}
		
		@Override
		void inputChanged() {
			
			//validate input
			if(!firstName.equals("")) {
				state = new ValidState();
			}
		}
		
	}
	class ValidState extends State {
		
		public ValidState() {
			//mark input field green
			System.out.println("Mark input field green");
		}

		@Override
		void inputChanged() {
			if(firstName.equals("")) {
				state = new InvalidState();
			}
		}
		
	}
	
    private ContactDetailViewListener listener;
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField phoneNr = new TextField("Phone number");
    private Button btnSave = new Button("Save");
    private Button btnDelete = new Button("Delete");
    private Button btnCancel = new Button("Cancel");
    private BeanValidationBinder<ContactBookEntryBean> beanValidationBinder = new BeanValidationBinder(ContactBookEntryBean.class);

    public ContactDetailViewImpl() {
        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(btnSave, btnDelete, btnCancel);
        VerticalLayout textBoxes = new VerticalLayout(firstName, lastName, phoneNr);
        addComponents(textBoxes, buttons);

        beanValidationBinder.bindInstanceFields(this);

        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnDelete.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnCancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
       
        state = new InvalidState();
        firstName.addValueChangeListener(event->{
        	state.inputChanged();
        });
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
    @Override
    public void addListener(ContactDetailViewListener listener) {
        this.listener = listener;
        btnSave.addClickListener(event ->   listener.saveClicked());
        btnDelete.addClickListener(event -> listener.deleteClicked());
        btnCancel.addClickListener(event -> listener.cancelClicked());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        if (listener != null) {
            listener.changeView(viewChangeEvent);
        }
    }
}



