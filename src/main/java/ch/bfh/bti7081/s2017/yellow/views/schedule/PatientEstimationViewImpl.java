package ch.bfh.bti7081.s2017.yellow.views.schedule;

import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.EstimationViewBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.PatientEstimationBean;
import ch.bfh.bti7081.s2017.yellow.views.listeners.EstimateResourceListener;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 16.05.17.
 */
public class PatientEstimationViewImpl extends CustomComponent implements PatientEstimationView, HasValue.ValueChangeListener<LocalDate> {

    private Grid<PatientEstimationBean> grid;

    private DateField dateField;

    private List<PatientEstimationBean> patientEstimations;

    private List<PatientBean> patientList;

    private ComboBox<PatientBean> selectPatient = new ComboBox<>("Add a Patient");

    private List<EstimateResourceListener> listeners;

    private BeanValidationBinder<EstimationViewBean> binder;

    DataProvider<PatientBean, String> selectDataProvider;

    DataProvider<PatientEstimationBean, String> gridDataProvider;

    public PatientEstimationViewImpl() {
        listeners = new ArrayList<>();
        patientEstimations = new ArrayList<>();
        patientList = new ArrayList<>();
        gridDataProvider = DataProvider.fromFilteringCallbacks(
                query -> patientEstimations.stream(),
                query -> patientEstimations.size()
        );
        selectDataProvider = DataProvider.fromFilteringCallbacks(
                query -> patientList.stream(),
                query ->patientList.size()
        );
        binder = new BeanValidationBinder<>(EstimationViewBean.class);

        VerticalLayout rootLayout = new VerticalLayout();
        HorizontalLayout weekNavi = new HorizontalLayout();

        weekNavi.addComponent(new Button("<", e -> dateField.setValue(dateField.getValue().minusDays(1L))));
        dateField = new DateField("dateField", this);
        dateField.setTextFieldEnabled(false);
        dateField.setShowISOWeekNumbers(true);
        binder.bind(
                dateField,
                b -> b.getDailyEstimationBean().getDate(),
                (b, date) -> b.getDailyEstimationBean().setDate(date)
        );
        weekNavi.addComponent(dateField);
        weekNavi.addComponent(new Button(">", e -> dateField.setValue(dateField.getValue().plusDays(1L))));
        rootLayout.addComponent(weekNavi);

        grid = new Grid<>();
        grid.addColumn(b -> b.getPatient().getFirstName()).setCaption("first name");
        grid.addColumn(b -> b.getPatient().getLastName()).setCaption("last name");
        Grid.Column<PatientEstimationBean, Integer> hourCol =
                grid.addColumn(PatientEstimationBean::getTotalHour).setCaption("estimated hours");
        grid.setDataProvider(gridDataProvider);

        TextField gridHourField = new TextField();
        Binder.Binding<PatientEstimationBean, String> gridHourBinding = grid.getEditor().getBinder().bind(gridHourField,
                b -> String.valueOf(b.getTotalHour()),
                (b, hour) -> b.setTotalHour(Integer.parseInt(hour))
        );
        hourCol.setEditorBinding(gridHourBinding).setEditable(true);

        rootLayout.addComponent(grid);
        rootLayout.addComponent(
                new Button("Save", e -> listeners.forEach(EstimateResourceListener::saveClicked))
        );

        /* Patient Form */
        GridLayout formGrid = new GridLayout(1, 5);
        formGrid.addComponent(new Label("Patient"),0, 0);
        selectPatient.setItemCaptionGenerator(b -> b.getFirstName() + " " + b.getLastName());
        selectPatient.setDataProvider(selectDataProvider);
        binder.bind(
                selectPatient,
                b -> b.getNewPatientEstimation().getPatient(),
                (b, p) -> b.getNewPatientEstimation().setPatient(p)
        );
        formGrid.addComponent(selectPatient, 0, 1);
        formGrid.addComponent(new Label("Total estimated hours"), 0, 2);
        TextField estimatedHourField = new TextField();
        binder.bind(
                estimatedHourField,
                b -> String.valueOf(b.getNewPatientEstimation().getTotalHour()),
                (b, h) -> b.getNewPatientEstimation().setTotalHour(Integer.parseInt(h))
        );
        formGrid.addComponent(estimatedHourField, 0, 3);
        formGrid.addComponent(
                new Button("Add", e -> addEstimation()),
                0,
                4
        );
        rootLayout.addComponent(formGrid);

        setCompositionRoot(rootLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) { }

    @Override
    public void setPatientEstimation(EstimationViewBean estimation) {
        binder.setBean(estimation);
        patientList = estimation.getPatientBean();
        patientEstimations = estimation.getDailyEstimationBean().getPatientEstimation();
        selectDataProvider.refreshAll();
        gridDataProvider.refreshAll();
    }


    @Override
    public void valueChange(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent) {
        listeners.forEach(listeners -> listeners.changeDate(valueChangeEvent.getValue()));
    }

    @Override
    public void addListener(EstimateResourceListener listener) {
        listeners.add(listener);
    }

    private void addEstimation() {
        listeners.forEach(EstimateResourceListener::addEstimation);
    }
}
