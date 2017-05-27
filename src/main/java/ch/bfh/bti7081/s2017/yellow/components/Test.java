package ch.bfh.bti7081.s2017.yellow.components;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeeBean;
import ch.bfh.bti7081.s2017.yellow.components.client.TestClientRpc;
import ch.bfh.bti7081.s2017.yellow.components.client.TestServerRpc;
import ch.bfh.bti7081.s2017.yellow.components.client.TestState;
import com.vaadin.ui.AbstractComponent;

/**
 * Created by jan on 5/23/17.
 */
public class Test extends AbstractComponent {
    private EmployeeBean employee = null;

    public Test() {
        registerRpc(new TestServerRpc() {
            private TestClientRpc getClientRpc() {
                return getRpcProxy(TestClientRpc.class);
            }
        });
    }

    public void setEmployee(EmployeeBean employee) {
        this.employee = employee;
    }

    @Override
    protected TestState getState() {
        return (TestState) super.getState();
    }
}
