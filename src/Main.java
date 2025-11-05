import controllers.AppController;
import utils.Loader;
import views.MainFrame;

import java.util.List;
import java.util.Map;

/**
 * @author Hoang
 * @date 11/4/2025
 */
public class Main {


    public static void main(String[] args) {
        new MainFrame(new AppController());
    }

}
