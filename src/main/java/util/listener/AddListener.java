package util.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import util.controller.Controller;
import util.view.Window;

public class AddListener implements Listener {

    private Controller controller;
    private Window window;
    private Shell parent;

    public AddListener(Shell parent, Controller controller, Window window) {
        this.parent = parent;
        this.controller = controller;
        this.window = window;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent);
        child.setText("Adding...");

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        child.setLayout(rowLayout);

        Label clientNameLabel = new Label(child, SWT.NONE);
        clientNameLabel.setText("Put client name:");

        Text clientNameText = new Text(child, SWT.NONE);
        clientNameText.setLayoutData(new RowData(200, 20));

        Label accountNumb = new Label(child, SWT.NONE);
        accountNumb.setText("Put account number");

        Text accountNumbText = new Text(child, SWT.NONE);
        accountNumbText.setLayoutData(new RowData(200, 20));

        Label address = new Label(child, SWT.NONE);
        address.setText("Put address:");

        Text addressText = new Text(child, SWT.NONE);
        addressText.setLayoutData(new RowData(200, 20));

        Label mobilePhone = new Label(child, SWT.NONE);
        mobilePhone.setText("Put mobile phone:");

        Text mobilePhoneText = new Text(child, SWT.NONE);
        mobilePhoneText.setLayoutData(new RowData(200, 20));

        Label phone = new Label(child, SWT.NONE);
        phone.setText("Put phone:");

        Text phoneText = new Text(child, SWT.NONE);
        phoneText.setLayoutData(new RowData(200, 20));

        Button proceedButton = new Button(child, SWT.PUSH);
        proceedButton.setText("Add");
        proceedButton.setLayoutData(new RowData(100, 30));

        Listener proceedListener = new Listener() {

            @Override
            public void handleEvent(Event arg0) {

                String stName = clientNameText.getText();
                String accountNumber = accountNumbText.getText();
                String adress = address.getText();
                int bad = Integer.parseInt(mobilePhoneText.getText());
                int another = Integer.parseInt(phoneText.getText());

                controller.addClient(stName,
                        accountNumber,
                        adress,
                        bad,
                        another);
                window.updateTable();
                child.dispose();
            }

        };
        proceedButton.addListener(SWT.MouseDown, proceedListener);

        child.pack();
        child.open();
    }
}