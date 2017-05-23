package ch.bfh.bti7081.s2017.yellow.components.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
/**
 * Created by jan on 5/23/17.
 */
public class TestWidget extends Composite {
    private final static String CLASSNAME = "v-testwidget";
    Label label = null;

    public TestWidget() {
        //setStyleName(CLASSNAME);
        
        FlowPanel panel = new FlowPanel();
		
        label = new Label("TestWidget");
        panel.add(label);

        initWidget(panel);
    }
}
