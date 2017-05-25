package ch.bfh.bti7081.s2017.yellow.views.wiki;

import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import com.vaadin.ui.Component;

/**
 * Created by taahuem2 on 25.05.17.
 */
public interface WikiView extends Component, NavigatorView {

    interface WikiViewListener extends NavigatorViewListener{

    }

    void addListener(WikiViewListener listener);

}
