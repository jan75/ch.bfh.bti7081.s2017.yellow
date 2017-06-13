package ch.bfh.bti7081.s2017.yellow.views.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by taahuem2 on 12.06.17.
 */
public class WikiPanel extends Panel {

    private WikiEntry wikiEntry;
    private TextArea wikiEntryContent;

    public WikiPanel(WikiEntry wikiEntry, WikiView.WikiViewListener listener) {

        super(wikiEntry.getCaption());

        this.wikiEntry = wikiEntry;

        GridLayout grid = new GridLayout(2, 1);
        grid.setWidth("100%");

        VerticalLayout panelContent = new VerticalLayout();
        this.setWidth("100%");
        this.addStyleName("wikiPanel");

        wikiEntryContent = new TextArea( ); //wikiEntry.getEntry(), ContentMode.HTML);
        wikiEntryContent.setValue(wikiEntry.getEntry());
        wikiEntryContent.setStyleName("wikiEntryContentLabel");

        String category = wikiEntry.getCategory();
        String createdFrom = wikiEntry.getUser().getFirstName() + " " + wikiEntry.getUser().getLastName(); // "Michi Jackson";
        String createdAt = convertDateToString(wikiEntry.getCreatedAt());
        String updatedAt = convertDateToString(wikiEntry.getUpdatedAt());

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
        editEntry.addClickListener(event -> listener.edit(this.wikiEntry));

        Button uploadEntry = new Button("Save");
        uploadEntry.addClickListener(event -> listener.save(this.wikiEntry));

        wikiEntryInformationButtonContent.addComponents(editEntry, uploadEntry);
        wikiEntryInformationButtonContent.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        wikiEntryInformationContent.addComponents(wikiEntryInformation, wikiEntryInformationButtonContent);

        wikiEntryInformationContent.setComponentAlignment(wikiEntryInformation, Alignment.TOP_RIGHT);
        wikiEntryInformationContent.setComponentAlignment(wikiEntryInformationButtonContent, Alignment.MIDDLE_RIGHT);

        panelContent.addComponent(wikiEntryContent);
        wikiEntryContent.setWidth("100%");
        grid.addComponent(wikiEntryContent, 0, 0);
        grid.addComponent(wikiEntryInformationContent, 1, 0);
        grid.setColumnExpandRatio(0, 0.75f);
        grid.setColumnExpandRatio(1, 0.25f);
        grid.setComponentAlignment(wikiEntryInformationContent, Alignment.TOP_CENTER);
        this.setContent(grid);

    }
    private String convertDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
