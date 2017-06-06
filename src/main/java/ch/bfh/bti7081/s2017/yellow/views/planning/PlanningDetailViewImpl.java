package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.joda.time.LocalDate;

import java.util.HashMap;

/**
 * Concrete PlanningView implementation
 * @author iSorp
 */
public class PlanningDetailViewImpl extends CustomComponent implements PlanningDetailView {
    private EmployeePlanningBean employee = null;
    private ScheduleBean schedule = null;
    private LocalDate date = null;
    private VerticalLayout layout = new VerticalLayout();

    public PlanningDetailViewImpl() {
        layout.addComponent(new Label("planningDetailView"));
        layout = new VerticalLayout();

        setCompositionRoot(layout);

        setVisible(false);
    }

    @Override
    public void updateView(EmployeePlanningBean employee, LocalDate date) {
        this.employee = employee;
        this.date = date;
        //this.schedule = schedule;

        layout.removeAllComponents();
        layout.addComponent(drawDaySchedule());
    }

    private VerticalLayout drawDaySchedule() {
        VerticalLayout verticalLayout = new VerticalLayout();

        Label name = new Label(employee.getFirstName() + " " + employee.getLastName());
        verticalLayout.addComponent(name);

        HashMap<Integer, String> scheduleDay = employee.getSchedule().getEntryForDay(date);

        String tmpString = scheduleDay.get(0);
        String tmpBeginTime = timeStringBuilder(0);
        String tmpEndTime;
        for(int i = 0; i < scheduleDay.size(); i++) {
            if(scheduleDay.get(i) == tmpString) {
                continue;
            } else {
                tmpEndTime = timeStringBuilder(i);
                if(tmpString != null) {
                    verticalLayout.addComponent(new Label(tmpBeginTime + " - " + tmpEndTime + "   " + tmpString));
                }
                tmpBeginTime = timeStringBuilder(i);
                tmpString = scheduleDay.get(i);
            }
        }
        if(tmpString != null) {
            verticalLayout.addComponent(new Label(tmpBeginTime + " - 00:00   " + tmpString));
        }

        return verticalLayout;
    }

    private String timeStringBuilder(Integer timeShort) {
        StringBuilder stringBuilder = new StringBuilder();
        if(timeShort < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(timeShort + ":00");
        return new String(stringBuilder);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setVisible(true);
    }
}
