package util.controller;

import util.loader.Loader;
import util.model.Client;
import util.saver.Saver;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Client> clients;
    private Loader loader;
    private Saver saver;

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
}


