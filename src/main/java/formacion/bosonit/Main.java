package formacion.bosonit;

import formacion.bosonit.controller.GameController;
import formacion.bosonit.utils.Utils;
import formacion.bosonit.view.GameView;

public class Main {
    public static void main(String[] args) {
        GameView view = new GameView("Batalla por la Tierra Media");
        Utils utils = new Utils();
        GameController controller = new GameController(utils, view);
    }
}