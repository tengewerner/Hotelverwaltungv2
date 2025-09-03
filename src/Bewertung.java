import java.io.Serializable;

/**
 * Repräsentiert eine Bewertung mit einer Anzahl von Sternen (1–5) und einem optionalen Kommentar.
 * <p>
 * Diese Klasse ist serialisierbar, um Bewertungen speichern und laden zu können.
 * </p>
 */
public class Bewertung implements Serializable {
    private static final long serialVersionUID = 1L;
    private int sterne; // 1-5
    private String kommentar;

    /**
     * Erstellt eine neue Bewertung mit Sternen und Kommentar.
     *
     * @param sterne    Anzahl der Sterne (1–5).
     * @param kommentar Kommentar zur Bewertung.
     */
    public Bewertung(int sterne, String kommentar) {
        this.sterne = sterne;
        this.kommentar = kommentar;
    }

    /**
     * Gibt die Anzahl der vergebenen Sterne zurück.
     *
     * @return Anzahl der Sterne (1–5).
     */
    public int getSterne() {
        return sterne;
    }

    /**
     * Gibt den Kommentar der Bewertung zurück.
     *
     * @return Kommentartext oder {@code null}, falls keiner vorhanden ist.
     */
    public String getKommentar() {
        return kommentar;
    }

    /**
     * Gibt eine textuelle Darstellung der Bewertung zurück.
     *
     * @return String in der Form "Bewertung: X/5 - Kommentar: ...".
     */
    @Override
    public String toString() {
        return "Bewertung: " + sterne + "/5 - Kommentar: " + kommentar;
    }
}