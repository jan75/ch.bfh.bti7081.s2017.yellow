package ch.bfh.bti7081.s2017.yellow.views.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents the wiki view, acts as container for WikiPanel objects
 * @author theonlyandone
 * @see WikiPanel
 */

public class WikiViewImpl extends CustomComponent implements WikiView   {

    private WikiViewListener listener;
    private Wiki wiki;
    private Layout content;
    private ArrayList<Panel> wikiEntries = new ArrayList<Panel>();

    public WikiViewImpl() {
        final VerticalLayout layout = new VerticalLayout();

        Page.getCurrent().setTitle("Wiki");

        Label wikiTitle =  new Label("This is our wiki, say hello!");
        wikiTitle.setStyleName("wikiTitleLabel");

        TextField wikiSearch = new TextField();
        wikiSearch.setStyleName("wikiSearch");
        wikiSearch.setWidth("100%");
        wikiSearch.setValue("Search title...");
        wikiSearch.addShortcutListener(new ShortcutListener("Shortcut Name", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                if (target instanceof TextField) {
                    TextField wikiSearchBar = (TextField) target;
                    search(wikiSearchBar.getValue());
                }
            }
        });

        content = new VerticalLayout();
        content.setWidth("80%");

        content.addComponent(wikiTitle);
        content.addComponent(wikiSearch);

        layout.addComponent(content);
        layout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        setCompositionRoot(layout);
        setVisible(false);
    }

    /**
     * Search function for wiki entries
     * Only searches in titlebar
     * @param textToFind searchstring
     */
    private void search(String textToFind) {
        for (Panel p : wikiEntries) {
            if (p.getCaption().toLowerCase().contains(textToFind.toLowerCase())) {
                p.setVisible(true);
            }
            else {
                p.setVisible(false);
            }
        }
    }

    /**
     * Set wiki instance and loads data
     * @param wiki Wiki, which you want to display
     */
    public void updateWiki(Wiki wiki) {
        this.wiki = wiki;
        reloadData();
    }

    /**
     * Adds all WikiPanels with wikiEntries from our wiki instance to content layout
     */
    private void reloadData() {
        List<WikiEntry> wikiEntryData = this.wiki.getWikiEntry();

        for (WikiEntry wikiEntry : wikiEntryData ) {
            Panel p = new WikiPanel(wikiEntry, listener);
            wikiEntries.add(p);
            content.addComponent(p);
        }
    }

    @Override
    public void addListener(WikiViewListener listener) {
        this.listener = listener;
    }

    /**
     * This Method is called when the view navigator calls this view and
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listener.changeView(viewChangeEvent);
        setVisible(true);
    }

}
