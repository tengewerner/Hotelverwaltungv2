import java.util.*;

public class Zimmer {
    private int zimmernummer;
    private zimmerTyp typ;
    private boolean belegt;
    private boolean reserviert;
    private String reservierterGast;
    private List<String> ausstattung;
    private Set<Verpflegung> verpflegung;
    private boolean ausgecheckt = false;

    public Zimmer(int zimmernummer, zimmerTyp typ, List<String> ausstattung) {
        this.zimmernummer = zimmernummer;
        this.typ = typ;
        this.ausstattung = ausstattung;
        this.belegt = false;
        this.reserviert = false;
        this.reservierterGast = null;
        this.verpflegung = new HashSet<>();
    }

    public int getZimmernummer() {
        return zimmernummer;
    }
    public zimmerTyp getTyp() {
        return typ;
    }
    public boolean isBelegt() {
        return belegt;
    }
    public void setBelegt(boolean belegt) {
        this.belegt = belegt;
        if (belegt) {
            this.ausgecheckt = false;
        }
    }
    public boolean isReserviert() {
        return reserviert;
    }
    public String getReservierterGast() {
        return reservierterGast;
    }
    public void reservieren(String gastName) {
        this.reserviert = true;
        this.reservierterGast = gastName;
    }
    public void stornieren() {
        this.reserviert = false;
        this.reservierterGast = null;
        this.ausgecheckt = true;
    }
    public List<String> getAusstattung() {
        return ausstattung;
    }
    public Verpflegung getVerpflegung() {
        if (verpflegung.isEmpty()) return null;
        return verpflegung.iterator().next(); // Gibt die erste Verpflegung zurück, falls vorhanden
    }

    public void bucheVerpflegung(Verpflegung verpflegung) {
        this.verpflegung.clear();
        this.verpflegung.add(verpflegung);
    }

    public void storniereVerpflegung() {
        this.verpflegung.clear();
    }

    public int getMaxPersonen() {
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
    public String toString() {
        String status = belegt ? "belegt" : (reserviert ? "reserviert für " + reservierterGast : "frei");
        String verpflegungStr = verpflegung.isEmpty() ? "keine Verpflegung gebucht" : "Verpflegung: " + getVerpflegung();
        return "Zimmer " + zimmernummer + " (" + typ + ") - max. Personen: " + getMaxPersonen() +
                ", Preis/Nacht: " + getPreisProNacht() + "€, Status: " + status + ", " + verpflegungStr;
    }
}
