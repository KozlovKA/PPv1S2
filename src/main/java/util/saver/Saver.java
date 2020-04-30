package util.saver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import util.model.Client;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class Saver {
    public void save(List<Client> clients, String filePath) {
        try {
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Clients");
            doc.appendChild(rootElement);

            for (Client client : clients) {
                Element studentElement = doc.createElement("Client");
                rootElement.appendChild(studentElement);

                Element studName = doc.createElement("Name");
                studName.appendChild(doc.createTextNode(client.getName()));
                studentElement.appendChild(studName);

                Element group = doc.createElement("AccountNumber");
                group.appendChild(doc.createTextNode(client.getAccountNumber()));
                studentElement.appendChild(group);

                Element ilnessMissingsCount = doc.createElement("Address");
                ilnessMissingsCount.appendChild(doc.createTextNode(String.valueOf(client.getAddress())));
                studentElement.appendChild(ilnessMissingsCount);

                Element withoutAppropriateReasonCount = doc.createElement("MobilePhoneNumber");
                withoutAppropriateReasonCount.appendChild(doc.createTextNode(String.valueOf(client.getMobilePhoneNumber())));
                studentElement.appendChild(withoutAppropriateReasonCount);

                Element anotherReasonCount = doc.createElement("PhoneNumber");
                anotherReasonCount.appendChild(doc.createTextNode(String.valueOf(client.getPhoneNumber())));
                studentElement.appendChild(anotherReasonCount);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}