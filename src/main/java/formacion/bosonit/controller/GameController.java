package formacion.bosonit.controller;

import formacion.bosonit.model.beast.Beast;
import formacion.bosonit.model.beast.BeastRace;
import formacion.bosonit.model.beast.Goblin;
import formacion.bosonit.model.beast.Orc;
import formacion.bosonit.model.hero.*;
import formacion.bosonit.utils.Utils;
import formacion.bosonit.view.GameView;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.util.*;

@Getter
@Setter
public class GameController implements KeyListener, MouseListener {
    private List<Hero> heroTeam = new ArrayList<>();
    private List<Beast> beastTeam = new ArrayList<>();
    private String heroName = "";
    private HeroRace heroRace = HeroRace.Humano;
    private int heroHealth = 120;
    private int heroArmor = 35;
    private String beastName = "";
    private BeastRace beastRace = BeastRace.Orco;
    private int beastHealth = 120;
    private int beastArmor = 35;

    private final Utils utils;
    private final GameView gameView;

    public GameController(Utils utils, GameView gameView) {
        this.utils = utils;

        this.gameView = gameView;

        this.gameView.getHeroRaceSelectionDrop().setModel(new DefaultComboBoxModel<>(HeroRace.values()));
        this.gameView.getBeastRaceSelectionDrop().setModel(new DefaultComboBoxModel<>(BeastRace.values()));

        this.gameView.getHeroNameSelectionInput().addKeyListener(this);
        this.gameView.getHeroRaceSelectionDrop().addItemListener(heroRaceListener);
        this.gameView.getHeroHealthSelectionSpinner().addChangeListener(heroHealthListener);
        this.gameView.getHeroArmorSelectionSpinner().addChangeListener(heroArmorListener);
        this.gameView.getHeroAddButton().addMouseListener(this);

        this.gameView.getBeastNameSelectionInput().addKeyListener(this);
        this.gameView.getBeastRaceSelectionDrop().addItemListener(beastRaceListener);
        this.gameView.getBeastHealthSelectionSpinner().addChangeListener(beastHealthListener);
        this.gameView.getBeastArmorSelectionSpinner().addChangeListener(beastArmorListener);
        this.gameView.getBeastAddButton().addMouseListener(this);
        this.gameView.getHeroUpButton().addMouseListener(this);
        this.gameView.getHeroDownButton().addMouseListener(this);
        this.gameView.getHeroRemoveButton().addMouseListener(this);

        this.gameView.getBeastUpButton().addMouseListener(this);
        this.gameView.getBeastDownButton().addMouseListener(this);
        this.gameView.getBeastRemoveBButton().addMouseListener(this);

        this.gameView.getStartFightButton().addMouseListener(this);
    }

    ItemListener heroRaceListener = e -> setHeroRace((HeroRace) e.getItem());

    ChangeListener heroHealthListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            setHeroHealth(Integer.parseInt(gameView.getHeroHealthSelectionSpinner().getValue().toString()));
        }
    };

    ChangeListener heroArmorListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            setHeroArmor(Integer.parseInt(gameView.getHeroArmorSelectionSpinner().getValue().toString()));
        }
    };

    ItemListener beastRaceListener = e -> setBeastRace((BeastRace) e.getItem());

    ChangeListener beastHealthListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            setBeastHealth(Integer.parseInt(gameView.getBeastHealthSelectionSpinner().getValue().toString()));
        }
    };

    ChangeListener beastArmorListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            setBeastArmor(Integer.parseInt(gameView.getBeastArmorSelectionSpinner().getValue().toString()));
        }
    };

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(gameView.getHeroNameSelectionInput())) {
            this.heroName = gameView.getHeroNameSelectionInput().getText();
        }

        if (e.getSource().equals(gameView.getBeastNameSelectionInput())) {
            this.beastName = gameView.getBeastNameSelectionInput().getText();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(gameView.getHeroAddButton())) {
            if (!Objects.equals(this.heroName, "")) {
                Hero unit = null;

                switch (this.heroRace) {
                    case Humano -> {
                        unit = new Human();
                        unit.setRace(HeroRace.Humano);
                    }
                    case Elfo -> {
                        unit = new Elf();
                        unit.setRace(HeroRace.Elfo);
                    }
                    case Hobbit -> {
                        unit = new Hobbit();
                        unit.setRace(HeroRace.Hobbit);
                    }
                }

                unit.setName(this.heroName);
                unit.setHealth(this.heroHealth);
                unit.setArmor(heroArmor);
                unit.setAlive(true);

                this.heroTeam.add(unit);

                this.updateHeroTeam();

                gameView.getHeroNameSelectionInput().setText("");
                this.heroName = "";
                gameView.getHeroRaceSelectionDrop().setSelectedItem(HeroRace.Humano);
                this.heroRace = HeroRace.Humano;
                gameView.getHeroHealthSelectionSpinner().setValue(120);
                this.heroHealth = 120;
                gameView.getHeroArmorSelectionSpinner().setValue(35);
                this.heroArmor = 35;
            } else {
                gameView.showEmptyNameAlert();
            }
        }

        if (e.getSource().equals(gameView.getBeastAddButton())) {
            if (!Objects.equals(this.beastName, "")){
                Beast unit = null;

                switch (this.beastRace) {
                    case Orco -> {
                        unit = new Orc();
                        unit.setRace(BeastRace.Orco);
                    }
                    case Trasgo -> {
                        unit = new Goblin();
                        unit.setRace(BeastRace.Trasgo);
                    }
                }

                unit.setName(this.beastName);
                unit.setHealth(this.beastHealth);
                unit.setArmor(this.beastArmor);
                unit.setAlive(true);

                this.beastTeam.add(unit);

                this.updateBeastTeam();

                gameView.getBeastNameSelectionInput().setText("");
                this.beastName = "";
                gameView.getBeastRaceSelectionDrop().setSelectedItem(BeastRace.Orco);
                this.beastRace = BeastRace.Orco;
                gameView.getBeastHealthSelectionSpinner().setValue(120);
                this.beastHealth = 120;
                gameView.getBeastArmorSelectionSpinner().setValue(35);
                this.beastArmor = 35;
            } else {
                gameView.showEmptyNameAlert();
            }
        }

        if (!gameView.getHeroList().isSelectionEmpty()) {
            if (e.getSource().equals(gameView.getHeroUpButton())) {
                try {
                    int index = gameView.getHeroList().getSelectedIndex();
                    Collections.swap(heroTeam, index, (index - 1));
                    this.updateHeroTeam();
                } catch (IndexOutOfBoundsException exception) {
                    gameView.showIndexOutOfBoundsExceptionAlert();
                }
            }

            if (e.getSource().equals(gameView.getHeroDownButton())) {
                try {
                    int index = gameView.getHeroList().getSelectedIndex();
                    Collections.swap(heroTeam, index, (index + 1));
                    this.updateHeroTeam();
                } catch (IndexOutOfBoundsException exception) {
                    gameView.showIndexOutOfBoundsExceptionAlert();
                }
            }

            if (e.getSource().equals(gameView.getHeroRemoveButton())) {
                int index = gameView.getHeroList().getSelectedIndex();
                heroTeam.remove(index);
                this.updateHeroTeam();
            }
        }

        if (!gameView.getBeastList().isSelectionEmpty()) {
            if (e.getSource().equals(gameView.getBeastUpButton())) {
                try {
                    int index = gameView.getBeastList().getSelectedIndex();
                    Collections.swap(beastTeam, index, (index - 1));
                    this.updateBeastTeam();
                } catch (IndexOutOfBoundsException exception) {
                    gameView.showIndexOutOfBoundsExceptionAlert();
                }
            }

            if (e.getSource().equals(gameView.getBeastDownButton())) {
                try {
                    int index = gameView.getBeastList().getSelectedIndex();
                    Collections.swap(beastTeam, index, (index + 1));
                    this.updateBeastTeam();
                } catch (IndexOutOfBoundsException exception) {
                    gameView.showIndexOutOfBoundsExceptionAlert();
                }
            }

            if (e.getSource().equals(gameView.getBeastRemoveBButton())) {
                int index = gameView.getBeastList().getSelectedIndex();
                beastTeam.remove(index);
                this.updateBeastTeam();
            }
        }

        if (e.getSource().equals(gameView.getStartFightButton())
                & this.heroTeam.size() > 0
                & this.beastTeam.size() > 0) {
            gameView.getFightLog().setText("");
            SwingWorker<Void, Void> gameWorker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    gameBattle();
                    return null;
                }
            };

            gameWorker.execute();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void gameBattle() {
        Random random = new Random();

        boolean battleBool = true;
        int turn = 1;

        gameView.displayGameStart();

        while (battleBool) {
            int fightsNum = Math.min(heroTeam.size(), beastTeam.size());

            gameView.displayTurn(turn);
            gameView.updateAndScroll();
            this.updateBattle();

            utils.gameDelay(1000);

            for (int i = 0; i < fightsNum; i++) {
                if ((heroTeam.size() - 1) < i | (beastTeam.size() - 1) < i) {
                    break;
                }

                Hero hero = heroTeam.get(i);
                Beast beast = beastTeam.get(i);

                gameView.displayFightWarning(hero, beast);
                gameView.updateAndScroll();
                utils.gameDelay(1000);

                if ((random.nextInt(99) + 1) % 2 == 0) {
                    int[] heroAttack = hero.attack(beast);
                    gameView.displayFightFirstAttack(hero, beast, heroAttack);
                    gameView.updateAndScroll();
                    utils.gameDelay(500);

                    beast.getAttacked(heroAttack[1]);

                    if (beast.getHealth() <= 0) {
                        gameView.displayBeastDeath(beast);
                        gameView.updateAndScroll();
                        utils.gameDelay(500);

                        beastTeam.remove(beast);

                        continue;
                    }

                    this.updateBattle();

                    int[] beastAttack = beast.attack(hero);
                    gameView.displayFightSecondAttack(beast, hero, beastAttack);
                    gameView.updateAndScroll();
                    utils.gameDelay(500);

                    hero.getAttacked(beastAttack[1]);

                    if (hero.getHealth() <= 0) {
                        gameView.displayHeroDeath(hero);
                        gameView.updateAndScroll();
                        utils.gameDelay(500);

                        heroTeam.remove(hero);
                    }

                    this.updateBattle();
                } else {
                    int[] beastAttack = beast.attack(hero);
                    gameView.displayFightFirstAttack(beast, hero, beastAttack);
                    gameView.updateAndScroll();
                    utils.gameDelay(500);

                    hero.getAttacked(beastAttack[1]);

                    if (hero.getHealth() <= 0) {
                        gameView.displayHeroDeath(hero);
                        gameView.updateAndScroll();
                        utils.gameDelay(500);

                        heroTeam.remove(hero);

                        continue;
                    }

                    this.updateBattle();

                    int[] heroAttack = hero.attack(beast);
                    gameView.displayFightSecondAttack(hero, beast, heroAttack);
                    gameView.updateAndScroll();
                    utils.gameDelay(500);

                    beast.getAttacked(heroAttack[1]);

                    if (beast.getHealth() <= 0) {
                        gameView.displayBeastDeath(beast);
                        gameView.updateAndScroll();
                        utils.gameDelay(500);

                        beastTeam.remove(beast);
                    }

                    this.updateBattle();
                }

            }

            if (heroTeam.size() == 0) {
                this.updateBattle();
                gameView.displayBeastWin();
                gameView.updateAndScroll();
                battleBool = false;
            } else if (beastTeam.size() == 0) {
                this.updateBattle();
                gameView.displayHeroWin();
                gameView.updateAndScroll();
                battleBool = false;
            }

            turn++;
        }
    }

    private void updateHeroTeam() {
        DefaultListModel<String> dlmHero = new DefaultListModel<>();

        for (Hero hero : this.heroTeam) {
            dlmHero.addElement(hero.printHeroInfo());
        }

        gameView.getHeroList().setModel(dlmHero);
    }

    private void updateBeastTeam() {
        DefaultListModel<String> dlmHero = new DefaultListModel<>();

        for (Beast beast : this.beastTeam) {
            dlmHero.addElement(beast.printBeastInfo());
        }

        gameView.getBeastList().setModel(dlmHero);
    }

    private void updateBattle() {
        this.updateHeroTeam();
        this.updateBeastTeam();
    }
}
