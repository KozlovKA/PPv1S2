package util.controller;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import util.loader.Loader;
import util.model.Client;
import util.saver.Saver;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Client> clients;
    private List<String> clientsName;
    private List<String> clientsAccountNumber;
    private List<String> clientsAddress;
    private List<Integer> clientsMobilePhoneNumber;
    private List<Integer> clientsPhoneNumber;
    private Loader loader;
    private Saver saver;
    private Text numOfClientText;
    private Label pagesIndicatorLabel;
    private int numOfClientsOnPage = 10;
    private int pageNum = 0;

    public Controller(List<Client> clients) {
        this.clients = clients;
        loader = new Loader();
        saver = new Saver();
    }

    public void load(String filePath) {
        this.clients = loader.load(filePath);
    }

    public void save(String filePath) {
        saver.save(this.clients, filePath);
    }


    public List<String> getAllAccountNumber(){
      return clientsName;
    }
    public List<String> getAllAddress(){
        return clientsAddress;
    }
    public List<String> getAllNames(){
        return clientsName;
    }
    public List<Integer> getAllPhoneNumber(){
        return clientsPhoneNumber;
    }
    public List<Integer> getAllMobilePhoneNumber(){
        return clientsMobilePhoneNumber;
    }


    public List<Client> getAllClients() {
        return clients;
    }


    public void addClient(String name, String accNum, String adress, int mobilePhoneNumber, int phoneNumber) {
        Client client = new Client();
        client.setName(name);
        client.setAccountNumber(accNum);
        client.setAddress(adress);
        client.setMobilePhoneNumber(mobilePhoneNumber);
        client.setPhoneNumber(phoneNumber);

        clients.add(client);
    }

    public void deleteClient(List<Client> clients) {
        for (Client client : clients) {
            this.clients.remove(client);
        }
    }


    public List<Client> findByName(String name, List<Client> clientList) {
        List<Client> clients = new ArrayList<Client>();
        for (Client client : clientList) {
            if (client.getName().contains(name)) {
                clients.add(client);
            }
        }
        return clients;
    }

    public List<Client> findByNumber(String number, List<Client> clientList) {
        List<Client> clients = new ArrayList<Client>();
        for (Client client : clientList) {
            if (client.getAccountNumber().contains(number)) {
                clients.add(client);
            }
        }
        return clients;
    }

    public List<Client> findByAdress(String adress, List<Client> clientList) {
        List<Client> clients = new ArrayList<Client>();
        for (Client client : clientList) {
            if (client.getAddress() == adress) {
                clients.add(client);
            }
        }
        return clients;
    }

    public List<Client> findByPhone(int phone, List<Client> clientList) {
        List<Client> clients = new ArrayList<Client>();
        for (Client client : clientList) {
            if (client.getPhoneNumber() == phone) {
                clients.add(client);
            }
        }
        return clients;
    }

    public List<Client> findByMobilePhone(int mobilePhone, List<Client> clientList) {
        List<Client> clients = new ArrayList<Client>();
        for (Client client : clientList) {
            if (client.getMobilePhoneNumber() == mobilePhone) {
                clients.add(client);
            }
        }
        return clients;
    }

    public List<Client> getClientPage(int index, int numOfClientsOnPage, List<Client> clients) {
        List<List<Client>> pages = calculatePages(numOfClientsOnPage, clients);
        if (!pages.isEmpty()) {
            return pages.get(index);
        } else {
            List<Client> page = new ArrayList<Client>();
            return page;
        }
    }

    public List<List<Client>> calculatePages(int numOfClientsOnPage, List<Client> clients) {
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
