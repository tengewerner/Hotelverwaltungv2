import java.util.Arrays;
import java.util.List;

public enum zimmerTyp {     // Enum für die verschiedenen Zimmertypen im Hotel
    EINZELZIMMER(50.0, 1, Arrays.asList("TV", "WLAN")),
    DOPPELZIMMER(80.0, 2, Arrays.asList("TV", "WLAN", "Balkon")),
    SUITE(150.0, 4, Arrays.asList("TV", "WLAN", "Balkon", "Whirlpool"));

    private final double preisProNacht;     // Preis pro Nacht für den Zimmertyp
    private final int maxPersonen;      // Maximale Anzahl an Personen, die im Zimmer übernachten können
    private final List<String> ausstattung;     // Liste der Ausstattungsmerkmale des Zimmertyps

    zimmerTyp(double preisProNacht, int maxPersonen, List<String> ausstattung) {        // Konstruktor für den Zimmertyp
        this.preisProNacht = preisProNacht;
        this.maxPersonen = maxPersonen;
        this.ausstattung = ausstattung;
    }

    public double getPreisProNacht() {
        return preisProNacht;
    }

    public int getMaxPersonen() {
        return maxPersonen;
    }

    public List<String> getAusstattung() {
        return ausstattung;
    }
}
