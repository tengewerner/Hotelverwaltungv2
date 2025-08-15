public class Mitarbeiter {
    private String name;
    private Rolle rolle;
    private Schicht schicht;

    public Mitarbeiter(String name, Rolle rolle, Schicht schicht) {
        this.name = name;
        this.rolle = rolle;
        this.schicht = schicht;
    }

    public String getName() {
        return name;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public Schicht getSchicht() {
        return schicht;
    }

    @Override
    public String toString() {
        return "Mitarbeiter: " + name + ", Rolle: " + rolle + ", Schicht: " + schicht;
    }
}

