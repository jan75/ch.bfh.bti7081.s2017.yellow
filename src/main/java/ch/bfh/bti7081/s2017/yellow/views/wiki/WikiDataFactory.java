package ch.bfh.bti7081.s2017.yellow.views.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.person.User;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton wiki instance, provides wiki with sample data
 * @author theonlyandone
 */
public final class WikiDataFactory {

    private static Wiki wiki;

    public static Wiki getWiki() {

        if (wiki == null) {
            initializeWiki();
        }
        return wiki;
    }


    /**
     * Initialize static wiki with sample data and saves all entries to db.
     */
    private static void initializeWiki() {
        DbConnector dbConnector = new DbConnector();
        DbConnector.DbTask dbTask = dbConnector.createDbTask();

        wiki = new Wiki();
        List<WikiEntry> wikiEntries = new ArrayList<WikiEntry>();
        wikiEntries.add( new WikiEntry(wiki, "Bauchschmerzen", "Der Bauchschmerz (Abdominalschmerz oder abdomineller Schmerz) ist eines der häufigsten Symptome, die zur Inanspruchnahme ärztlicher Hilfe führen. Bauchschmerz kann durch eine Vielzahl von Ursachen ausgelöst werden. Zu diesen Ursachen gehören konkrete Erkrankungen von Organen des Bauchraumes, Krankheiten, die sich außerhalb des Bauchraumes abspielen, aber auch Missempfindungen, die durch psychische Erkrankungen ausgelöst werden.", "Allg. Medizin"));
        wikiEntries.add( new WikiEntry(wiki, "Ohrenschmerzen", "Als Otalgie (griech. οὦς (oōs), Gen. ὠτός (ōtos), Ohr und griech. ἄλγος (álgos) Schmerz) bezeichnet man in der Medizin Ohrenschmerzen verschiedener – auch unbekannter − Ursache. Die Otalgie ist das Leitsymptom aller entzündlichen Erkrankungen des Mittelohrs und des äußeren Ohres. Man unterscheidet zwischen primärer und sekundärer Otalgie. Ohrenschmerzen werden durch den Plexus tympanicus über den Nervus glossopharyngeus vermittelt. Auch der Nervus vagus ist in Trommelfellnähe beteiligt", "Allg. Medizin") );
        wikiEntries.add( new WikiEntry(wiki, "Kopfschmerzen", "Kopfschmerzen gehören neben Rückenschmerzen zu den häufigsten gesundheitlichen Beeinträchtigungen: Etwa vier bis fünf Prozent der deutschen Bevölkerung leiden unter täglichen und ca. 70 Prozent leiden unter anfallsweisen oder chronischen (immer wiederkehrenden) Kopfschmerzen. In einer großen deutschen Studie über 14 Jahre gaben etwa 60 % der Befragten an, Kopfschmerzen gehabt zu haben. Dabei zeigte sich, dass gehäuft Frauen und Bewohner von Städten über 50.000 Einwohner an Kopfschmerzen leiden." +
                "Dabei entfallen über 90 Prozent der Kopfschmerzerkrankungen auf die beiden primären Kopfschmerzformen Migräne und Spannungskopfschmerzen, die auch kombiniert auftreten können. Zu den primären Kopfschmerzen gehört auch der Cluster-Kopfschmerz und der medikamentenassoziierte Kopfschmerz.\n" +
                "\n", "Allg. Medizin") );
        wikiEntries.add( new WikiEntry(wiki, "Tech Dok", "Der Bauchschmerz (Abdominalschmerz oder abdomineller Schmerz) ist eines der häufigsten Symptome, die zur Inanspruchnahme ärztlicher Hilfe führen. Bauchschmerz kann durch eine Vielzahl von Ursachen ausgelöst werden. Zu diesen Ursachen gehören konkrete Erkrankungen von Organen des Bauchraumes, Krankheiten, die sich außerhalb des Bauchraumes abspielen, aber auch Missempfindungen, die durch psychische Erkrankungen ausgelöst werden.", "Tech. Medizin"));


        wiki.setWikiEntry(wikiEntries);

        wikiEntries.get(0).setUser(new User("Michi", "Jackson"));
        wikiEntries.get(1).setUser(new User("Peter", "Imhof"));
        wikiEntries.get(2).setUser(new User("Christu", "Ronaldo"));
        wikiEntries.get(3).setUser(new User("Beni", "Turnschuh"));


        for (WikiEntry wikiEntry : wikiEntries) {
            dbTask.save(wikiEntry);
        }
        
        dbTask.end();
    }

}
