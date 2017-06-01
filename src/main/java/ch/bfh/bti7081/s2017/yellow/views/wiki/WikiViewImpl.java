package ch.bfh.bti7081.s2017.yellow.views.wiki;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.util.ArrayList;


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
                TextField wikiSearchBar = (TextField)target;
                search(wikiSearchBar.getValue());
            }
        });

        VerticalLayout content = new VerticalLayout();
        content.setWidth("80%");

        content.addComponent(wikiTitle);
        content.addComponent(wikiSearch);

        for (int i = 0; i<20; i++) {
            Panel p = createWikiEntryPanel(i);
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

    private Panel createWikiEntryPanel(int i){
        Panel panel = new Panel("Kopfschmerzen" + i);

        GridLayout grid = new GridLayout(2, 1);
        grid.setWidth("100%");

        VerticalLayout panelContent = new VerticalLayout();
        panel.setWidth("100%");
        panel.addStyleName("wikiPanel");

        Label wikiEntryContent = new Label(
                "Kopfschmerzen" + i + " gehören neben Rückenschmerzen zu den häufigsten gesundheitlichen Beeinträchtigungen: Etwa vier bis fünf Prozent der deutschen Bevölkerung leiden unter täglichen und ca. 70 Prozent leiden unter anfallsweisen oder chronischen (immer wiederkehrenden) Kopfschmerzen. In einer großen deutschen Studie über 14 Jahre gaben etwa 60 % der Befragten an, Kopfschmerzen gehabt zu haben. Dabei zeigte sich, dass gehäuft Frauen und Bewohner von Städten über 50.000 Einwohner an Kopfschmerzen leiden.[1]\n" +
                        "Dabei entfallen über 90 Prozent der Kopfschmerzerkrankungen auf die beiden primären Kopfschmerzformen Migräne und Spannungskopfschmerzen, die auch kombiniert auftreten können. Zu den primären Kopfschmerzen gehört auch der Cluster-Kopfschmerz und der medikamentenassoziierte Kopfschmerz. Gemeinsam haben sie, dass bei bildgebender Diagnostik kein sichtbares Korrelat gefunden werden kann.\n" +
                        "Bei den primären Kopfschmerzen ist der Schmerz selbst die Erkrankung. Ihre Ursache ist immer noch nicht genau bekannt und kann deshalb auch nicht immer beseitigt werden. Die Vorbeugung zielt darauf hin, bekannte Auslöser und Faktoren für die Entstehung zu vermeiden. Die Behandlung besteht in einer schnellen und anhaltenden Schmerzlinderung.\n", ContentMode.HTML);

        wikiEntryContent.setStyleName("wikiEntryContentLabel");

        String category = "Allgemeinmedizin";
        String createdFrom = "Michi Jackson";
        String createdAt = "18.02.2017";
        String updatedAt = "30.05.2017";

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
