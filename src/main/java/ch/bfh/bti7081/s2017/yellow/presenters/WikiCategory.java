package ch.bfh.bti7081.s2017.yellow.presenters;

/**
 * Created by taahuem2 on 25.05.17.
 */
public enum WikiCategory {
    CommonMedicine("Allgemein Medizin"), AlternativeMedicin("Alternative Medizin"), TechnicalMedicine("Technische Medizin"), Therapie("Therapie");

    private final String text;

    private WikiCategory(final String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}
