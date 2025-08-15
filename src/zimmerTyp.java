import java.util.Arrays;
import java.util.List;

public enum zimmerTyp {
    EINZELZIMMER(50.0, 1, Arrays.asList("TV", "WLAN")),
    DOPPELZIMMER(80.0, 2, Arrays.asList("TV", "WLAN", "Balkon")),
    SUITE(150.0, 4, Arrays.asList("TV", "WLAN", "Balkon", "Whirlpool"));

    private final double preisProNacht;
    private final int maxPersonen;
    private final List<String> ausstattung;

    zimmerTyp(double preisProNacht, int maxPersonen, List<String> ausstattung) {
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
