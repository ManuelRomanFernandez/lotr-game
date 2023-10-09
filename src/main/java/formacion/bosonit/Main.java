package formacion.bosonit;

import formacion.bosonit.controller.GameController;
import formacion.bosonit.utils.Utils;
import formacion.bosonit.view.GameViewImpl;

public class Main {
    public static void main(String[] args) {
        GameViewImpl gameView = new GameViewImpl();
        Utils utils = new Utils();
        GameController controller = new GameController(gameView, utils);

        controller.init();
    }
}