import java.util.*;

public class MitarbeiterVerwaltung {
    private List<Mitarbeiter> mitarbeiterListe;

    public MitarbeiterVerwaltung() {        // Konstruktor der MitarbeiterVerwaltung-Klasse initialisiert die Mitarbeiterliste
        mitarbeiterListe = new ArrayList<>();
    }

    public void addMitarbeiter(String name, Rolle rolle, Schicht schicht) {     // Methode zum Hinzufügen eines Mitarbeiters
        mitarbeiterListe.add(new Mitarbeiter(name, rolle, schicht));
        System.out.println("Mitarbeiter hinzugefügt: " + name + ", Rolle: " + rolle + ", Schicht: " + schicht);
    }

    public void listMitarbeiter() {     // Methode zum Auflisten aller Mitarbeiter
        if (mitarbeiterListe.isEmpty()) {
            System.out.println("Keine Mitarbeiter vorhanden.");
            return;
        }
        for (Mitarbeiter m : mitarbeiterListe) {
            System.out.println(m);
        }
    }

    public void zeigeEinsatzplan() {        // Methode zum Anzeigen des Einsatzplans
        if (mitarbeiterListe.isEmpty()) {
            System.out.println("Keine Mitarbeiter vorhanden.");
            return;
        }
        for (Schicht s : Schicht.values()) {        // geht alle Schichten durch
            System.out.println("Schicht: " + s);
            boolean found = false;
            for (Mitarbeiter m : mitarbeiterListe) {
                if (m.getSchicht() == s) {
                    System.out.println("  " + m.getName() + " (" + m.getRolle() + ")");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("  Keine Mitarbeiter");
            }
        }
    }
}
