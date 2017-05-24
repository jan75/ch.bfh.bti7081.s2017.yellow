package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import ch.bfh.bti7081.s2017.yellow.entities.resource.PatientResourceEntry;
import ch.bfh.bti7081.s2017.yellow.entities.resource.WeekResource;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;

import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by samuel on 15.05.17.
 */
public class ResourceService {

    //private SimpleService<WeekResource> service;

    private WeekResource currentWeek;

    public ResourceService() {
        loadWeek(LocalDate.now());
    }

    public void loadWeek(LocalDate date) {
        //currentWeek = service.findEntities();
        /** Mock **/
        currentWeek = new WeekResource();
        currentWeek.setYear(2017);
        currentWeek.setWeekOfYear(15);
        Patient p1 = new Patient("Ruedi", "Ruf");
        Patient p2 = new Patient("Sabine", "Sahli");
        List<PatientResourceEntry> res = new ArrayList<>();
        PatientResourceEntry e1 = new PatientResourceEntry();
        e1.setPatient(p1);
        e1.setTotalHour(18);
        PatientResourceEntry e2 = new PatientResourceEntry();
        e2.setPatient(p2);
        e2.setTotalHour(37);

        res.add(e1);
        res.add(e2);
        currentWeek.setPatientResources(res);
    }

    public int getTotalPlannedHour() {
        if (currentWeek == null) {

        }

        return (int)currentWeek.getPatientResources().stream()
                .collect(Collectors.summarizingInt(PatientResourceEntry::getTotalHour))
                .getSum();
    }

    public List<PatientResourceEntry> getPatientResource() {
        return currentWeek.getPatientResources();
    }

    public List<Patient> getPatientWhereNotAdded() {
        return new ArrayList<>();
    }

    private void loadCurrentWeek() {
        LocalDate currentDate = LocalDate.now();
    }
}
