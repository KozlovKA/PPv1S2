package util.loader;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import util.model.Client;

import java.util.ArrayList;
import java.util.List;

public class LoaderHandler extends DefaultHandler {
    private boolean bName = false;
    private boolean bGroup = false;
    private boolean bIlnessMissingsCount = false;
    private boolean bWithoutAppropriateReasonCount = false;
    private boolean bAnotherReasonCount = false;

    private List<Client> clients = null;
    private Client client = null;
    private StringBuilder data;

    public List<Client> getClients() {
        return clients;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(qName.equalsIgnoreCase("Client")) {
            client = new Client();
            if(clients == null) {
                clients = new ArrayList<Client>();
            }
        }
        else if (qName.equalsIgnoreCase("Name")) {
            bName = true;
        }
        else if (qName.equalsIgnoreCase("AccountNumber")) {
            bGroup = true;
        }
        else if (qName.equalsIgnoreCase("Address")) {
            bIlnessMissingsCount = true;
        }
        else if (qName.equalsIgnoreCase("MobilePhoneNumber")) {
            bWithoutAppropriateReasonCount = true;
        }
        else if (qName.equalsIgnoreCase("PhoneNumber")) {
            bAnotherReasonCount = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(bName) {
            client.setName(data.toString());
            bName = false;
        }
        else if (bGroup) {
            client.setAccountNumber(data.toString());
            bGroup = false;
        }
        else if (bIlnessMissingsCount) {
            client.setAddress(data.toString());
            bIlnessMissingsCount = false;
        }
        else if (bWithoutAppropriateReasonCount) {
            client.setMobilePhoneNumber(Integer.parseInt(data.toString()));
            bWithoutAppropriateReasonCount = false;
        }
        else if (bAnotherReasonCount) {
            client.setPhoneNumber(Integer.parseInt(data.toString()));
            bAnotherReasonCount = false;
        }
        if (qName.equalsIgnoreCase("Client")) {
            clients.add(client);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }
}