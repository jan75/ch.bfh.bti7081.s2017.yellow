package ch.bfh.bti7081.s2017.yellow.views.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import com.vaadin.ui.Component;

/**
 * Interface for wikiViewImpl class. Supports public methods for the presenter.
 * @author theonlyandone
 * @see WikiViewImpl
 */
public interface WikiView extends Component, NavigatorView {

    interface WikiViewListener extends NavigatorViewListener{
        void save(WikiEntry entry);
        void edit(WikiEntry entry);
    }

    void addListener(WikiViewListener listener);
    void updateWiki(Wiki wiki);

}
