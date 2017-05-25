package ch.bfh.bti7081.s2017.yellow.views.wiki;

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

        layout.addComponent(new Label("<b>Hello!</b> - This is our Wiki?",
                ContentMode.HTML));

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
