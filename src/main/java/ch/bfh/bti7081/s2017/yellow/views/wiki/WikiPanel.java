package ch.bfh.bti7081.s2017.yellow.views.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by theonlyandone on 12.06.17.
 */
public class WikiPanel extends Panel {

    private WikiEntry wikiEntry;
    private TextArea wikiEntryContent;
    private Label wikiEntryInformation;
    private Boolean readOnly;

    private Button saveEntry;
    private Button editEntry;

    public WikiPanel(WikiEntry wikiEntry, WikiView.WikiViewListener listener) {

        super(wikiEntry.getCaption());

        this.wikiEntry = wikiEntry;
        this.readOnly = true;
        this.setSizeFull();

        GridLayout grid = new GridLayout(2, 1);
        grid.setSizeFull();

        wikiEntryContent = new TextArea( ); //wikiEntry.getEntry(), ContentMode.HTML);
        wikiEntryContent.setValue(wikiEntry.getEntry());
        wikiEntryContent.setStyleName("wikiEntryContentTextArea");
        wikiEntryContent.setReadOnly(this.readOnly);
        wikiEntryContent.setSizeFull();

        VerticalLayout wikiEntryInformationContent = new VerticalLayout();
        wikiEntryInformationContent.setStyleName("wikiEntryInformationContent");

        wikiEntryInformation = new Label(getWikiEntryInformation(), ContentMode.HTML);

        HorizontalLayout wikiEntryInformationButtonContent = new HorizontalLayout();

        editEntry = new Button("Bearbeiten");
        editEntry.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                listener.edit(wikiEntry);
                toggleReadOnly();
            }
        });

        saveEntry = new Button("Speichern");
        saveEntry.setVisible(false);
        saveEntry.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                wikiEntry.setEntry(wikiEntryContent.getValue());
                wikiEntry.setUpdatedAt(new Date());
                wikiEntryInformation.setValue(getWikiEntryInformation());
                listener.save(wikiEntry);
                toggleReadOnly();
            }
        });

        wikiEntryInformationButtonContent.addComponents(editEntry, saveEntry);
        wikiEntryInformationButtonContent.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        wikiEntryInformationContent.addComponents(wikiEntryInformation, wikiEntryInformationButtonContent);
        wikiEntryInformationContent.setComponentAlignment(wikiEntryInformation, Alignment.TOP_RIGHT);
        wikiEntryInformationContent.setComponentAlignment(wikiEntryInformationButtonContent, Alignment.MIDDLE_RIGHT);
        wikiEntryInformationContent.setWidth("280px");

        grid.addComponent(wikiEntryContent, 0, 0);
        grid.addComponent(wikiEntryInformationContent, 1, 0);
        grid.setColumnExpandRatio(0, 1.0f);
        grid.setColumnExpandRatio(1, 0f);
        grid.setComponentAlignment(wikiEntryInformationContent, Alignment.TOP_CENTER);

        this.setContent(grid);

    }

    public String getWikiEntryInformation() {

        String category = wikiEntry.getCategory();
        String createdFrom = wikiEntry.getUser().getFirstName() + " " + wikiEntry.getUser().getLastName(); // "Michi Jackson";
        String createdAt = convertDateToString(wikiEntry.getCreatedAt());
        String updatedAt = convertDateToString(wikiEntry.getUpdatedAt());

        return  "<table>\n" +
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
                "  </tr>\n";
    }

    public void toggleReadOnly() {
        this.readOnly = !this.readOnly;

        if (readOnly) {
            editEntry.setCaption("Bearbeiten");
            wikiEntryContent.removeStyleName("wikiEntryContentInEditMode");
        }
        else {
            editEntry.setCaption("Abbrechen");
            wikiEntryContent.addStyleName("wikiEntryContentInEditMode");
        }

        this.saveEntry.setVisible(!this.saveEntry.isVisible());
        this.wikiEntryContent.setReadOnly(readOnly);
    }

    private String convertDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
