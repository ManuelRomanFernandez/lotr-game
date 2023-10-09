package formacion.bosonit.model;

import lombok.Data;

@Data
public abstract class Unit {
    private String name;
    private int health;
    private int armor;
    private boolean alive;
    private boolean fighting;

    public abstract int[] attack(Unit enemy);
    public void getAttacked(int damage){
        if ((this.health - damage) <= 0){
            setHealth(0);
            setAlive(false);
        } else {
            setHealth(this.health - damage);
        }
    }

    public String printUnitStats() {
        return this.name
                + " (Vida:"
                + this.health
                + " Armadura:"
                + this.armor
                + ")";
    }

}
