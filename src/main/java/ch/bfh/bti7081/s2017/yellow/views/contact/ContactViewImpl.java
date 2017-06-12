package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete ContactView implementation displays a list of Contacts and a detailed contact view.
 * @author iSorp
 */
public class ContactViewImpl extends CustomComponent implements ContactView {

    private ContactViewListener listener;
    private Grid<ContactBookEntryBean> grid = new Grid<>();
    private List<ContactBookEntryBean> entries = new ArrayList<>();
    private TextField filterText = new TextField();

    /**
     * Default ContactViewImpl Constructor.
     *
     */
    public ContactViewImpl() {
        this.createLayout();
    }

    /**
     * Creates the Layout and the view components.
     */
    protected void createLayout() {
        final VerticalLayout layout = new VerticalLayout();

        filterText.setPlaceholder("filter by name...");
        filterText.addValueChangeListener(e -> listener.setFilter(e.getValue()));
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button();
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        HorizontalLayout toolbar = new HorizontalLayout(filtering);

        grid.setDataProvider(dataProvider);
        grid.addColumn(a->a.getPerson().getFirstName()).setCaption("First name");
        grid.addColumn(a->a.getPerson().getLastName()).setCaption("Last name");
        grid.addColumn(ContactBookEntryBean::getPhoneNr).setCaption("Phone number");

        HorizontalLayout main = new HorizontalLayout(grid);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(toolbar, main);

        grid.asSingleSelect().addValueChangeListener(event -> listener.selectionChange(event.getValue()));
        // Split and allow resizing
        setCompositionRoot(layout);
        setSizeFull();
        setVisible(false);
    }


    /**
     * GridView dataProvider for all contactBook entries.
     */
    DataProvider<ContactBookEntryBean, String> dataProvider = DataProvider.fromFilteringCallbacks(
            // First callback fetches items based on a query
            query -> entries.stream(),

            // Second callback fetches the number of items for a query
            query ->entries.size()
    );

    @Override
    public void setContactBookEntries(List<ContactBookEntryBean> entries) {
        this.entries = entries;
        dataProvider.refreshAll();
    }

    @Override
    public void addListener(ContactViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listener.changeView(viewChangeEvent);
        setVisible(true);
    }

}
