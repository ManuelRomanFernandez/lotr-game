package formacion.bosonit.model.beast;

import formacion.bosonit.model.Unit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Beast extends Unit {
    private BeastRace race;
    @Override
    public int[] attack(Unit enemy) {
        int dice = (int)(Math.ceil(Math.random()*90+1));
        int damage = dice > enemy.getArmor()
                ? dice - enemy.getArmor()
                : 0;

        return new int[]{dice, damage};
    }

    public String printBeastTitle() {
        return this.getName() + " (" + this.race.getText() + ")";
    }

    public String printBeastInfo() {
        return this.getName()
                + "("
                + this.getRace().getText()
                + ")"
                + " (Vida:"
                + this.getHealth()
                + " Armadura:"
                + this.getArmor()
                + ")";
    }
}
