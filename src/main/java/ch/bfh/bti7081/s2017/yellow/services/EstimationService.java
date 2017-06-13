package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.DailyEstimationBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.PatientEstimationBean;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.PatientEstimation;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.DailyEstimation;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector.DbTask;
import ch.bfh.bti7081.s2017.yellow.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

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

    private SimpleService<DailyEstimationBean> estimationService;

    private SimpleService<PatientBean> patientService;

    private DbConnector connector;

    public EstimationService() {
        connector = new DbConnector();
        estimationService = new SimpleServiceImpl<>(DailyEstimation.class, DailyEstimationBean.class, connector);
        patientService = new SimpleServiceImpl(Patient.class, PatientBean.class, connector);
    }

    public DailyEstimationBean getDailyEstimation(LocalDate date) {
    	DbTask dbTask = connector.createDbTask();
    	dbTask.start();
        Query q = dbTask.getSession().createQuery("from DailyEstimation d where d.date = :date");
        q.setParameter("date", date);

        List<DailyEstimationBean> result = q.getResultList();
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

        List<PatientBean> patients = patientService.getALlEntities();

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
