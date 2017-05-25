package ch.bfh.bti7081.s2017.yellow.views.wiki;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;


/**
 * Created by taahuem2 on 25.05.17.
 */

public class WikiViewImpl extends CustomComponent implements WikiView   {

    private WikiViewListener listener;

    public WikiViewImpl() {
        final VerticalLayout layout = new VerticalLayout();

        VerticalLayout content = new VerticalLayout();
        content.setWidth("80%");

        Panel panel = new Panel("Astronomer Panel");
        VerticalLayout panelContent = new VerticalLayout();
        panel.setWidth("100%");
        panel.addStyleName("backgroundColorRed");

        Label title = new Label("Wiki Titel",  ContentMode.HTML);
        title.setStyleName("caption");
        Label wikiEntryContent = new Label(
                "\"In HTML mode, all HTML formatting tags, such as \\n\" +\n" +
                "    \"are preserved.\"", ContentMode.HTML);

        panelContent.addComponent(title);
        panelContent.addComponent(wikiEntryContent);
        panel.setContent(panelContent);
        content.addComponent(panel);

        layout.addComponent(content);
        layout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        setCompositionRoot(layout);
        setVisible(false);
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
