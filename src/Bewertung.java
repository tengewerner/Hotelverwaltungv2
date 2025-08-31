import java.io.Serializable;

public class Bewertung implements Serializable {        // Klasse Bewertung
    private static final long serialVersionUID = 1L;
    private int sterne; // 1-5
    private String kommentar;

    public Bewertung(int sterne, String kommentar) {        // Konstruktor mit Sternen und Kommentar
        this.sterne = sterne;
        this.kommentar = kommentar;
    }

    public int getSterne() {        // Getter für Sterne
        return sterne;
    }

    public String getKommentar() {      // Getter für Kommentar
        return kommentar;
    }

    @Override
    public String toString() {      // Überschreiben der toString-Methode für bessere Ausgabe
        return "Bewertung: " + sterne + "/5 - Kommentar: " + kommentar;
    }
}
