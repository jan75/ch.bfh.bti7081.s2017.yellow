package ch.bfh.bti7081.s2017.yellow.presenters;


import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by taahuem2 on 25.05.17.
 */
public class WikiPresenter  implements WikiView.WikiViewListener {


    private class WikiEntry {

        String title;
        String content;
        WikiCategory category;
        Date createdAt;
        Date modifiedAt;
        String createdFrom;
        String modifiedFrom;

        public WikiEntry (String title, String content, WikiCategory category, String createdFrom) {
            this.title = title;
            this.content = content;
            this.category = category;
            this.createdAt = new Date();
            this.modifiedAt = this.createdAt;
            this.createdFrom = createdFrom;
            this.modifiedFrom =  this.createdFrom;
        }

    }


    private WikiView view;

    public WikiPresenter(WikiView wikiView) {

        this.view = wikiView;
        view.addListener(this);
    }

    public List<WikiEntry> sampleWikiEntries() {
        List<WikiEntry> wikiEntries = new ArrayList<WikiEntry>();
        wikiEntries.add(new WikiEntry("Husten", "Husten ist schlecht für den Rachen.", WikiCategory.CommonMedicine, "Oberchefbossartzt Wehrlein"));
        wikiEntries.add(new WikiEntry("Kopfweh", "Kopfweh ist schlecht für den kopf.", WikiCategory.CommonMedicine, "Oberchefbossartzt Wehrlein"));
        wikiEntries.add(new WikiEntry("Kopfwehtherapie - Was beachten?", "Bei der Kopfwehtherapie ist die Einnahmezeit des Konterbieres enorm entscheiden. Am besten so fürh wie möglich", WikiCategory.Therapie, "Praktikant Hülsensack"));
        wikiEntries.add(new WikiEntry("Prgramm XYZ", "Starten mit Doppelklick, blablabla", WikiCategory.TechnicalMedicine, "Oberchefbossartzt Wehrlein"));
        return wikiEntries;
    }

    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {

        System.out.println("Change View");
    }


}
