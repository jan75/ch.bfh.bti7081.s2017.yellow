package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.presenters.PlanningDetailPresenter;
import ch.bfh.bti7081.s2017.yellow.services.PlanningService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Concrete PlanningView implementation
 * @author Jan Ackermann
 */
public class PlanningDetailViewImpl extends CustomComponent implements PlanningDetailView {
    private EmployeePlanningBean employee = null;
    private ScheduleBean schedule = null;
    private LocalDate date = null;
    private HorizontalLayout layout = new HorizontalLayout();
    private VerticalLayout currentPlanLayout = new VerticalLayout();
    VerticalLayout addEntryLayout = new VerticalLayout();
    PlanningDetailPresenter planningDetailPresenter;

    /**
     * The constructor creates the basic layout with the static components as well as dynamically loading (over the presenter) the employees and displaying them
     */
    public PlanningDetailViewImpl() {
        planningDetailPresenter = new PlanningDetailPresenter(this);

        layout.addComponent(new Label("planningDetailView"));
        layout = new HorizontalLayout();
        currentPlanLayout = new VerticalLayout();
        layout.addComponent(currentPlanLayout);


        addEntryLayout.addComponent(new Label("Add Planning Entry. Warning: Old entries will be overwritten!"));
        ComboBox<Integer> beginningComboBox = new ComboBox<>();
        ComboBox<Integer> endingComboBox = new ComboBox<>();
        Integer[] hoursArray = new Integer[24];
        for(int i = 0; i < hoursArray.length; i++) {
            hoursArray[i] = i;
        }
        beginningComboBox.setItems(hoursArray);
        endingComboBox.setItems(hoursArray);
        TextField activityTextArea = new TextField();
        activityTextArea.setPlaceholder("Activity...");

        Button addPlanningEntry = new Button("Add", (Button.ClickListener) clickEvent -> {
            planningDetailPresenter.addPlanningEntry(beginningComboBox.getValue(), endingComboBox.getValue(), activityTextArea.getValue(), employee, date);
        });
        addEntryLayout.addComponents(beginningComboBox, endingComboBox, activityTextArea, addPlanningEntry);

        layout.addComponent(addEntryLayout);
        setCompositionRoot(layout);
        setVisible(false);
    }

    /**
     * @param employee The employee which is to display
     * @param date The date for which the schedule is to be displayed
     */
    @Override
    public void updateView(EmployeePlanningBean employee, LocalDate date) {
        this.employee = employee;
        this.date = date;
        this.schedule = employee.getSchedule();

        currentPlanLayout.removeAllComponents();
        currentPlanLayout.addComponent(drawDaySchedule());
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
            if(scheduleDay.get(i).equals(tmpString)) {
                continue;
            } else {
                tmpEndTime = timeStringBuilder(i);
                if(tmpString != "") {
                    verticalLayout.addComponent(new Label(tmpBeginTime + " - " + tmpEndTime + "   " + tmpString));
                }
                tmpBeginTime = timeStringBuilder(i);
                tmpString = scheduleDay.get(i);
            }
        }
        if(tmpString != "") {
            verticalLayout.addComponent(new Label(tmpBeginTime + " - 00:00   " + tmpString));
        }

        return verticalLayout;
    }

    /**
     * Helper method to build time in the format 00:00
     * @param timeShort the one or two digit long integer with the time in 24h format
     * @return returns a string in the format 00:00
     */
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
        if (this.employee == null) {
            NavigatorController.getInstance().navigateTo("planningView");
        } else {
            setVisible(true);
        }
    }
}
