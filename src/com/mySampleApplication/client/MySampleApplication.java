package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // idea generated example vvv
        final Button button = new Button("Click me");
        final Label label = new Label();

        button.addClickHandler(event -> {
            if (label.getText().equals("")) {
                MySampleApplicationService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
            } else {
                label.setText("");
            }
        });

        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //
        RootPanel.get("slot1").add(button);
        RootPanel.get("slot2").add(label);
        // idea generated example ^^^

        // this is my code vvv

        // instantiate of calculator service
        final Calculator calculator = new Calculator();

        // button (with = symbol on it) which click on runs calculation
        final Button equalsButton = new Button(" = ");

        // list <select> with supported operations!
        final ListBox operationBox = new ListBox();

        // add supported operations to the list as <option>s
        calculator.supportedOperations().forEach(operationBox::addItem);

        // input fields to enter numbers
        final DoubleBox argBox1 = new DoubleBox();
        final DoubleBox argBox2 = new DoubleBox();

        // label to display the output (empty on startup)
        final Label output = new Label();

        // change label to result of calculation
        equalsButton.addClickHandler(e -> {
            Double result = calculator.calculate(
                    operationBox.getSelectedValue(),
                    argBox1.getValue(),
                    argBox2.getValue()
            );
            output.setText(String.valueOf(result));
        });

        // bind html objects with java objects
        RootPanel.get("argSlot1").add(argBox1);
        RootPanel.get("argSlot2").add(argBox2);
        RootPanel.get("operationSlot").add(operationBox);
        RootPanel.get("buttonSlot").add(equalsButton);
        RootPanel.get("outputSlot").add(output);
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
