import java.util.Arrays;
import java.util.List;

/**
 * Enum für die verschiedenen Zimmertypen eines Hotels.
 * <p>
 * Enthält Preis pro Nacht, maximale Personenanzahl und Ausstattungsmerkmale.
 * </p>
 */
public enum zimmerTyp {
    EINZELZIMMER(50.0, 1, Arrays.asList("TV", "WLAN")),                   // Standard-Einzelzimmer
    DOPPELZIMMER(80.0, 2, Arrays.asList("TV", "WLAN", "Balkon")),        // Doppelzimmer
    SUITE(150.0, 4, Arrays.asList("TV", "WLAN", "Balkon", "Whirlpool")); // Luxussuite

    private final double preisProNacht;     // Preis pro Nacht für den Zimmertyp
    private final int maxPersonen;          // Maximale Personenanzahl für den Zimmertyp
    private final List<String> ausstattung; // Ausstattung des Zimmertyps

    /**
     * Konstruktor für das Enum zimmerTyp.
     *
     * @param preisProNacht Preis pro Nacht
     * @param maxPersonen   Maximale Anzahl an Personen
     * @param ausstattung   Liste der Ausstattungsmerkmale
     */
    zimmerTyp(double preisProNacht, int maxPersonen, List<String> ausstattung) {
        this.preisProNacht = preisProNacht;
        this.maxPersonen = maxPersonen;
        this.ausstattung = ausstattung;
    }

    /**
     * Liefert den Preis pro Nacht für diesen Zimmertyp.
     *
     * @return Preis pro Nacht
     */
    public double getPreisProNacht() {
        return preisProNacht;
    }

    /**
     * Liefert die maximale Anzahl an Personen für diesen Zimmertyp.
     *
     * @return Maximale Personenanzahl
     */
    public int getMaxPersonen() {
        return maxPersonen;
    }

    /**
     * Liefert die Ausstattungsmerkmale des Zimmertyps.
     *
     * @return Liste der Ausstattungsmerkmale
     */
    public List<String> getAusstattung() {
        return ausstattung;
    }
}