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
        Map<Schicht, List<Mitarbeiter>> plan = new HashMap<>();
        for (Schicht s : Schicht.values()) {
            plan.put(s, new ArrayList<>());
        }
        for (Mitarbeiter m : mitarbeiterListe) {
            plan.get(m.getSchicht()).add(m);
        }
        for (Schicht s : Schicht.values()) {
            System.out.println("Schicht: " + s);
            List<Mitarbeiter> ms = plan.get(s);
            if (ms.isEmpty()) {
                System.out.println("  Keine Mitarbeiter");
            } else {
                for (Mitarbeiter m : ms) {
                    System.out.println("  " + m.getName() + " (" + m.getRolle() + ")");
                }
            }
        }
    }
}

