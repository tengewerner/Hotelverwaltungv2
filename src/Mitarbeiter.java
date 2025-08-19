public class Mitarbeiter {      // Klasse Mitarbeite
    private String name;
    private Rolle rolle;
    private Schicht schicht;

    public Mitarbeiter(String name, Rolle rolle, Schicht schicht) {     // Konstruktor der Mitarbeiter-Klasse
        this.name = name;
        this.rolle = rolle;
        this.schicht = schicht;
    }

    public String getName() {       // Getter für den Namen des Mitarbeiters
        return name;
    }

    public Rolle getRolle() {       // Getter für die Rolle des Mitarbeiters
        return rolle;
    }

    public Schicht getSchicht() {       // Getter für die Schicht des Mitarbeiters
        return schicht;
    }

    @Override
    public String toString() {      // Überschreiben der toString-Methode für bessere Ausgabe
        return "Mitarbeiter: " + name + ", Rolle: " + rolle + ", Schicht: " + schicht;
    }
}

