package ch.bfh.bti7081.s2017.yellow.presenters;


import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiDataFactory;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiView;
import com.vaadin.navigator.ViewChangeListener;
import java.util.Date;

/**
 * Presenter for a wikiview. Supports editing and saving wiki entries to db.
 * @author theonlyandone
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

    /**
     * This Method is called when the view navigator calls this view and
     * Nothing done right now, just log to console
     * @param event ViewChange event
     */
    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("Change View");
    }

    /**
     * Saves wikiEntry to db
     */
    @Override
    public void save(ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry entry) {
        DbConnector dbConnector = new DbConnector();
        DbConnector.DbTask dbTask = dbConnector.createDbTask();
        System.out.println(entry.getEntry());
        dbTask.save(entry);
        dbTask.end();
    }

    /**
     * Writes wiki entry title to console, which was edited
     */
    @Override
    public void edit(ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry entry) {
        System.out.println("Edit " + entry.getCaption());
    }
}
