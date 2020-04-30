package util.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import util.controller.Controller;
import util.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientTable extends Composite {
    private Table table;
    private Controller controller;
    private int numOfClientsOnPage = 10;
    private int pageNum = 0;
    private Text numOfClientText;
    private Label pagesIndicatorLabel;

    private List<Client> searchClientList;

    public ClientTable(Composite parent, int style, Controller controller) {
        super(parent, style);

        RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        this.setLayout(rowLayout);

        table = new Table(this, style);
        table.setHeaderVisible(true);
        String[] titles = {"Name", "Account Number",
                "Address",
                "Mobile Phone Number",
                "Phone Number",
        };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }
        this.controller = controller;

        Button firstPageButton = new Button(this, SWT.PUSH);
        firstPageButton.setText("<<");
        firstPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                pageNum = 0;
                if (searchClientList.isEmpty()) {
                    updateTable();
                } else updateTable(searchClientList);
            }
        });

        Button previousPageButton = new Button(this, SWT.PUSH);
        previousPageButton.setText("<");
        previousPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (pageNum - 1 >= 0) {
                    pageNum--;
                    if (searchClientList.isEmpty()) {
                        updateTable();
                    } else updateTable(searchClientList);
                }
            }
        });

        Button nextPageButton = new Button(this, SWT.PUSH);
        nextPageButton.setText(">");
        nextPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (searchClientList.isEmpty()) {
                    if (pageNum + 1 < Math.ceil((double) controller.getAllClients().size() / numOfClientsOnPage)) {
                        pageNum++;
                        updateTable();
                    }
                } else {
                    if (pageNum + 1 < Math.ceil((double) searchClientList.size() / numOfClientsOnPage)) {
                        pageNum++;
                        updateTable(searchClientList);
                    }
                }
            }
        });

        Button lastPageButton = new Button(this, SWT.PUSH);
        lastPageButton.setText(">>");
        lastPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (searchClientList.isEmpty()) {
                    pageNum = (int) Math.ceil((double) controller.getAllClients().size() / numOfClientsOnPage) - 1;
                    updateTable();
                } else {
                    pageNum = (int) Math.ceil((double) searchClientList.size() / numOfClientsOnPage - 1);
                    updateTable(searchClientList);
                }
            }
        });

        numOfClientText = new Text(this, SWT.NONE);
        numOfClientText.setText("1");

        searchClientList = new ArrayList<Client>();

        ModifyListener modifyListener = new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                try {
                    numOfClientsOnPage = Integer.parseInt(numOfClientText.getText());
                    pageNum = 0;
                    if (searchClientList.isEmpty()) {
                        updateTable();
                    } else updateTable(searchClientList);
                } catch (Exception exception) {
                    exception.getStackTrace();
                }
            }
        };

        numOfClientText.addModifyListener(modifyListener);

        pagesIndicatorLabel = new Label(this, SWT.NONE);
        pagesIndicatorLabel.setText("1/1");

        this.pack();
        table.pack();
        super.pack();
    }

    private void fillTheTable(List<Client> clients) {
        table.removeAll();
        for (Client client : clients) {
            try {
                if (client.getName() == null || client.getAccountNumber() == null)
                    throw new Exception("No clients!");
            } catch (Exception ex) {
                System.exit(1);
            }

            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, client.getName());
            item.setText(1, client.getAccountNumber());
            item.setText(2, client.getAddress());
            item.setText(3, Integer.toString(client.getMobilePhoneNumber()));
            item.setText(4, Integer.toString(client.getPhoneNumber()));
        }
    }

    public void updateTable() {
        List<Client> clients = getClientPage(pageNum, numOfClientsOnPage, controller.getAllClients());
        fillTheTable(clients);
        pagesIndicatorLabel.setText((pageNum + 1) + "/" + (int) Math.ceil((double) controller.getAllClients().size() / numOfClientsOnPage));
        table.pack();
        this.pack();
        //super.pack();
    }


    public void updateTable(List<Client> clientsList) {
        searchClientList = clientsList;
        List<Client> clients = getClientPage(pageNum, numOfClientsOnPage, clientsList);
        fillTheTable(clients);
        pagesIndicatorLabel.setText((pageNum + 1) + "/" + (int) Math.ceil((double) searchClientList.size() / numOfClientsOnPage));
        table.pack();
        this.pack();
        //super.pack();
    }

    private List<Client> getClientPage(int index, int numOfClientsOnPage, List<Client> clients) {
        List<List<Client>> pages = calculatePages(numOfClientsOnPage, clients);
        if (!pages.isEmpty()) {
            return pages.get(index);
        } else {
            List<Client> page = new ArrayList<Client>();
            return page;
        }
    }

    private List<List<Client>> calculatePages(int numOfClientsOnPage, List<Client> clients) {
        List<List<Client>> pages = new ArrayList<List<Client>>();
        int numOfPages = (int) Math.ceil((double) clients.size() / numOfClientsOnPage);

        for (int j = 0; j < numOfPages; j++) {
            List<Client> clientPage = new ArrayList<Client>();
            for (int i = numOfClientsOnPage * j; i < numOfClientsOnPage * j + numOfClientsOnPage; i++) {
                try {
                    clientPage.add(clients.get(i));
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            pages.add(clientPage);
        }
        return pages;
    }
}