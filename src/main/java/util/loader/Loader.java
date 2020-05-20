package util.loader;

import util.model.Client;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class Loader {
    public List<Client> load(String filePath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            LoaderHandler handler = new LoaderHandler();
            saxParser.parse(new File(filePath), handler);
            return handler.getClients();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}