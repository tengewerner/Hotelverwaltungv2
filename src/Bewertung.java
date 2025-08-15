public class Bewertung {
    private int sterne; // 1-5
    private String kommentar;

    public Bewertung(int sterne, String kommentar) {
        this.sterne = sterne;
        this.kommentar = kommentar;
    }

    public int getSterne() {
        return sterne;
    }

    public String getKommentar() {
        return kommentar;
    }

    @Override
    public String toString() {
        return "Bewertung: " + sterne + "/5 - Kommentar: " + kommentar;
    }
}

