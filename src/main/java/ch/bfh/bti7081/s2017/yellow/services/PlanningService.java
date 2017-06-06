package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.*;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import com.vaadin.data.provider.DataProvider;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by simon on 15.05.17.
 */
public class PlanningService extends SimpleServiceImpl<Employee, EmployeePlanningBean>  {

    //public List<ContactBookEntryBean> contactList = new ArrayList<>();
    private static boolean init = false;

    public PlanningService() {
        super(Employee.class, EmployeePlanningBean.class);

        if (init == false) {
            EmployeePlanningBean tim = new EmployeePlanningBean();
            tim.setFirstName("Time");
            tim.setLastName("Schmid");

            EmployeePlanningBean fred = new EmployeePlanningBean();
            fred.setFirstName("Fred");
            fred.setLastName("Feuerstein");

            saveEntity(tim);
            saveEntity(fred);
            init = true;
        }
    }

    /**
     * Returns a list of EmployeePlanningBeans
     * @return list of EmployeePlanningBean
     */
    public List<EmployeePlanningBean> getEmployees(){
        return getALlEntities().stream().collect(Collectors.toList());
    }
}
