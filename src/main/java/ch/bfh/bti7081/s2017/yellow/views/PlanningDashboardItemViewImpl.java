package ch.bfh.bti7081.s2017.yellow.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by theonlyandone on 08.05.17.
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

    private class DateService {

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

        DateService ds = new DateService();

        // Create sample shift data
        List<Shift> shifts = Arrays.asList(
                new Shift("Peter Zwegat", ds.createDateFromString("2011-01-01 04:00:00"), ds.createDateFromString("2011-01-01 08:00:00")),
                new Shift("Hans Lustig",  ds.createDateFromString("2011-01-01 08:00:00"), ds.createDateFromString("2011-01-01 12:00:00")),
                new Shift("Otto Schulte", ds.createDateFromString("2011-01-01 04:00:00"), ds.createDateFromString("2011-01-01 12:00:00")),
                new Shift("Steffi Geil", ds.createDateFromString("2011-01-01 12:00:00"), ds.createDateFromString("2011-01-01 18:00:00")),
                new Shift("Petra Müller",  ds.createDateFromString("2011-01-01 18:00:00"), ds.createDateFromString("2011-01-01 24:00:00")),
                new Shift("Stefan Raab", ds.createDateFromString("2011-01-01 08:00:00"), ds.createDateFromString("2011-01-01 18:00:00")));

        Grid<Shift> grid = new Grid<>();
        grid.setWidth("100%");
        grid.setHeight("100%");
        grid.setItems(shifts);
        grid.addColumn(Shift::getEmployee).setCaption("Employee");
        grid.addColumn(Shift::getFrom).setCaption("Shift from");
        grid.addColumn(Shift::getTo).setCaption("Shift to");

        addComponent(grid);
    }

}
