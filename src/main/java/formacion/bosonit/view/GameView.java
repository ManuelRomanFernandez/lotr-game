package formacion.bosonit.view;

import formacion.bosonit.model.Unit;
import formacion.bosonit.model.beast.Beast;
import formacion.bosonit.model.beast.BeastRace;
import formacion.bosonit.model.hero.Hero;
import formacion.bosonit.model.hero.HeroRace;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class GameView extends JFrame{
    private JPanel mainPanel;

    private JPanel heroSelectionMainPanel;
    private JLabel heroSelectionLabel;
    private JPanel heroSelectionSubPanel;
    private JPanel heroNameSelectionPanel;
    private JLabel heroNameSelectionLabel;
    private JTextField heroNameSelectionInput;
    private JPanel heroRaceSelectionPanel;
    private JLabel heroRaceSelectionLabel;
    private JComboBox<HeroRace> heroRaceSelectionDrop;
    private JPanel heroHealthSelectionPanel;
    private JLabel heroHealthSelectionLabel;
    private JSpinner heroHealthSelectionSpinner;
    private JPanel heroArmorSelectionPanel;
    private JLabel heroArmorSelectionLabel;
    private JSpinner heroArmorSelectionSpinner;
    private JButton heroAddButton;

    private JPanel beastSelectionMainPanel;
    private JLabel beastSelectionLabel;
    private JPanel beastSelectionSubPanel;
    private JPanel beastNameSelectionPanel;
    private JLabel beastNameSelectionLabel;
    private JTextField beastNameSelectionInput;
    private JPanel beastRaceSelectionPanel;
    private JLabel beastRaceSelectionLabel;
    private JComboBox<BeastRace> beastRaceSelectionDrop;
    private JPanel beastHealthSelectionPanel;
    private JLabel beastHealthSelectionLabel;
    private JSpinner beastHealthSelectionSpinner;
    private JPanel beastArmorSelectionPanel;
    private JLabel beastArmorSelectionLabel;
    private JSpinner beastArmorSelectionSpinner;
    private JButton beastAddButton;

    private JPanel fightMainPanel;
    private JLabel fightLabel;
    private JPanel fightSubPanel;

    private JPanel heroListPanel;
    private JLabel heroListLabel;
    private JScrollPane heroListSubPanel;
    private JList<String> heroList;
    private JButton heroUpButton;
    private JButton heroDownButton;
    private JButton heroRemoveButton;

    private JPanel beastListPanel;
    private JLabel beastListLabel;
    private JScrollPane beastListSubPanel;
    private JList<String> beastList;
    private JButton beastUpButton;
    private JButton beastDownButton;
    private JButton beastRemoveBButton;

    private JButton startFightButton;

    private JScrollPane fightLogPanel;
    private JTextArea fightLog;

    public GameView(String title) throws HeadlessException {
        super(title);

        SpinnerModel heroHealthModel = new SpinnerNumberModel(120,100,999,1);
        this.getHeroHealthSelectionSpinner().setModel(heroHealthModel);
        SpinnerModel beastHealthModel = new SpinnerNumberModel(120,100,999,1);
        this.getBeastHealthSelectionSpinner().setModel(beastHealthModel);

        SpinnerModel heroArmorModel = new SpinnerNumberModel(35,10,80,1);
        this.getHeroArmorSelectionSpinner().setModel(heroArmorModel);
        SpinnerModel beastArmorModel = new SpinnerNumberModel(35,10,80,1);
        this.getBeastArmorSelectionSpinner().setModel(beastArmorModel);

        this.setContentPane(this.mainPanel);
        this.setTitle("Batalla por la Tierra Media");
        this.setSize(750, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayGameStart() {
        this.getFightLog().append("******************************************************************************\n");
        this.getFightLog().append("                         ¡Comienza la batalla por la Tierra Media!\n");
        this.getFightLog().append("******************************************************************************\n");
        this.getFightLog().append("\n");
    }

    public void displayTurn(int turn) {
        this.getFightLog().append("******************************************************************************\n");
        this.getFightLog().append("                                                      Turno " + turn + "\n");
        this.getFightLog().append("******************************************************************************\n\n");
    }

    public void displayFightWarning(Hero hero, Beast beast) {
        this.getFightLog().append(" Lucha entre " + hero.printUnitStats() + " y " + beast.printUnitStats() + "\n");
    }

    public void displayFightFirstAttack(Unit attacker, Unit defender, int[] attack) {
        this.getFightLog().append(" " + attacker.getName() + " ataca primero, saca " + attack[0] + " y le quita " + attack[1] + " puntos de vida a " + defender.getName() + "\n");
    }
    public void displayFightSecondAttack(Unit attacker, Unit defender, int[] attack) {
        this.getFightLog().append(" " + attacker.getName() + " responde, saca " + attack[0] + " y le quita " + attack[1] + " puntos de vida a " + defender.getName() + "\n\n");
    }

    public void displayHeroDeath(Hero hero) {
        this.getFightLog().append(" ¡Muere " + hero.printHeroTitle() + "!\n\n");
    }

    public void displayBeastDeath(Beast beast) {
        this.getFightLog().append(" ¡Muere " + beast.printBeastTitle() + "!\n\n");
    }

    public void displayHeroWin() {
        this.getFightLog().append("******************************************************************************\n");
        this.getFightLog().append("                                        ¡Victoria de los Héroes!\n");
        this.getFightLog().append("                                   La Tierra Media ha sido salvada\n");
        this.getFightLog().append("******************************************************************************\n");
    }

    public void displayBeastWin() {
        this.getFightLog().append("******************************************************************************\n");
        this.getFightLog().append("                                       ¡Victoria de las Bestias!\n");
        this.getFightLog().append("                                  La Tierra Media ha sido condenada\n");
        this.getFightLog().append("******************************************************************************\n");
    }

    public void updateAndScroll() {
        this.getFightLogPanel().getVerticalScrollBar().setValue(this.getFightLogPanel().getVerticalScrollBar().getMaximum());
    }

    public void showIndexOutOfBoundsExceptionAlert() {
        JOptionPane.showMessageDialog(null, "No se puede bajar/subir el personaje", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public void showEmptyNameAlert() {
        JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio", "Error", JOptionPane.WARNING_MESSAGE);
    }
}
