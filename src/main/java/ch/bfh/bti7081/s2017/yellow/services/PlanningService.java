package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

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
            ScheduleBean scheduleTim = new ScheduleBean();
            scheduleTim.addScheduleEntry(LocalDate.now());
            tim.setSchedule(scheduleTim);

            EmployeePlanningBean fred = new EmployeePlanningBean();
            fred.setFirstName("Fred");
            fred.setLastName("Feuerstein");
            ScheduleBean scheduleFred = new ScheduleBean();
            scheduleFred.addScheduleEntry(LocalDate.now());
            fred.setSchedule(scheduleFred);

            saveEntity(tim);
            saveEntity(fred);
            init = true;
        }
    }

    /**
     * Returns a list of EmployeePlanningBeans
     * @return list of EmployeePlanningBean
     */
    public List<EmployeePlanningBean> getEmployees() {
        return getALlEntities();
    }
}
