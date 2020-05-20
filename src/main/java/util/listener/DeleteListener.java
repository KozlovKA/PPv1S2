package util.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import util.controller.Controller;
import util.model.Client;
import util.view.Window;

import java.util.ArrayList;
import java.util.List;

public class DeleteListener implements Listener {

    private Controller controller;
    private Window window;
    private Shell parent;

    public DeleteListener(Shell parent, Controller controller, Window window) {
        this.parent = parent;
        this.controller = controller;
        this.window = window;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent, SWT.SHELL_TRIM | SWT.RESIZE);
        child.setText("Deletion...");

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        child.setLayout(rowLayout);

        Label nameLabel = new Label(child, SWT.NONE);
        nameLabel.setText("Client name:");

        Text nameText = new Text(child, SWT.NONE);
        nameText.setLayoutData(new RowData(200, 20));

        Button nameSearchCheck = new Button(child, SWT.CHECK);
        nameSearchCheck.setText("Delete by name");

        Label accountNumbLabel = new Label(child, SWT.NONE);
        accountNumbLabel.setText("Account number:");

        Text accountNumbText = new Text(child, SWT.NONE);
        accountNumbText.setLayoutData(new RowData(200, 20));

        Button accountNumberCheck = new Button(child, SWT.CHECK);
        accountNumberCheck.setText("Delete by account number");

        Label adressLabel = new Label(child, SWT.NONE);
        adressLabel.setText("Client address:");

        Text adressText = new Text(child, SWT.NONE);
        adressText.setLayoutData(new RowData(200, 20));

        Button adressCheck = new Button(child, SWT.CHECK);
        adressCheck.setText("Delete by address");

        Label mobilePhoneLabel = new Label(child, SWT.NONE);
        mobilePhoneLabel.setText("Mobile phone number:");

        Text mobilePhoneText = new Text(child, SWT.NONE);
        mobilePhoneText.setLayoutData(new RowData(200, 20));

        Button mobilePhoneCheck = new Button(child, SWT.CHECK);
        mobilePhoneCheck.setText("Deleted by mobile phone number:");

        Label phoneLabel = new Label(child, SWT.NONE);
        phoneLabel.setText("Phone nubmer");

        Text phoneText = new Text(child, SWT.NONE);
        phoneText.setLayoutData(new RowData(200, 20));

        Button phoneCheck = new Button(child, SWT.CHECK);
        phoneCheck.setText("Delete by phone number:");

        Button deleteButton = new Button(child, SWT.NONE);
        deleteButton.setText("Delete");

        Listener proceedListener = new Listener() {
            @Override
            public void handleEvent(Event e) {

                List<Client> clients = new ArrayList<>();
                if (accountNumberCheck.getSelection()) {
                    String accountNum = accountNumbText.getText();
                    clients.addAll(controller.findByNumber(accountNum, controller.getAllClients()));
                }

                if (adressCheck.getSelection()) {
                    String address = adressText.getText();
                    clients.addAll(controller.findByAddress(address, controller.getAllClients()));

                }
                if (phoneCheck.getSelection()) {
                    int phone = Integer.parseInt(phoneCheck.getText());
                    clients.addAll(controller.findByPhone(phone, controller.getAllClients()));

                }
                if (mobilePhoneCheck.getSelection()) {
                    int mobilePhone = Integer.parseInt(mobilePhoneText.getText());
                    clients.addAll(controller.findByMobilePhone(mobilePhone, controller.getAllClients()));

                }
                if (nameSearchCheck.getSelection()) {
                    String name = nameText.getText();
                    clients = controller.findByName(name, controller.getAllClients());
                }
                // put deleted students into a `students`
                Shell dialog = new Shell(child);
                dialog.setText("Action");
                dialog.setLayout(new FillLayout());
                Label resultLabel = new Label(dialog, SWT.NONE);
                if (clients.size() != 0) {
                    controller.deleteClient(clients);
                    window.updateTable();
                    resultLabel.setText("Was deleted " + clients.size() + " client.");
                } else resultLabel.setText("No such client.");
                dialog.pack();
                dialog.open();
            }

        };
        deleteButton.addListener(SWT.MouseDown, proceedListener);
        child.pack();
        child.open();
    }
}