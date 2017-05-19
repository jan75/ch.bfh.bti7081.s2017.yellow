package ch.bfh.bti7081.s2017.yellow.views.contact;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Concrete ContactView implementation displays a list of Contacts and a detailed contact view.
 * @author iSorp
 */
public class ContactViewImpl extends CustomComponent implements ContactView {

    private ContactViewListener listener;
    private Grid<ContactBookEntryBean> grid = new Grid<>(ContactBookEntryBean.class);
    private TextField filterText = new TextField();

    public ContactViewImpl() {
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

        Button addCustomerBtn = new Button("Add new");
        addCustomerBtn.addClickListener(clickEvent -> listener.addContact());

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn);

        grid.setColumns("firstName", "lastName", "phoneNumber");

        HorizontalLayout main = new HorizontalLayout(grid);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(toolbar, main);

        grid.asSingleSelect().addValueChangeListener(event -> listener.selectionChange(event.getValue()));
        // Split and allow resizing
        setCompositionRoot(layout);
        setVisible(false);
    }

    @Override
    public void setDataProvider(DataProvider<ContactBookEntryBean, String> provider) {
        grid.setDataProvider(provider);
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
