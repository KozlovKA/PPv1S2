import util.controller.Controller;
import util.model.Client;
import util.view.Window;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new ArrayList<Client>());
        Window window = new Window(controller);
        window.start();
    }
}