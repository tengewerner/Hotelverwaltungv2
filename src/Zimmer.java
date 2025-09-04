import java.io.Serializable;
import java.util.*;

/**
 * Repräsentiert ein Zimmer in einem Hotel.
 * <p>
 * Enthält Informationen über Zimmernummer, Typ, Belegung, Reservierung, Ausstattung und Verpflegung.
 * </p>
 */
public class Zimmer implements Serializable {
    private static final long serialVersionUID = 1L;

    private int zimmernummer;             // Eindeutige Zimmernummer
    private zimmerTyp typ;                // Typ des Zimmers (Einzelzimmer, Doppelzimmer, Suite)
    private boolean belegt;               // Gibt an, ob das Zimmer belegt ist
    private boolean reserviert;           // Gibt an, ob das Zimmer reserviert ist
    private String reservierterGast;      // Name des reservierten Gastes
    private String belegterGast; // Name der eingecheckten Hauptperson
    private List<String> ausstattung;     // Liste der Ausstattung des Zimmers
    private Set<Verpflegung> verpflegung; // Menge der gebuchten Verpflegung (max. 1)
    private boolean ausgecheckt = false;  // Kennzeichnet, ob der Gast ausgecheckt wurde

    /**
     * Konstruktor zum Erstellen eines Zimmers.
     *
     * @param zimmernummer Eindeutige Zimmernummer
     * @param typ          Typ des Zimmers
     * @param ausstattung  Liste der Ausstattung des Zimmers
     */
    public Zimmer(int zimmernummer, zimmerTyp typ, List<String> ausstattung) {
        this.zimmernummer = zimmernummer;
        this.typ = typ;
        this.ausstattung = ausstattung;
        this.belegt = false;
        this.reserviert = false;
        this.reservierterGast = null;
        this.verpflegung = new HashSet<>();
    }

    /**
     * Liefert die Zimmernummer.
     *
     * @return Zimmernummer
     */
    public int getZimmernummer() {
        return zimmernummer;
    }

    /**
     * Liefert den Typ des Zimmers.
     *
     * @return Zimmertyp
     */
    public zimmerTyp getTyp() {
        return typ;
    }

    /**
     * Prüft, ob das Zimmer belegt ist.
     *
     * @return true, falls belegt, sonst false
     */
    public boolean isBelegt() {
        return belegt;
    }

    /**
     * Setzt den Belegungsstatus des Zimmers.
     * <p>
     * Beim Belegen wird das Zimmer automatisch als nicht ausgecheckt markiert.
     * </p>
     *
     * @param belegt Belegungsstatus
     */
    public void setBelegt(boolean belegt) {
        this.belegt = belegt;
        if (belegt) {
            this.ausgecheckt = false;
        }
    }

    /**
     * Prüft, ob das Zimmer reserviert ist.
     *
     * @return true, falls reserviert, sonst false
     */
    public boolean isReserviert() {
        return reserviert;
    }

    /**
     * Liefert den Namen des reservierten Gastes.
     *
     * @return Name des reservierten Gastes oder null, falls nicht reserviert
     */
    public String getReservierterGast() {
        return reservierterGast;
    }

    /**
     * Reserviert das Zimmer für einen Gast.
     *
     * @param gastName Name des Gastes
     */
    public void reservieren(String gastName) {
        this.reserviert = true;
        this.reservierterGast = gastName;
    }

    /**
     * Storniert die Reservierung des Zimmers.
     * <p>
     * Markiert das Zimmer als ausgecheckt.
     * </p>
     */
    public void stornieren() {
        this.reserviert = false;
        this.reservierterGast = null;
        this.ausgecheckt = true;
    }

    /**
     * Liefert die Ausstattung des Zimmers.
     *
     * @return Liste der Ausstattungsmerkmale
     */
    public List<String> getAusstattung() {
        return ausstattung;
    }

    /**
     * Liefert die aktuell gebuchte Verpflegung.
     *
     * @return Verpflegung oder null, falls keine gebucht
     */
    public Verpflegung getVerpflegung() {
        if (verpflegung.isEmpty()) return null;
        return verpflegung.iterator().next(); // Gibt die erste Verpflegung zurück
    }

    /**
     * Bucht eine Verpflegung für das Zimmer.
     * <p>
     * Vorherige Verpflegung wird gelöscht.
     * </p>
     *
     * @param verpflegung Verpflegung, die gebucht werden soll
     */
    public void bucheVerpflegung(Verpflegung verpflegung) {
        this.verpflegung.clear();
        this.verpflegung.add(verpflegung);
    }

    /**
     * Storniert die gebuchte Verpflegung.
     */
    public void storniereVerpflegung() {
        this.verpflegung.clear();
    }

    /**
     * Liefert die maximale Anzahl an Personen für das Zimmer.
     *
     * @return Maximale Personenanzahl
     */
    public int getMaxPersonen() {
        return typ.getMaxPersonen();
    }

    /**
     * Liefert den Preis pro Nacht des Zimmers.
     *
     * @return Preis pro Nacht
     */
    public double getPreisProNacht() {
        return typ.getPreisProNacht();
    }

    /**
     * Prüft, ob das Zimmer bereits ausgecheckt wurde.
     *
     * @return true, falls ausgecheckt, sonst false
     */
    public boolean isAusgecheckt() {
        return ausgecheckt;
    }

    /**
     * Setzt den ausgecheckt-Status des Zimmers.
     *
     * @param ausgecheckt true, falls ausgecheckt, sonst false
     */
    public void setAusgecheckt(boolean ausgecheckt) {
        this.ausgecheckt = ausgecheckt;
    }

    /**
     * Liefert den Preis des Zimmers (identisch mit Preis pro Nacht).
     *
     * @return Preis des Zimmers
     */
    public double getPreis() {
        return getPreisProNacht();
    }

    /**
     * Liefert den Namen der eingecheckten Hauptperson.
     *
     * @return Name der Hauptperson oder null, falls nicht belegt
     */
    public String getBelegterGast() {
        return belegterGast;
    }

    /**
     * Setzt den Namen der eingecheckten Hauptperson.
     *
     * @param name Name der Hauptperson
     */
    public void setBelegterGast(String name) {
        this.belegterGast = name;
    }

    /**
     * Setzt den Belegungsstatus des Zimmers und den Namen der Hauptperson.
     * <p>
     * Beim Belegen wird das Zimmer automatisch als nicht ausgecheckt markiert.
     * </p>
     *
     * @param belegt   Belegungsstatus
     * @param gastName Name der Hauptperson
     */
    public void setBelegt(boolean belegt, String gastName) {
        this.belegt = belegt;
        if (belegt) {
            this.ausgecheckt = false;
            this.belegterGast = gastName;
        } else {
            this.belegterGast = null;
        }
    }

    /**
     * Überschreibt die Standard-String-Repräsentation.
     * <p>
     * Zeigt Zimmernummer, Typ, maximale Personen, Preis, Status und gebuchte Verpflegung an.
     * </p>
     *
     * @return Formatierter String mit Zimmerinformationen
     */
    @Override
    public String toString() {
        String status;
        if (belegt) {
            status = "belegt durch " + (belegterGast != null ? belegterGast : "unbekannt");
        } else if (reserviert) {
            status = "reserviert für " + reservierterGast;
        } else {
            status = "frei";
        }
        String verpflegungStr = verpflegung.isEmpty() ? "keine Verpflegung gebucht" : "Verpflegung: " + getVerpflegung();

        return "Zimmer " + zimmernummer + " (" + typ + ") - max. Personen: " + getMaxPersonen() +
                ", Preis/Nacht: " + getPreisProNacht() + "€, Status: " + status + ", " + verpflegungStr;
    }
}