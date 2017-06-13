package ch.bfh.bti7081.s2017.yellow.views.listeners;

import java.time.LocalDate;

/**
 * Created by samuel on 16.05.17.
 */
public interface EstimateResourceListener {

    void changeDate(LocalDate date);

    void addEstimation();

    void saveClicked();
}
