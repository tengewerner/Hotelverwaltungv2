import java.io.Serializable;
import java.util.*;

/**
 * Verwaltungsklasse für Mitarbeiter im Hotel.
 * <p>
 * Ermöglicht Hinzufügen, Auflisten und Anzeigen des Einsatzplans.
 * </p>
 */
public class MitarbeiterVerwaltung implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Mitarbeiter> mitarbeiterListe; // Liste aller Mitarbeiter

    /**
     * Konstruktor der MitarbeiterVerwaltung.
     * <p>
     * Initialisiert die Mitarbeiterliste.
     * </p>
     */
    public MitarbeiterVerwaltung() {
        mitarbeiterListe = new ArrayList<>();
    }

    /**
     * Fügt einen neuen Mitarbeiter hinzu und gibt eine Bestätigung aus.
     *
     * @param name   Name des Mitarbeiters
     * @param rolle  Rolle des Mitarbeiters
     * @param schicht Zugewiesene Schicht des Mitarbeiters
     */
    public void addMitarbeiter(String name, Rolle rolle, Schicht schicht) {
        mitarbeiterListe.add(new Mitarbeiter(name, rolle, schicht));
        System.out.println("Mitarbeiter hinzugefügt: " + name + ", Rolle: " + rolle + ", Schicht: " + schicht);
    }

    /**
     * Listet alle vorhandenen Mitarbeiter auf.
     * <p>
     * Gibt eine Nachricht aus, wenn keine Mitarbeiter vorhanden sind.
     * </p>
     */
    public void listMitarbeiter() {
        if (mitarbeiterListe.isEmpty()) {
            System.out.println("Keine Mitarbeiter vorhanden.");
            return;
        }

        for (Mitarbeiter m : mitarbeiterListe) {
            System.out.println(m);
        }
    }

    /**
     * Zeigt den Einsatzplan der Mitarbeiter nach Schichten geordnet an.
     * <p>
     * Für jede Schicht werden alle Mitarbeiter angezeigt, die dieser Schicht zugeordnet sind.
     * </p>
     * <p>
     * Gibt an, wenn keine Mitarbeiter für eine Schicht vorhanden sind.
     * </p>
     */
    public void zeigeEinsatzplan() {
        if (mitarbeiterListe.isEmpty()) {
            System.out.println("Keine Mitarbeiter vorhanden.");
            return;
        }

        // Schleife durch alle definierten Schichten
        for (Schicht s : Schicht.values()) {
            System.out.println("Schicht: " + s);
            boolean found = false;

            // Suche alle Mitarbeiter, die in dieser Schicht arbeiten
            for (Mitarbeiter m : mitarbeiterListe) {
                if (m.getSchicht() == s) { // Nicht-triviale Bedingung: Prüfen der Schichtzuordnung
                    System.out.println("  " + m.getName() + " (" + m.getRolle() + ")");
                    found = true;
                }
            }

            // Keine Mitarbeiter gefunden
            if (!found) {
                System.out.println("  Keine Mitarbeiter");
            }
        }
    }
}