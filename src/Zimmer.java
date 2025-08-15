import java.util.*;

public class Zimmer {
    private int zimmernummer;
    private zimmerTyp typ;
    private boolean belegt;
    private boolean reserviert;
    private String reservierterGast;
    private List<String> ausstattung;
    private Set<Verpflegung> verpflegung;

    public Zimmer(int zimmernummer, zimmerTyp typ, List<String> ausstattung) {
        this.zimmernummer = zimmernummer;
        this.typ = typ;
        this.ausstattung = ausstattung;
        this.belegt = false;
        this.reserviert = false;
        this.reservierterGast = null;
        this.verpflegung = new HashSet<>();
    }

    public int getZimmernummer() { return zimmernummer; }
    public zimmerTyp getTyp() { return typ; }
    public boolean isBelegt() { return belegt; }
    public void setBelegt(boolean belegt) { this.belegt = belegt; }
    public boolean isReserviert() { return reserviert; }
    public String getReservierterGast() { return reservierterGast; }
    public void reservieren(String gastName) {
        this.reserviert = true;
        this.reservierterGast = gastName;
    }
    public void stornieren() {
        this.reserviert = false;
        this.reservierterGast = null;
    }
    public List<String> getAusstattung() { return ausstattung; }
    public Set<Verpflegung> getVerpflegung() { return verpflegung; }

    public void bucheVerpflegung(Verpflegung... verpflegungen) {
        verpflegung.addAll(Arrays.asList(verpflegungen));
    }

    public void storniereVerpflegung(Verpflegung... verpflegungen) {
        verpflegung.removeAll(Arrays.asList(verpflegungen));
    }

    public int getMaxPersonen() { return typ.getMaxPersonen(); }
    public double getPreisProNacht() { return typ.getPreisProNacht(); }

    @Override
    public String toString() {
        String status = belegt ? "belegt" : (reserviert ? "reserviert für " + reservierterGast : "frei");
        String verpflegungStr = verpflegung.isEmpty() ? "keine Verpflegung gebucht" : "Verpflegung: " + verpflegung;
        return "Zimmer " + zimmernummer + " (" + typ + ") - max. Personen: " + getMaxPersonen() +
                ", Preis/Nacht: " + getPreisProNacht() + "€, Status: " + status + ", " + verpflegungStr;
    }
}
