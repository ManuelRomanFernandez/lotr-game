package formacion.bosonit.model.hero;

public enum HeroRace {
    Humano("Humano"),
    Elfo("Elfo"),
    Hobbit("Hobbit");

    private final String text;

    HeroRace(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
