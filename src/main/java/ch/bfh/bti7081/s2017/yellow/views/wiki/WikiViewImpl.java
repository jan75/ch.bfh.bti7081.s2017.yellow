package ch.bfh.bti7081.s2017.yellow.views.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by taahuem2 on 25.05.17.
 */

public class WikiViewImpl extends CustomComponent implements WikiView   {

    private WikiViewListener listener;
    private ArrayList<Panel> wikiEntries = new ArrayList<Panel>();

    public WikiViewImpl() {
        final VerticalLayout layout = new VerticalLayout();

        Label wikiTitle =  new Label("This is our awesome wiki, say hello!");
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

                    if (wikiSearchBar.getValue().equals("Hello")){
                        Notification.show("haha you said hello to a wiki. noob, you are",
                                Notification.Type.HUMANIZED_MESSAGE);
                    }
                }
            }
        });

        VerticalLayout content = new VerticalLayout();
        content.setWidth("80%");

        content.addComponent(wikiTitle);
        content.addComponent(wikiSearch);

       /* for (int i = 0; i<20; i++) {
            Panel p = createWikiEntryPanel(i);
            wikiEntries.add(p);
            content.addComponent(p);
        }*/

        Wiki wiki = WikiDataFactory.getWiki();
        List<WikiEntry> wikiEntryData = wiki.getWikiEntry();

        for (WikiEntry wikiEntry : wikiEntryData ) {
            Panel p = createWikiEntryPanel(wikiEntry);
            wikiEntries.add(p);
            content.addComponent(p);
        }


        layout.addComponent(content);
        layout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        setCompositionRoot(layout);
        setVisible(false);
    }

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

    private Panel createWikiEntryPanel(WikiEntry wikiEntry){

        Panel panel = new Panel(wikiEntry.getCaption()); //"Kopfschmerzen");


        GridLayout grid = new GridLayout(2, 1);
        grid.setWidth("100%");

        VerticalLayout panelContent = new VerticalLayout();
        panel.setWidth("100%");
        panel.addStyleName("wikiPanel");

        Label wikiEntryContent = new Label(
                wikiEntry.getEntry(), ContentMode.HTML);

        wikiEntryContent.setStyleName("wikiEntryContentLabel");

        String category = wikiEntry.getCategory();
        String createdFrom = wikiEntry.getUser().getFirstName() + " " + wikiEntry.getUser().getLastName(); // "Michi Jackson";
        String createdAt = convertDateToString(wikiEntry.getCreatedAt()); //"18.02.2017";
        String updatedAt = convertDateToString(wikiEntry.getUpdatedAt()); //"30.05.2017";

        VerticalLayout wikiEntryInformationContent = new VerticalLayout();
        wikiEntryInformationContent.setStyleName("wikiEntryInformationContent");

        Label wikiEntryInformation = new Label(
                "<table>\n" +
                        "  <tr>\n" +
                        "    <td>Kategorie:</td>\n" +
                        "    <td>" + category + "</td> \n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Erstellt von:</td>\n" +
                        "    <td>" + createdFrom + "</td> \n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Erstellt am:</td>\n" +
                        "    <td>" + createdAt + "</td> \n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Zuletzt bearbeitet:</td>\n" +
                        "    <td>" + updatedAt + "</td> \n" +
                        "  </tr>\n" +
                        "</table>"
                , ContentMode.HTML);

        HorizontalLayout wikiEntryInformationButtonContent = new HorizontalLayout();
        Button editEntry = new Button("Edit");
        Button uploadEntry = new Button("Delete");
        wikiEntryInformationButtonContent.addComponents(editEntry, uploadEntry);

        wikiEntryInformationContent.addComponents(wikiEntryInformation, wikiEntryInformationButtonContent);

        wikiEntryInformationContent.setComponentAlignment(wikiEntryInformation, Alignment.TOP_RIGHT);
        wikiEntryInformationContent.setComponentAlignment(wikiEntryInformationButtonContent, Alignment.TOP_CENTER);

        panelContent.addComponent(wikiEntryContent);
        wikiEntryContent.setWidth("100%");
        grid.addComponent(wikiEntryContent, 0, 0);
        grid.addComponent(wikiEntryInformationContent, 1, 0);
        grid.setColumnExpandRatio(0, 0.75f);
        grid.setColumnExpandRatio(1, 0.25f);
        grid.setComponentAlignment(wikiEntryInformationContent, Alignment.TOP_CENTER);
        panel.setContent(grid);

        return panel;
    }

    private String convertDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    @Override
    public void addListener(WikiViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listener.changeView(viewChangeEvent);
        setVisible(true);
    }

}
