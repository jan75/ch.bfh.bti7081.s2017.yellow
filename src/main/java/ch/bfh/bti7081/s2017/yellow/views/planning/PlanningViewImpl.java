package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;
import ch.bfh.bti7081.s2017.yellow.presenters.PlanningDetailPresenter;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.joda.time.LocalDate;

import java.util.HashMap;

/**
 * Concrete PlanningView implementation
 * @author iSorp
 */
public class PlanningViewImpl extends CustomComponent implements PlanningView {
    PlanningDetailView planningDetailView = new PlanningDetailViewImpl();
    PlanningDetailPresenter planningDetailPresenter= new PlanningDetailPresenter(planningDetailView);

    public PlanningViewImpl() {
        final VerticalLayout layout = new VerticalLayout();

        Employee tim = new Employee("Tim", "Gerber");
        Schedule scheduleTim = tim.getSchedule();
        scheduleTim.addScheduleEntry(new LocalDate().withDayOfMonth(2).withMonthOfYear(1).withYear(2017));
        scheduleTim.addScheduleEntry(new LocalDate().withDayOfMonth(3).withMonthOfYear(1).withYear(2017));
        scheduleTim.addScheduleEntry(new LocalDate().withDayOfMonth(4).withMonthOfYear(1).withYear(2017));

        HashMap<LocalDate, HashMap<Integer, String>> scheduleDaysMap = scheduleTim.getScheduleEntryTestList();

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

        layout.addComponent(drawScheduleDaysForEmployee(tim));
        setCompositionRoot(layout);
        setVisible(false);
    }

    private VerticalLayout drawScheduleDaysForEmployee(Employee employee) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label(employee.getFirstName() + " " + employee.getLastName()));

        Schedule schedule = employee.getSchedule();
        HashMap<LocalDate, HashMap<Integer, String>> scheduleMap = schedule.getScheduleDayMap();
        for(LocalDate date: scheduleMap.keySet()) {
            verticalLayout.addComponent(new Button(date.toString(), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    planningDetailPresenter.updateView(employee, date);
                    NavigatorController.getInstance().navigateTo("planningDetailView");
                }
            }));
        }

        return verticalLayout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setVisible(true);
    }

}
