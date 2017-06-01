package ch.bfh.bti7081.s2017.yellow.views.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.entities.person.User;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taahuem2 on 01.06.17.
 */
public final class WikiDataFactory {

    private static Wiki wiki;

    public static Wiki getWiki() {

        if (wiki == null) {
            initializeWiki();
        }
        return wiki;
    }

    private static void initializeWiki() {
        //Get a session
        DbConnector.DbTask dbTask = new DbConnector.DbTask();

        wiki = new Wiki();
        List<WikiEntry> wikiEntries = new ArrayList<WikiEntry>();
        //create the MANY entity
        wikiEntries.add( new WikiEntry(wiki, "Bauchschmerzen", "this is a huge wiki content", "Allg. Medizin") );
        wikiEntries.add( new WikiEntry(wiki, "Ohrenschmerzen", "this is a huge wiki content", "Allg. Medizin") );
        wikiEntries.add( new WikiEntry(wiki, "Kopfschmerzen", "Kopfschmerzen gehören neben Rückenschmerzen zu den häufigsten gesundheitlichen Beeinträchtigungen: Etwa vier bis fünf Prozent der deutschen Bevölkerung leiden unter täglichen und ca. 70 Prozent leiden unter anfallsweisen oder chronischen (immer wiederkehrenden) Kopfschmerzen. In einer großen deutschen Studie über 14 Jahre gaben etwa 60 % der Befragten an, Kopfschmerzen gehabt zu haben. Dabei zeigte sich, dass gehäuft Frauen und Bewohner von Städten über 50.000 Einwohner an Kopfschmerzen leiden." +
                "Dabei entfallen über 90 Prozent der Kopfschmerzerkrankungen auf die beiden primären Kopfschmerzformen Migräne und Spannungskopfschmerzen, die auch kombiniert auftreten können. Zu den primären Kopfschmerzen gehört auch der Cluster-Kopfschmerz und der medikamentenassoziierte Kopfschmerz. Gemeinsam haben sie, dass bei bildgebender Diagnostik kein sichtbares Korrelat gefunden werden kann" +
                "Bei den primären Kopfschmerzen ist der Schmerz selbst die Erkrankung. Ihre Ursache ist immer noch nicht genau bekannt und kann deshalb auch nicht immer beseitigt werden. Die Vorbeugung zielt darauf hin, bekannte Auslöser und Faktoren für die Entstehung zu vermeiden. Die Behandlung besteht in einer schnellen und anhaltenden Schmerzlinderung.\n" +
                "\n", "Allg. Medizin") );


        wiki.setWikiEntry(wikiEntries);
        //create the ONE entity

        User user = new User("Michi", "Jackson");

        //only save wikiEntry
        for (WikiEntry wikiEntry : wikiEntries) {
            wikiEntry.setUser(user);
            dbTask.save(wikiEntry);
        }
    }

}
