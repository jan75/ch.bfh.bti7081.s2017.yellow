package ch.bfh.bti7081.s2017.yellow.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by taahuem2 on 08.05.17.
 */
public class PlanningDashboardItemViewImpl extends VerticalLayout implements PlanningDashboardItemView {


    private class Shift {

        private String employee;
        private Date from;
        private Date to;

        public Shift(String employee, Date from, Date to) {
            this.employee = employee;
            this.from = from;
            this.to = to;
        }

        public String getEmployee() {
            return this.employee;
        }
        public String getFrom() {
            return new SimpleDateFormat("HH:mm").format(this.from);
        }

        public String getTo() {
            return new SimpleDateFormat("HH:mm").format(this.to);
        }

    }

    private class DateFactory {

        public Date createDateFromString(String dateToParse) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            try {
                date = sdf.parse(dateToParse);
            }
            catch (Exception e) {

            }
            return date;
        }
    }

    public PlanningDashboardItemViewImpl() {

        Label label = new Label("Employees planned today " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        addComponent(label);

        DateFactory df = new DateFactory();
        // Have some data
        List<Shift> shifts = Arrays.asList(
                new Shift("Peter Zwegat", df.createDateFromString("2011-01-01 04:00:00"), df.createDateFromString("2011-01-01 08:00:00")),
                new Shift("Hans Lustig",  df.createDateFromString("2011-01-01 08:00:00"), df.createDateFromString("2011-01-01 12:00:00")),
                new Shift("Otto Schulte", df.createDateFromString("2011-01-01 04:00:00"), df.createDateFromString("2011-01-01 12:00:00")),
                new Shift("Steffi Geil", df.createDateFromString("2011-01-01 12:00:00"), df.createDateFromString("2011-01-01 18:00:00")),
                new Shift("Petra Müller",  df.createDateFromString("2011-01-01 18:00:00"), df.createDateFromString("2011-01-01 24:00:00")),
                new Shift("Stefan Raab", df.createDateFromString("2011-01-01 08:00:00"), df.createDateFromString("2011-01-01 18:00:00")));

        // Create a grid bound to the list
        Grid<Shift> grid = new Grid<>();
        grid.setItems(shifts);
        grid.addColumn(Shift::getEmployee).setCaption("Employee");
        grid.addColumn(Shift::getFrom).setCaption("Shift from");
        grid.addColumn(Shift::getTo).setCaption("Shift to");

        addComponent(grid);
    }

}
