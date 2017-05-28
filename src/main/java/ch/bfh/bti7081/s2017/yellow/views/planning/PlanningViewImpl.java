package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeeBean;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hezamu.canvas.Canvas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Concrete PlanningView implementation
 * @author iSorp
 */
public class PlanningViewImpl extends CustomComponent implements PlanningView {

    public PlanningViewImpl() {
        final VerticalLayout layout = new VerticalLayout();

        //Test test = new Test();
        //layout.addComponent(test);

        EmployeeTest employee = new EmployeeTest("Tim", "Gerber");
        ScheduleTest schedule = employee.getSchedule();
        List<ScheduleEntryTest> scheduleEntryList = schedule.getScheduleEntryTestList();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 28);
        Date date = calendar.getTime();

        ScheduleEntryTest scheduleEntryTest = new ScheduleEntryTest(date);
        List<String> scheduleDay = scheduleEntryTest.getScheduleDay();

        for(String string: scheduleDay) {
            layout.addComponent(new Label(string));
        }

        layout.addComponent(new Label(new Integer(scheduleDay.size()).toString()));



        //layout.addComponent(horizontalLayout);
        setCompositionRoot(layout);
        setVisible(false);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setVisible(true);
    }

    private HorizontalLayout drawEmployee(EmployeeBean employee) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(false);

        List<Canvas> canvasList = new ArrayList<Canvas>();
        String color = "red";

        for(int i = 0; i < 24; i++) {
            if(color == "red") {
                color = "blue";
            } else {
                color = "red";
            }
            canvasList.add(new Canvas());
            Canvas currentCanvas = canvasList.get(canvasList.size() -1);
            currentCanvas.setFillStyle(color);
            currentCanvas.fillRect(0,0,10,20);
            currentCanvas.setWidth("12px");
            currentCanvas.setHeight("20px");
        }

        for(Canvas canvas: canvasList) {
            horizontalLayout.addComponent(canvas);
        }

        return horizontalLayout;
    }

}
