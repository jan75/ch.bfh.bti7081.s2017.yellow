package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;

import java.time.LocalDate;
import java.util.List;

/**
 * This service supplies the planning presenter with db logic
 */
public class PlanningService extends SimpleServiceImpl<Employee, EmployeePlanningBean>  {
    private static boolean init = false;

    /**
     * Creates an instance of PlanningService and fills it with some dummy entries
     */
    public PlanningService() {
        super(Employee.class, EmployeePlanningBean.class, new DbConnector());

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
