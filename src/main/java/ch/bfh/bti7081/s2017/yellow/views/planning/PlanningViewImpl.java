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
 * @author iSorp
 */
public class PlanningViewImpl extends CustomComponent implements PlanningView {
    PlanningDetailView planningDetailView = new PlanningDetailViewImpl();
    PlanningDetailPresenter planningDetailPresenter= new PlanningDetailPresenter(planningDetailView);
    PlanningPresenter planningPresenter = new PlanningPresenter(this);
    PlanningService planningService;
    VerticalLayout displayEmployeesLayout = new VerticalLayout();

    // LocalDate from Java 8

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

    public void loadEmployees(List<EmployeePlanningBean> employeePlanningBeanList) {
        displayEmployeesLayout.removeAllComponents();
        for(EmployeePlanningBean employeePlanningBean: employeePlanningBeanList) {
            displayEmployeesLayout.addComponent(drawScheduleDaysForEmployee(employeePlanningBean));
        }
    }

    private HorizontalLayout drawScheduleDaysForEmployee(EmployeePlanningBean employee) {
        ScheduleBean schedule = employee.getSchedule();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(new Label(employee.getFirstName() + " " + employee.getLastName()));

        DateField dateField = new DateField();
        horizontalLayout.addComponent(dateField);

        Button addSchedule = new Button("Add Schedule", (Button.ClickListener) clickEvent -> {
            String date = dateField.getValue().toString();
            LocalDate dateTime = LocalDate.parse(date);
            //System.out.println(dateTime);
            schedule.addScheduleEntry(dateTime);
            employee.setSchedule(schedule);
            planningService.saveEntity(employee);

            planningDetailPresenter.updateView(employee, dateTime);
            NavigatorController.getInstance().navigateTo("planningDetailView");
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

    @Override
    public void addEmployee(String firstName, String lastName) {

    }
}
