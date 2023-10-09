package formacion.bosonit.model.beast;

public enum BeastRace {
    Orc("Orco"),
    Goblin("Trasgo");

    private final String text;

    BeastRace(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
