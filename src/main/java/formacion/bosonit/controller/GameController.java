package formacion.bosonit.controller;

import formacion.bosonit.model.beast.Beast;
import formacion.bosonit.model.beast.BeastRace;
import formacion.bosonit.model.beast.Goblin;
import formacion.bosonit.model.beast.Orc;
import formacion.bosonit.model.hero.*;
import formacion.bosonit.utils.Utils;
import formacion.bosonit.view.GameViewImpl;

import java.util.*;

public class GameController {
    private final GameViewImpl gameView;
    private final Utils utils;

    public GameController(GameViewImpl gameView, Utils utils) {
        this.gameView = gameView;
        this.utils = utils;
    }

    public void init() {
        gameView.displayWelcome();
        utils.gameDelay(1000);

        gameView.displayHeroBanner();
        utils.gameDelay(1000);

        List<Hero> heroTeam = heroTeamSelection();
        utils.gameDelay(1000);

        gameView.displayBeastBanner();
        utils.gameDelay(1000);

        List<Beast> beastTeam = beastTeamSelection();
        utils.gameDelay(1000);

        gameView.displayTeams(heroTeam, beastTeam);
        utils.gameDelay(1500);

        gameView.displayGameStart();
        utils.gameDelay(1500);

        gameBattle(heroTeam, beastTeam);
    }

    private List<Hero> heroTeamSelection () {
        List<Hero> heroTeam = new ArrayList<>();

        boolean activeLoop = true;
        while (activeLoop) {
            Scanner scanner = new Scanner(System.in);

            gameView.displayHeroRaceSelection();

            Hero unit = heroUnitSelection(scanner);

            heroTeam.add(unit);

            gameView.displayCurrentHeroTeam(heroTeam);
            String loopOption = scanner.nextLine();

            if (!Objects.equals(loopOption, "1")){
                activeLoop = false;
            }
        }

        return heroTeam;
    }

    private Hero heroUnitSelection (Scanner scanner) {
        Hero unit = null;
        int unitNumber = 0;
        boolean unitNumberBool = true;

        while (unitNumberBool) {

            try {
                unitNumber = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                gameView.displayErrorNumber();
                scanner.nextLine();
                gameView.displaySpace();
                gameView.displayHeroRaceSelection();
                continue;
            }

            switch (unitNumber) {
                case 1 -> {
                    unit = new Human();
                    unit.setRace(HeroRace.Human);
                    unitNumberBool = false;
                }
                case 2 -> {
                    unit = new Elf();
                    unit.setRace(HeroRace.Elf);
                    unitNumberBool = false;
                }
                case 3 -> {
                    unit = new Hobbit();
                    unit.setRace(HeroRace.Hobbit);
                    unitNumberBool = false;
                }
                default -> {
                    gameView.displayErrorNumber();
                    gameView.displaySpace();
                    gameView.displayHeroRaceSelection();
                }
            }
        }

        gameView.displayUnitNameSelection(HeroRace.values()[unitNumber - 1].getText());
        String unitName = scanner.nextLine();

        gameView.displayUnitHealthSelection();
        int unitHealth = scanner.nextInt();
        scanner.nextLine();

        gameView.displayUnitArmorSelection();
        int unitArmor = scanner.nextInt();
        scanner.nextLine();

        unit.setName(unitName);
        unit.setHealth(unitHealth);
        unit.setArmor(unitArmor);
        unit.setAlive(true);

        return unit;
    }

    private List<Beast> beastTeamSelection() {
        List<Beast> beastTeam = new ArrayList<>();

        boolean activeLoop = true;
        while (activeLoop) {
            Scanner scanner = new Scanner(System.in);

            gameView.displayBeastRaceSelection();

            Beast unit = beastUnitSelection(scanner);

            beastTeam.add(unit);

            gameView.displayCurrentBeastTeam(beastTeam);
            String loopOption = scanner.nextLine();

            if (!Objects.equals(loopOption, "1")){
                activeLoop = false;
            }
        }

        return beastTeam;
    }

    private Beast beastUnitSelection (Scanner scanner) {
        Beast unit = null;
        int unitNumber = 0;
        boolean unitNumberBool = true;

        while (unitNumberBool) {
            try {
                unitNumber = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                gameView.displayErrorNumber();
                scanner.nextLine();
                gameView.displaySpace();
                gameView.displayBeastRaceSelection();
                continue;
            }

            switch (unitNumber) {
                case 1 -> {
                    unit = new Orc();
                    unit.setRace(BeastRace.Orc);
                    unitNumberBool = false;
                }
                case 2 -> {
                    unit = new Goblin();
                    unit.setRace(BeastRace.Goblin);
                    unitNumberBool = false;
                }
                default -> {
                    gameView.displayErrorNumber();
                    gameView.displaySpace();
                    gameView.displayBeastRaceSelection();
                }
            }
        }

        gameView.displayUnitNameSelection(BeastRace.values()[unitNumber - 1].getText());
        String unitName = scanner.nextLine();

        gameView.displayUnitHealthSelection();
        int unitHealth = scanner.nextInt();
        scanner.nextLine();

        gameView.displayUnitArmorSelection();
        int unitArmor = scanner.nextInt();
        scanner.nextLine();

        unit.setName(unitName);
        unit.setHealth(unitHealth);
        unit.setArmor(unitArmor);
        unit.setAlive(true);

        return unit;
    }

    private void gameBattle(List<Hero> heroTeam, List<Beast> beastTeam) {
        Random random = new Random();
        Collections.shuffle(heroTeam);
        Collections.shuffle(beastTeam);

        boolean battleBool = true;
        int turn = 1;

        int fightsNum = Math.min(heroTeam.size(), beastTeam.size());

        activateInitialFighters(heroTeam, beastTeam, fightsNum);

        while (battleBool) {
            gameView.displayTurn(turn);
            gameView.displaySpace();
            utils.gameDelay(1000);

            fightsNum = Math.min(heroTeam.size(), beastTeam.size());

            for (int i = 0; i < fightsNum; i++) {

                if (!heroTeam.get(i).isAlive()) {
                    swapHeroUnits(heroTeam, i, countAliveBeasts(beastTeam));
                }
                Hero hero = heroTeam.get(i);

                if (!beastTeam.get(i).isAlive()) {
                    swapBeastUnits(beastTeam, i, countAliveHeroes(heroTeam));
                }
                Beast beast = beastTeam.get(i);

                if (hero.isAlive() & beast.isAlive()) {
                    gameView.displayFightWarning(hero, beast);
                    utils.gameDelay(1000);

                    if ((random.nextInt(99)+1) % 2 == 0) {
                        int[] heroAttack = hero.attack(beast);
                        gameView.displayFightFirstAttack(hero, beast, heroAttack);
                        utils.gameDelay(500);

                        beast.getAttacked(heroAttack[1]);

                        if (!beast.isAlive()) {
                            gameView.displayBeastDeath(beast);
                            gameView.displaySpace();
                            utils.gameDelay(500);

                            hero.setFighting(false);
                            swapBeastUnits(beastTeam, i, countAliveHeroes(heroTeam));

                            continue;
                        }

                        int[] beastAttack = beast.attack(hero);
                        gameView.displayFightSecondAttack(beast, hero, beastAttack);
                        utils.gameDelay(500);

                        hero.getAttacked(beastAttack[1]);

                        if (!hero.isAlive()) {
                            gameView.displayHeroDeath(hero);
                            gameView.displaySpace();
                            utils.gameDelay(500);

                            beast.setFighting(false);
                            swapHeroUnits(heroTeam, i, countAliveBeasts(beastTeam));

                            continue;
                        }
                    } else {
                        int[] beastAttack = beast.attack(hero);
                        gameView.displayFightFirstAttack(beast, hero, beastAttack);
                        utils.gameDelay(500);

                        hero.getAttacked(beastAttack[1]);

                        if (!hero.isAlive()) {
                            gameView.displayHeroDeath(hero);
                            gameView.displaySpace();
                            utils.gameDelay(500);

                            beast.setFighting(false);
                            swapHeroUnits(heroTeam, i, countAliveBeasts(beastTeam));

                            continue;
                        }

                        int[] heroAttack = hero.attack(beast);
                        gameView.displayFightSecondAttack(hero, beast, heroAttack);
                        utils.gameDelay(500);

                        beast.getAttacked(heroAttack[1]);

                        if (!beast.isAlive()) {
                            gameView.displayBeastDeath(beast);
                            gameView.displaySpace();
                            utils.gameDelay(500);

                            hero.setFighting(false);
                            swapBeastUnits(beastTeam, i, countAliveHeroes(heroTeam));

                            continue;
                        }
                    }

                    gameView.displaySpace();

                }

            }

            if (countAliveHeroes(heroTeam) == 0){
                gameView.displaySpace();
                gameView.displayBeastWin();
                battleBool = false;
            } else if (countAliveBeasts(beastTeam) == 0) {
                gameView.displaySpace();
                gameView.displayHeroWin();
                battleBool = false;
            }

            turn++;
        }
    }

    private void activateInitialFighters(List<Hero> heroTeam, List<Beast> beastTeam, int size) {
        for (int i = 0; i < size; i++){
            heroTeam.get(i).setFighting(true);
            beastTeam.get(i).setFighting(true);
        }
    }

    private void swapHeroUnits(List<Hero> heroTeam, int index, int minSize) {
        if (countAliveHeroes(heroTeam) >= minSize){
            heroTeam.stream()
                    .skip(minSize)
                    .filter(hero -> hero.isAlive() && !hero.isFighting())
                    .findFirst()
                    .ifPresent(hero -> {
                        Collections.swap(heroTeam, index, heroTeam.indexOf(hero));
                        heroTeam.get(index).setFighting(true);
                        hero.setFighting(false);
                    });
        }
    }

    private void swapBeastUnits(List<Beast> beastTeam, int index, int minSize) {
        if (countAliveBeasts(beastTeam) >= minSize) {
            beastTeam.stream()
                    .skip(minSize)
                    .filter(beast -> beast.isAlive() && !beast.isFighting())
                    .findFirst()
                    .ifPresent(beast -> {
                        Collections.swap(beastTeam, index, beastTeam.indexOf(beast));
                        beastTeam.get(index).setFighting(true);
                        beast.setFighting(false);
                    });
        }
    }

    private int countAliveHeroes(List<Hero> heroTeam) {
        return (int) heroTeam.stream().filter(Hero::isAlive).count();
    }

    private int countAliveBeasts(List<Beast> beastTeam) {
        return (int) beastTeam.stream().filter(Beast::isAlive).count();
    }

}
