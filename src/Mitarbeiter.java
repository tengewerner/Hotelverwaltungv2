import java.io.Serializable;

/**
 * Repräsentiert einen Mitarbeiter im Hotel.
 * <p>
 * Enthält Name, Rolle und Schicht des Mitarbeiters.
 * </p>
 */
public class Mitarbeiter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;   // Name des Mitarbeiters
    private Rolle rolle;   // Rolle im Hotel (Rezeptionist, Küchenpersonal, Reinigungspersonal)
    private Schicht schicht; // Zugewiesene Schicht (Frühschicht, Mittagschicht, Spätschicht)

    /**
     * Konstruktor zum Erstellen eines Mitarbeiters.
     *
     * @param name   Name des Mitarbeiters
     * @param rolle  Rolle des Mitarbeiters
     * @param schicht Zugewiesene Schicht des Mitarbeiters
     */
    public Mitarbeiter(String name, Rolle rolle, Schicht schicht) {
        this.name = name;
        this.rolle = rolle;
        this.schicht = schicht;
    }

    /**
     * Liefert den Namen des Mitarbeiters.
     *
     * @return Name des Mitarbeiters
     */
    public String getName() {
        return name;
    }

    /**
     * Liefert die Rolle des Mitarbeiters.
     *
     * @return Rolle des Mitarbeiters
     */
    public Rolle getRolle() {
        return rolle;
    }

    /**
     * Liefert die Schicht des Mitarbeiters.
     *
     * @return Zugewiesene Schicht des Mitarbeiters
     */
    public Schicht getSchicht() {
        return schicht;
    }

    /**
     * Überschreibt die Standard-String-Repräsentation.
     * Liefert eine lesbare Ausgabe von Name, Rolle und Schicht.
     *
     * @return Formatierter String mit Mitarbeiterinformationen
     */
    @Override
    public String toString() {
        return "Mitarbeiter: " + name + ", Rolle: " + rolle + ", Schicht: " + schicht;
    }
}
