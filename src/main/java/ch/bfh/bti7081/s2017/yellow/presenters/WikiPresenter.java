package ch.bfh.bti7081.s2017.yellow.presenters;


import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiDataFactory;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiView;
import com.vaadin.navigator.ViewChangeListener;
import java.util.Date;

/**
 * Created by theonlyandone on 25.05.17.
 */
public class WikiPresenter implements WikiView.WikiViewListener {


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
        Wiki wiki = WikiDataFactory.getWiki();
        this.view = wikiView;
        wikiView.addListener(this);
        wikiView.updateWiki(wiki);
    }


    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("Change View");
    }


    @Override
    public void save(ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry entry) {
        DbConnector dbConnector = new DbConnector();
        DbConnector.DbTask dbTask = dbConnector.createDbTask();
        System.out.println(entry.getEntry());
        dbTask.save(entry);
        dbTask.end();
    }

    @Override
    public void edit(ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry entry) {
        System.out.println("Edit " + entry.getCaption());
    }
}
