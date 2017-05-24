package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.entities.resource.PatientResourceEntry;
import ch.bfh.bti7081.s2017.yellow.views.listeners.EstimateResourceListener;
import com.vaadin.data.HasValue;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 16.05.17.
 */
public class EstimateResourceViewImpl extends CustomComponent implements EstimateResourceView, Button.ClickListener, HasValue.ValueChangeListener<LocalDate> {

    private Grid<PatientResourceEntry> grid;

    private DateField dateField;

    private List<EstimateResourceListener> listeners;

    public EstimateResourceViewImpl() {
        listeners = new ArrayList<>();

        VerticalLayout rootLayout = new VerticalLayout();
        HorizontalLayout weekNavi = new HorizontalLayout();
        weekNavi.addComponent(new Button("<", this));
        dateField = new DateField("dateField", this);
        dateField.setTextFieldEnabled(false);
        dateField.setShowISOWeekNumbers(true);
        dateField.setValue(LocalDate.now());
        weekNavi.addComponent(dateField);
        weekNavi.addComponent(new Button(">", this));
        rootLayout.addComponent(weekNavi);

        grid = new Grid<>();
        grid.addColumn(PatientResourceEntry::getPatient);
        grid.addColumn(PatientResourceEntry::getTotalHour); //.setEditable(true);
        rootLayout.addComponent(grid);

        rootLayout.addComponent(new Button("Add", this));
        setCompositionRoot(rootLayout);
    }

    @Override
    public void setPatientResources(List<PatientResourceEntry> resources) {
        grid.setItems(resources);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        switch (clickEvent.getComponent().getCaption()){
            case "<":
                dateField.setValue(dateField.getValue().minusWeeks(1L));
                break;
            case ">":
                dateField.setValue(dateField.getValue().plusWeeks(1L));
                break;
            case "Add":
                break;
        }
    }

    @Override
    public void valueChange(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent) {
        notifyListener(valueChangeEvent.getValue());
    }

    @Override
    public void addListener(EstimateResourceListener listener) {
        listeners.add(listener);
    }

    private void notifyListener(LocalDate date) {
        listeners.forEach(listeners -> listeners.changeDate(date));
    }
}
