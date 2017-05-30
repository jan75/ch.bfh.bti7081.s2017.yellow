package ch.bfh.bti7081.s2017.yellow.views.planning;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.joda.time.LocalDate;

import java.util.HashMap;

/**
 * Concrete PlanningView implementation
 * @author iSorp
 */
public class PlanningViewImpl extends CustomComponent implements PlanningView {

    public PlanningViewImpl() {
        final VerticalLayout layout = new VerticalLayout();

        EmployeeTest employee = new EmployeeTest("Tim", "Gerber");
        ScheduleTest schedule = employee.getSchedule();
        HashMap<LocalDate, ScheduleEntryTest> scheduleEntryList = schedule.getScheduleEntryTestList();

        LocalDate date = new LocalDate().withDayOfMonth(1).withMonthOfYear(1).withYear(2017);
        //layout.addComponent(new Label(date.toString()));

        if(schedule.addScheduleEntry(date)) {
            layout.addComponent(new Label("Success"));
        } else {
            layout.addComponent(new Label("Failure"));
        }
        ScheduleEntryTest scheduleEntryTest = schedule.getEntryForDay(date);
        HashMap<Integer, String> scheduleDay = scheduleEntryTest.getScheduleDay();

        int i;
        for(i = 8; i <= 11; i++) {
            scheduleDay.put(i, "Pflege Tom");
        }

        for(i = 12; i <= 13; i++) {
            scheduleDay.put(i, "Pause");
        }

        for(i = 14; i <= 17; i++) {
            scheduleDay.put(i, "Pflege Michael");
        }

        scheduleEntryTest.setScheduleDay(scheduleDay);
        schedule.setEntryForDay(date, scheduleEntryTest);

        layout.addComponent(drawEmployee(employee, date));

        /*
        for(i = 0; i < 24; i++) {
            layout.addComponent(new Label(i + " " + scheduleDay.get(i)));
        }
        */

        //layout.addComponent(horizontalLayout);
        setCompositionRoot(layout);
        setVisible(false);
    }

    private HorizontalLayout drawEmployee(EmployeeTest employee, LocalDate date) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticalLayout verticalLayout = new VerticalLayout();

        Label name = new Label(employee.getFullName());
        verticalLayout.addComponent(name);

        HashMap<Integer, String> scheduleDay = employee.getSchedule().getEntryForDay(date).getScheduleDay();

        String tmpString = scheduleDay.get(0);
        String tmpBeginTime = timeStringBuilder(0);
        String tmpEndTime;
        for(int i = 0; i < scheduleDay.size(); i++) {
            /*
            if(scheduleDay.get(i) == tmpString || scheduleDay.get(i) == null) {
                continue;
            } else if(scheduleDay.get(i) != tmpString && tmpString == null) {
                tmpBeginTime = timeStringBuilder(i);
                tmpString = scheduleDay.get(i);
                tmpEndTime = timeStringBuilder(i + 1);
            } else if(i == scheduleDay.size() - 1 && tmpString != null) {
                tmpEndTime = timeStringBuilder(i + 1);
                verticalLayout.addComponent(new Label(tmpBeginTime + " - " + tmpEndTime + "   " + tmpString));
            } else {
                tmpEndTime = timeStringBuilder(i);
                verticalLayout.addComponent(new Label(tmpBeginTime + " - " + tmpEndTime + "   " + tmpString));
                tmpBeginTime = timeStringBuilder(i);
                tmpString = scheduleDay.get(i);
            }
            */
            if(scheduleDay.get(i) == tmpString) {
                continue;
            } else {
                if(tmpString == null) {
                    tmpBeginTime = timeStringBuilder(i);
                    tmpString = scheduleDay.get(i);
                } else if(scheduleDay.get(i) == null) {
                    tmpEndTime = timeStringBuilder(i);
                    verticalLayout.addComponent(new Label(tmpBeginTime + " - " + tmpEndTime + "   " + tmpString));
                    tmpBeginTime = timeStringBuilder(i);
                    tmpString = scheduleDay.get(i);
                } else if(scheduleDay.size() -1 == i && scheduleDay.get(i) != null) {
                    if(tmpString == null) {
                        tmpString = scheduleDay.get(i);
                    }
                    tmpEndTime = timeStringBuilder(i + 1);
                    tmpString = scheduleDay.get(i);
                    verticalLayout.addComponent(new Label(tmpBeginTime + " - " + tmpEndTime + "   " + tmpString));
                } else {
                    tmpEndTime = timeStringBuilder(i);
                    verticalLayout.addComponent(new Label(tmpBeginTime + " - " + tmpEndTime + "   " + tmpString));
                    tmpBeginTime = timeStringBuilder(i);
                    tmpString = scheduleDay.get(i);
                }
            }
        }

        horizontalLayout.addComponent(verticalLayout);

        return horizontalLayout;
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
