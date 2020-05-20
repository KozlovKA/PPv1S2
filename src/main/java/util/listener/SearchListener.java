package util.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import util.controller.Controller;
import util.model.Client;
import util.view.ClientTable;

import java.util.ArrayList;
import java.util.List;

public class SearchListener implements Listener {

    private Controller controller;
    private Shell parent;

    public SearchListener(Shell parent, Controller controller) {
        this.parent = parent;
        this.controller = controller;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent, SWT.SHELL_TRIM | SWT.RESIZE);
        child.setText("Search...");

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        child.setLayout(rowLayout);

        Label nameLabel = new Label(child, SWT.NONE);
        nameLabel.setText("Clients's name:");

        Text nameText = new Text(child, SWT.NONE);
        nameText.setLayoutData(new RowData(200, 20));

        Button nameSearchCheck = new Button(child, SWT.CHECK);
        nameSearchCheck.setText("Search by name");

        Label accountNumberLabel = new Label(child, SWT.NONE);
        accountNumberLabel.setText("Account number:");

        Text AccountNumberText = new Text(child, SWT.NONE);
        AccountNumberText.setLayoutData(new RowData(200, 20));

        Button AccountNumberSearchButton = new Button(child, SWT.CHECK);
        AccountNumberSearchButton.setText("Search by account number");


        Label addressLabel = new Label(child, SWT.NONE);
        addressLabel.setText("Address:");

        Text addressText = new Text(child, SWT.NONE);
        addressText.setLayoutData(new RowData(200, 20));

        Button addressButton = new Button(child, SWT.CHECK);
        addressButton.setText("Search by address");

        Label mobilePhoneLabel = new Label(child, SWT.NONE);
        mobilePhoneLabel.setText("Mobile phone:");

        Text mobilePhoneText = new Text(child, SWT.NONE);
        mobilePhoneText.setLayoutData(new RowData(200, 20));

        Button mobilePhoneButton = new Button(child, SWT.CHECK);
        mobilePhoneButton.setText("Search by Mobile phone");

        Label phoneLabel = new Label(child, SWT.NONE);
        phoneLabel.setText("Phone:");

        Text phoneText = new Text(child, SWT.NONE);
        phoneText.setLayoutData(new RowData(200, 20));

        Button phoneButton = new Button(child, SWT.CHECK);
        phoneButton.setText("Search by phone");


        Button searchButton = new Button(child, SWT.PUSH);
        searchButton.setText("Find");

        ClientTable table = new ClientTable(child, SWT.NONE, controller);

        Listener proceedListener = new Listener() {

            @Override
            public void handleEvent(Event e) {
                List<Client> clients = new ArrayList<>();

                if (nameSearchCheck.getSelection()) {
                    String name = nameText.getText();
                    clients.addAll(controller.findByName(name, controller.getAllClients()));
                }

                if (AccountNumberSearchButton.getSelection()) {
                    String accountNumber = AccountNumberText.getText();
                    clients.addAll(controller.findByNumber(accountNumber, controller.getAllClients()));
                }
                if (addressButton.getSelection()) {
                    String address = addressText.getText();
                    clients.addAll(controller.findByAddress(address, controller.getAllClients()));
                }
                if (mobilePhoneButton.getSelection()) {
                    int mobilePhone = Integer.parseInt(mobilePhoneText.getText());
                    clients.addAll(controller.findByMobilePhone(mobilePhone, controller.getAllClients()));
                }
                if (phoneButton.getSelection()) {
                    int phone = Integer.parseInt(phoneText.getText());
                    clients.addAll(controller.findByPhone(phone, controller.getAllClients()));
                }

                table.updateTable(clients);
            }
        };

        searchButton.addListener(SWT.MouseDown, proceedListener);

        //child.pack();
        child.open();
    }
}