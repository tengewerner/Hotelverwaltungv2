import java.util.*;

public class MitarbeiterVerwaltung {
    private List<Mitarbeiter> mitarbeiterListe;

    public MitarbeiterVerwaltung() {
        mitarbeiterListe = new ArrayList<>();
    }

    public void addMitarbeiter(String name, Rolle rolle, Schicht schicht) {
        mitarbeiterListe.add(new Mitarbeiter(name, rolle, schicht));
        System.out.println("Mitarbeiter hinzugefügt: " + name + ", Rolle: " + rolle + ", Schicht: " + schicht);
    }

    public void listMitarbeiter() {
        if (mitarbeiterListe.isEmpty()) {
            System.out.println("Keine Mitarbeiter vorhanden.");
            return;
        }
        for (Mitarbeiter m : mitarbeiterListe) {
            System.out.println(m);
        }
    }

    public void zeigeEinsatzplan() {
        if (mitarbeiterListe.isEmpty()) {
            System.out.println("Keine Mitarbeiter vorhanden.");
            return;
        }
        for (Schicht s : Schicht.values()) {
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
