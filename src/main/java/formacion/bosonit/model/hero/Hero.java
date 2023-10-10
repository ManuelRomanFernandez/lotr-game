package formacion.bosonit.model.hero;

import formacion.bosonit.model.Unit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Hero extends Unit {
    private HeroRace race;

    @Override
    public int[] attack(Unit enemy) {
        int firstDice = (int) (Math.ceil(Math.random()*100+1));
        int secondDice = (int) (Math.ceil(Math.random()*100+1));

        int dice = Math.max(firstDice, secondDice);

        int damage = dice > enemy.getArmor()
                ? dice - enemy.getArmor()
                : 0;

        return new int[]{dice, damage};
    }

    public String printHeroTitle() {
        return this.getName() + " (" + this.race.getText() + ")";
    }

    public String printHeroInfo() {
        return this.getName()
                + " ("
                + this.getRace().getText()
                + ")"
                + " (Vida:"
                + this.getHealth()
                + " | Armadura:"
                + this.getArmor()
                + ")";
    }
}
