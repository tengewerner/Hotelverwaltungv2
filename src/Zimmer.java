import java.util.*;

public class Zimmer {       // Klasse für die Zimmerverwaltung in einem Hotel
    private int zimmernummer;
    private zimmerTyp typ;
    private boolean belegt;
    private boolean reserviert;
    private String reservierterGast;
    private List<String> ausstattung;
    private Set<Verpflegung> verpflegung;
    private boolean ausgecheckt = false;

    public Zimmer(int zimmernummer, zimmerTyp typ, List<String> ausstattung) {      // Konstruktor der Zimmer-Klasse
        this.zimmernummer = zimmernummer;
        this.typ = typ;
        this.ausstattung = ausstattung;
        this.belegt = false;
        this.reserviert = false;
        this.reservierterGast = null;
        this.verpflegung = new HashSet<>();
    }

    public int getZimmernummer() {      // Getter für die Zimmernummer
        return zimmernummer;
    }
    public zimmerTyp getTyp() {     // Getter für den Zimmertyp
        return typ;
    }
    public boolean isBelegt() {     // Prüft, ob das Zimmer belegt ist
        return belegt;
    }
    public void setBelegt(boolean belegt) {     // Setzt den Belegungsstatus des Zimmers
        this.belegt = belegt;
        if (belegt) {
            this.ausgecheckt = false;
        }
    }
    public boolean isReserviert() {     // Prüft, ob das Zimmer reserviert ist
        return reserviert;
    }
    public String getReservierterGast() {       // Gibt den Namen des reservierten Gastes zurück
        return reservierterGast;
    }
    public void reservieren(String gastName) {      // Reserviert das Zimmer für einen Gast
        this.reserviert = true;
        this.reservierterGast = gastName;
    }
    public void stornieren() {      // Storniert die Reservierung des Zimmers
        this.reserviert = false;
        this.reservierterGast = null;
        this.ausgecheckt = true;
    }
    public List<String> getAusstattung() {      // Gibt die Ausstattung des Zimmers zurück
        return ausstattung;
    }
    public Verpflegung getVerpflegung() {
        if (verpflegung.isEmpty()) return null;
        return verpflegung.iterator().next(); // Gibt die erste Verpflegung zurück, falls vorhanden
    }

    public void bucheVerpflegung(Verpflegung verpflegung) {     // Bucht eine Verpflegung für das Zimmer
        this.verpflegung.clear();
        this.verpflegung.add(verpflegung);
    }

    public void storniereVerpflegung() {        // Storniert die Verpflegung des Zimmers
        this.verpflegung.clear();
    }

    public int getMaxPersonen() {       // Gibt die maximale Personenanzahl für das Zimmer zurück
        return typ.getMaxPersonen();
    }
    public double getPreisProNacht() {
        return typ.getPreisProNacht();
    }
    public boolean isAusgecheckt() {
        return ausgecheckt;
    }
    public void setAusgecheckt(boolean ausgecheckt) {
        this.ausgecheckt = ausgecheckt;
    }
    public double getPreis() {
        return getPreisProNacht();
    }

    @Override
    public String toString() {    // Überschreibt die toString-Methode für eine bessere Ausgabe
        String status = belegt ? "belegt" : (reserviert ? "reserviert für " + reservierterGast : "frei");
        String verpflegungStr = verpflegung.isEmpty() ? "keine Verpflegung gebucht" : "Verpflegung: " + getVerpflegung();
        return "Zimmer " + zimmernummer + " (" + typ + ") - max. Personen: " + getMaxPersonen() +
                ", Preis/Nacht: " + getPreisProNacht() + "€, Status: " + status + ", " + verpflegungStr;
    }
}
