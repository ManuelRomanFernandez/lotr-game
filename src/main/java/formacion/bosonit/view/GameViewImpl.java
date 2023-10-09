package formacion.bosonit.view;

import formacion.bosonit.model.Unit;
import formacion.bosonit.model.beast.Beast;
import formacion.bosonit.model.hero.Hero;

import java.util.List;

public class GameViewImpl{
    public void displayWelcome() {
        System.out.println("******************************************************************************");
        System.out.println("*          ¡ Bienvenidos a la Batalla por la Tierra Media !                  *");
        System.out.println("* Este programa está diseñado para ofrecer una serie de combates automáticos *");
        System.out.println("*         en los cuales podéis elegir las unidades de los dos bandos:        *");
        System.out.println("*                          Heroes - VS - Bestias                             *");
        System.out.println("******************************************************************************");
        displaySpace();
    }

    public void displayHeroBanner() {
        System.out.println("******************************************************************************");
        System.out.println("*                  Construye el ejercito de los Heroes                       *");
        System.out.println("******************************************************************************");
        displaySpace();
    }

    public void displayHeroRaceSelection() {
        System.out.println("******************************************************************************");
        System.out.println("* Elige que tipo de unidad añadir escribiendo por consola su numero:         *");
        System.out.println("* 1. Humano || 2. Elfo || 3. Hobbit                                          *");
        System.out.println("******************************************************************************");
    }

    public void displayCurrentHeroTeam(List<Hero> heroTeam) {
        System.out.println("******************************************************************************");
        System.out.println("* El ejercito de los Heroes está compuesto por:                              *");
        heroTeam.forEach(hero -> {
            System.out.println("* - " + hero.printHeroInfo());
        });
        System.out.println(" ");
        System.out.println("* Introduce 1 por consola para añadir otro Héroe.                            *");
        System.out.println("* Introduce cualquier otro valor para acabar con la selección de Héroes.     *");
        System.out.println("******************************************************************************");
    }

    public void displayBeastBanner() {
        System.out.println("******************************************************************************");
        System.out.println("*                 Construye el ejercito de las Bestias                       *");
        System.out.println("******************************************************************************");
        displaySpace();
    }

    public void displayBeastRaceSelection() {
        System.out.println("******************************************************************************");
        System.out.println("* Elige que tipo de unidad añadir escribiendo por consola su numero:         *");
        System.out.println("* 1. Orco || 2. Trasgo                                                       *");
        System.out.println("******************************************************************************");
    }

    public void displayUnitNameSelection(String race) {
        System.out.println("******************************************************************************");
        System.out.println(" Escribe el nombre de tu " + race + ":");
        System.out.println("******************************************************************************");
    }

    public void displayUnitHealthSelection() {
        System.out.println("******************************************************************************");
        System.out.println(" Introduce los puntos de vida (recomendable entre 100 y 300):");
        System.out.println("******************************************************************************");
    }

    public void displayUnitArmorSelection() {
        System.out.println("******************************************************************************");
        System.out.println(" Introduce los puntos de armadura (recomendable entre 30 y 60):");
        System.out.println("******************************************************************************");
    }

    public void displayCurrentBeastTeam(List<Beast> beastTeam) {
        System.out.println("******************************************************************************");
        System.out.println(" El ejercito de las Bestias está compuesto por:");
        beastTeam.forEach(beast -> {
            System.out.println(" - " + beast.printBeastInfo());
        });
        System.out.println(" ");
        System.out.println(" Introduce 1 por consola para añadir otra Bestia ");
        System.out.println(" Introduce cualquier otro valor para acabar con la selección de Bestias ");
        System.out.println("******************************************************************************");
    }

    public void displayTeams(List<Hero> heroTeam, List<Beast> beastTeam) {
        System.out.println("******************************************************************************");
        System.out.println("* <<<<<<<<<<<<<<<<<<<<<<<<><<<<<< Héroes >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> *");
        heroTeam.forEach(hero -> {
            System.out.println("* - " + hero.printHeroInfo());
        });
        displaySpace();
        System.out.println("* <<<<<<<<<<<<<<<<<<<<<<<<><<<<< Bestias >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> *");
        beastTeam.forEach(beast -> {
            System.out.println("* - " + beast.printBeastInfo());
        });
        System.out.println("******************************************************************************");
        displaySpace();
    }

    public void displayGameStart() {
        System.out.println("******************************************************************************");
        System.out.println("*                  ¡Comienza la batalla por la Tierra Media!                 *");
        System.out.println("******************************************************************************");
        displaySpace();
    }

    public void displayTurn(int turn) {
        System.out.println("******************************************************************************");
        System.out.println("                                   Turno " + turn);
        System.out.println("******************************************************************************");
    }

    public void displayFightWarning(Hero hero, Beast beast) {
        System.out.println(" Lucha entre " + hero.printUnitStats() + " y " + beast.printUnitStats());
    }

    public void displayFightFirstAttack(Unit attacker, Unit defender, int[] attack) {
        System.out.println(" " + attacker.getName() + " ataca primero, saca " + (int)attack[0] + " y le quita " + (int)attack[1] + " puntos de vida a " + defender.getName());
    }
    public void displayFightSecondAttack(Unit attacker, Unit defender, int[] attack) {
        System.out.println(" " + attacker.getName() + " responde, saca " + (int)attack[0] + " y le quita " + (int)attack[1] + " puntos de vida a " + defender.getName());
    }

    public void displayHeroDeath(Hero hero) {
        System.out.println(" ¡Muere " + hero.printHeroTitle() + "!");
    }

    public void displayBeastDeath(Beast beast) {
        System.out.println(" ¡Muere " + beast.printBeastTitle() + "!");
    }

    public void displayHeroWin() {
        System.out.println("******************************************************************************");
        System.out.println("*                         ¡Victoria de los Héroes!                           *");
        System.out.println("*                    La Tierra Media ha sido salvada                         *");
        System.out.println("******************************************************************************");
    }

    public void displayBeastWin() {
        System.out.println("******************************************************************************");
        System.out.println("*                        ¡Victoria de las Bestias!                           *");
        System.out.println("*                   La Tierra Media ha sido condenada                        *");
        System.out.println("******************************************************************************");
    }

    public void displayErrorNumber() {
        System.out.println("******************************************************************************");
        System.out.println("* No has escogido un número válido. Intentalo de nuevo:                      *");
        System.out.println("******************************************************************************");
    }

    public void displaySpace(){
        System.out.println(" ");
    }
}
