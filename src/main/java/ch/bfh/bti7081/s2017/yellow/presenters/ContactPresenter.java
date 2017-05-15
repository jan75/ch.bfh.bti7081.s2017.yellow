package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.services.SimpleService;
import ch.bfh.bti7081.s2017.yellow.views.ContactView;

/**
 * Created by samuel on 30.04.17.
 */
public class ContactPresenter {

    private ContactView view;

    private SimpleService<Person> service;

    public ContactPresenter(ContactView view, SimpleService<Person> service) {
        this.view = view;
        this.service = service;

        view.setContacts(service.getALlEntities());
    }

}
