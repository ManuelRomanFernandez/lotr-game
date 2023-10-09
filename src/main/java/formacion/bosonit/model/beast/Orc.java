package formacion.bosonit.model.beast;

import formacion.bosonit.model.Unit;

public class Orc extends Beast{
    @Override
    public int[] attack(Unit enemy) {
        int dice = (int) Math.ceil(super.attack(enemy)[0]*1.1);
        int damage = dice > enemy.getArmor()
                ? dice - enemy.getArmor()
                : 0;

        return new int[]{dice, damage};
    }
}
