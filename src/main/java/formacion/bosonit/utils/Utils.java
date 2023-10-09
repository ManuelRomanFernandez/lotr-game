package formacion.bosonit.utils;

public class Utils {
    public void gameDelay(int mil) {
        try {
            Thread.sleep(mil);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
