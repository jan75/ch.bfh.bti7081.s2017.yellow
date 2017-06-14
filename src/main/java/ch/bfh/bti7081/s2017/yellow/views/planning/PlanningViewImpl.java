package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.presenters.PlanningDetailPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.PlanningPresenter;
import ch.bfh.bti7081.s2017.yellow.services.PlanningService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * Concrete PlanningView implementation
 * @author Jan Ackermann
 */
public class PlanningViewImpl extends CustomComponent implements PlanningView {
    PlanningDetailView planningDetailView = new PlanningDetailViewImpl();
    PlanningDetailPresenter planningDetailPresenter = new PlanningDetailPresenter(planningDetailView);
    PlanningPresenter planningPresenter = new PlanningPresenter(this);
    PlanningService planningService;
    VerticalLayout displayEmployeesLayout = new VerticalLayout();

    /**
     * The PlanningView constructor creates static and dynamic (via presenter) components
     */
    public PlanningViewImpl() {
        final VerticalLayout layout = new VerticalLayout();
        HorizontalLayout addEmployeesLayout = new HorizontalLayout();
        displayEmployeesLayout = new VerticalLayout();
        planningService = new PlanningService();

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        firstName.setPlaceholder("First Name");
        lastName.setPlaceholder("Last Name");
        Button addEmployee = new Button("Add Employee", (Button.ClickListener) clickEvent -> {
            planningPresenter.addEmployee(firstName.getValue(), lastName.getValue());
        });
        addEmployeesLayout.addComponents(firstName, lastName, addEmployee);

        planningPresenter.loadEmployees();

        layout.addComponent(addEmployeesLayout);
        layout.addComponent(displayEmployeesLayout);
        setCompositionRoot(layout);
        setVisible(false);
    }

    /**
     * @param employeePlanningBeanList The list of employees to load
     */
    public void loadEmployees(List<EmployeePlanningBean> employeePlanningBeanList) {
        displayEmployeesLayout.removeAllComponents();
        for(EmployeePlanningBean employeePlanningBean: employeePlanningBeanList) {
            displayEmployeesLayout.addComponent(drawScheduleDaysForEmployee(employeePlanningBean));
        }
    }

    /**
     * This method returns a view with information and a user input form for an employee
     * @param employee The employee which to draw
     * @return Returns a layout which gets displayed on the view
     */
    private HorizontalLayout drawScheduleDaysForEmployee(EmployeePlanningBean employee) {
        ScheduleBean schedule = employee.getSchedule();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(new Label(employee.getFirstName() + " " + employee.getLastName()));

        DateField dateField = new DateField();
        horizontalLayout.addComponent(dateField);

        Button addSchedule = new Button("Add Schedule", (Button.ClickListener) clickEvent -> {
            planningPresenter.addSchedule(employee, dateField.getValue(), planningDetailPresenter);

        });
        horizontalLayout.addComponent(addSchedule);

        HashMap<LocalDate, HashMap<Integer, String>> scheduleMap = schedule.getScheduleDayMap();
        ComboBox<LocalDate> schedulesEmployee = new ComboBox<>();
        schedulesEmployee.setItems(scheduleMap.keySet());
        if(scheduleMap.keySet().size() > 0) {
            horizontalLayout.addComponent(schedulesEmployee);
            horizontalLayout.addComponent(new Button("Go", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    LocalDate date = schedulesEmployee.getValue();
                    if (date != null) {
                        planningDetailPresenter.updateView(employee, date);
                        NavigatorController.getInstance().navigateTo("planningDetailView");
                    }
                }
            }));
        }

        return horizontalLayout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setVisible(true);
        planningPresenter.loadEmployees();
    }
}
