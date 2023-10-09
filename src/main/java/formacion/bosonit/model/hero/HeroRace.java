package formacion.bosonit.model.hero;

public enum HeroRace {
    Human("Humano"),
    Elf("Elfo"),
    Hobbit("Hobbit");

    private final String text;

    HeroRace(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
