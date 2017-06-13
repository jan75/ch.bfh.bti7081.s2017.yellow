package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.DailyEstimationBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.PatientEstimationBean;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.PatientEstimation;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.DailyEstimation;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import ch.bfh.bti7081.s2017.yellow.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by samuel on 15.05.17.
 */
public class EstimationService {

    private DailyEstimationBean selectedDay;

    private SimpleService<DailyEstimation, DailyEstimationBean> estimationService;

    private SimpleService<Patient, PatientBean> patientService;

    public EstimationService() {
        estimationService = new SimpleServiceImpl<>(DailyEstimation.class, DailyEstimationBean.class, new DbConnector());
        patientService = new SimpleServiceImpl<>(Patient.class, PatientBean.class, new DbConnector());
    }

    public DailyEstimationBean getDailyEstimation(LocalDate date) {
        CriteriaBuilder builder = HibernateUtil.getSessionFactory().getCriteriaBuilder();
        CriteriaQuery<DailyEstimation> criteria = builder.createQuery(DailyEstimation.class);
        criteria.where(builder.equal(criteria.from(DailyEstimation.class).get("date"), date));

        List<DailyEstimationBean> result = estimationService.findEntities(criteria);
        if (result.size() > 0) {
            selectedDay = result.get(0);
        } else {
            selectedDay = new DailyEstimationBean();
            selectedDay.setDate(date);
            selectedDay.setPatientEstimation(new ArrayList<>());
        }

        return selectedDay;
    }

    public List<PatientBean> getPatientWhereNotAdded() {
        if (selectedDay == null) {
            getDailyEstimation(LocalDate.now());
        }
        List<PatientBean> addedPatients = selectedDay.getPatientEstimation().stream()
                .map(PatientEstimationBean::getPatient)
                .collect(Collectors.toList());

        List<PatientBean> patients = patientService.getAllEntities();

        List<PatientBean> result = new ArrayList<>(patients);
        for (PatientBean p: patients) {
            for (PatientBean ap:addedPatients) {
                if (p.compareTo(ap) == 0) {
                    result.remove(p);
                }
            }
        }
        
        return  result;
    }

    public void addPatientEstimation(PatientEstimationBean patientEstimationBean) {
        selectedDay.getPatientEstimation().add(patientEstimationBean);
    }

    public void saveDay() {
        estimationService.saveEntity(selectedDay);
    }
}
