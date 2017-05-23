package ch.bfh.bti7081.s2017.yellow.components.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Created by jan on 5/23/17.
 */
@Connect(ch.bfh.bti7081.s2017.yellow.components.Test.class)
public class TestConnector extends AbstractComponentConnector {
    private final TestServerRpc serverRpc = RpcProxy.create(TestServerRpc.class, this);

    public TestConnector() {
        registerRpc(TestClientRpc.class, new TestClientRpc() {

        });
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(TestWidget.class);
    }

    @Override
    public TestWidget getWidget() {
        return (TestWidget) super.getWidget();
    }

    @Override
    public TestState getState() {
        return (TestState) super.getState();
    }
}
