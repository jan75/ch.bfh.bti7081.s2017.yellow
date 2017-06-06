package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.presenters.PlanningDetailPresenter;
import ch.bfh.bti7081.s2017.yellow.services.PlanningService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.List;

/**
 * Concrete PlanningView implementation
 * @author iSorp
 */
public class PlanningViewImpl extends CustomComponent implements PlanningView {
    PlanningDetailView planningDetailView = new PlanningDetailViewImpl();
    PlanningDetailPresenter planningDetailPresenter= new PlanningDetailPresenter(planningDetailView);

    public PlanningViewImpl() {
        final VerticalLayout layout = new VerticalLayout();

        PlanningService planningService = new PlanningService();
        List<EmployeePlanningBean> employeePlanningBeanList = planningService.getEmployees();
        for(EmployeePlanningBean employeePlanningBean: employeePlanningBeanList) {
            layout.addComponent(drawScheduleDaysForEmployee(employeePlanningBean));
        }
        /*
        ScheduleBean scheduleTim = tim.getSchedule();
        scheduleTim.addScheduleEntry(new LocalDate().withDayOfMonth(2).withMonthOfYear(1).withYear(2017));
        scheduleTim.addScheduleEntry(new LocalDate().withDayOfMonth(3).withMonthOfYear(1).withYear(2017));
        scheduleTim.addScheduleEntry(new LocalDate().withDayOfMonth(4).withMonthOfYear(1).withYear(2017));

        HashMap<LocalDate, HashMap<Integer, String>> scheduleDaysMap = scheduleTim.getScheduleDayMap();

        LocalDate date = new LocalDate().withDayOfMonth(1).withMonthOfYear(1).withYear(2017);
        if(scheduleTim.addScheduleEntry(date)) {
            //layout.addComponent(new Label("Success"));
        } else {
            //layout.addComponent(new Label("Failure"));
        }
        HashMap<Integer, String> scheduleDay = scheduleTim.getEntryForDay(date);

        int i;
        for(i = 8; i <= 11; i++) {
            scheduleDay.put(i, "Pflege Tom");
        }

        for(i = 14; i <= 17; i++) {
            scheduleDay.put(i, "Pflege Michael");
        }

        scheduleTim.setScheduleForDay(date, scheduleDay);
        tim.setSchedule(scheduleTim);
        */
        setCompositionRoot(layout);
        setVisible(false);
    }

    private HorizontalLayout drawScheduleDaysForEmployee(EmployeePlanningBean employee) {
        ScheduleBean schedule = employee.getSchedule();
        LocalDate dateTime = new LocalDate();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(new Label(employee.getFirstName() + " " + employee.getLastName()));

        DateField dateField = new DateField();
        horizontalLayout.addComponent(dateField);

        Button addSchedule = new Button("Add Schedule", (Button.ClickListener) clickEvent -> {
            String date = dateField.getValue().toString();
            dateTime.parse(date);
            //System.out.println(dateTime);
            schedule.addScheduleEntry(dateTime);

            employee.setSchedule(schedule);
            planningDetailPresenter.updateView(employee, dateTime);
            NavigatorController.getInstance().navigateTo("planningDetailView");
        });
        horizontalLayout.addComponent(addSchedule);

        HashMap<LocalDate, HashMap<Integer, String>> scheduleMap = schedule.getScheduleDayMap();
        for(LocalDate date: scheduleMap.keySet()) {
            horizontalLayout.addComponent(new Button(date.toString(), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    planningDetailPresenter.updateView(employee, date);
                    NavigatorController.getInstance().navigateTo("planningDetailView");
                }
            }));
        }

        return horizontalLayout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setVisible(true);
    }

}
