import java.util.*;

public class Hotel {
    private List<Zimmer> zimmerListe;
    private List<Bewertung> bewertungen = new ArrayList<>();

    public Hotel() {
        zimmerListe = new ArrayList<>();   //Generiert automatisch 12 Zimmer, 5 EZ, 5 DZ, 2 Suiten
        int nummer = 1;
        for (int i = 0; i < 5; i++) {
            zimmerListe.add(new Zimmer(nummer++, zimmerTyp.EINZELZIMMER, zimmerTyp.EINZELZIMMER.getAusstattung()));
        }
        for (int i = 0; i < 5; i++) {
            zimmerListe.add(new Zimmer(nummer++, zimmerTyp.DOPPELZIMMER, zimmerTyp.DOPPELZIMMER.getAusstattung()));
        }
        for (int i = 0; i < 2; i++) {
            zimmerListe.add(new Zimmer(nummer++, zimmerTyp.SUITE, zimmerTyp.SUITE.getAusstattung()));
        }
    }

    public boolean reserveZimmer(int zimmernummer, String gastName) {
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                if (!z.isBelegt() && !z.isReserviert()) {  //Falls Zimmer !NICHT! belegt und !NICHT! reserviert
                    z.reservieren(gastName);
                    System.out.println("Zimmer " + zimmernummer + " wurde für " + gastName + " reserviert.");
                    return true;
                } else if (z.isReserviert()) {
                    System.out.println("Zimmer " + zimmernummer + " ist bereits reserviert für " + z.getReservierterGast() + ".");
                    return false;
                } else {
                    System.out.println("Zimmer " + zimmernummer + " ist bereits belegt.");
                    return false;
                }
            }
        }
        System.out.println("Zimmer " + zimmernummer + " existiert nicht.");
        return false;
    }

    public void zeigeAlleZimmer() {
        for (Zimmer z : zimmerListe) {
            System.out.println(z.toString() + ", Ausstattung: " + z.getAusstattung());
        }
    }

    public List<Zimmer> getZimmerListe() {

        return zimmerListe;
    }

    public boolean checkIn(int zimmernummer) {
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                if (!z.isBelegt()) {
                    z.setBelegt(true);
                    System.out.println("Check-in erfolgreich für Zimmer " + zimmernummer);
                    return true;
                } else {
                    System.out.println("Zimmer " + zimmernummer + " ist bereits belegt.");
                    return false;
                }
            }
        }
        System.out.println("Zimmer " + zimmernummer + " existiert nicht.");
        return false;
    }

    public boolean checkOut(int zimmernummer) {
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                if (z.isBelegt()) {
                    z.setBelegt(false);
                    z.stornieren();
                    z.storniereVerpflegung();
                    System.out.println("Check-out erfolgreich für Zimmer " + zimmernummer + ". Reservierung und Verpflegung wurden entfernt.");
                    return true;
                } else {
                    System.out.println("Zimmer " + zimmernummer + " ist bereits frei.");
                    return false;
                }
            }
        }
        System.out.println("Zimmer " + zimmernummer + " existiert nicht.");
        return false;
    }

    public boolean bucheVerpflegung(int zimmernummer, Verpflegung verpflegung) {
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                if (!z.isBelegt()) {
                    System.out.println("Verpflegung kann nur gebucht werden, wenn das Zimmer belegt ist.");
                    return false;
                }
                z.bucheVerpflegung(verpflegung);
                System.out.println("Verpflegung " + verpflegung + " für Zimmer " + zimmernummer + " gebucht.");
                return true;
            }
        }
        System.out.println("Zimmer " + zimmernummer + " existiert nicht.");
        return false;
    }

    public void addBewertung(int sterne, String kommentar) {
        bewertungen.add(new Bewertung(sterne, kommentar));
    }

    public void zeigeBewertungenStatistik() {
        if (bewertungen.isEmpty()) {
            System.out.println("Noch keine Bewertungen vorhanden.");
            return;
        }
    int summe = 0;
    for (Bewertung b : bewertungen) {
       summe += b.getSterne();
    }
        double avg = (double) summe / bewertungen.size();
        System.out.println("Durchschnittliche Bewertung: " + avg + "/5");
        System.out.println("Alle Kommentare:");
        for (Bewertung b : bewertungen) {
            System.out.println(b);
        }
    }

    public double berechneEinnahmen() {
        double summe = 0;
        for (Zimmer z : zimmerListe) {
            if (z.isBelegt()) {
                summe += z.getPreisProNacht();
            }
        }
        return summe;
    }

    public void zeigeAuslastungProKategorie() {
        int ezGesamt = 0, ezBelegt = 0;
        int dzGesamt = 0, dzBelegt = 0;
        int suiteGesamt = 0, suiteBelegt = 0;
        for (Zimmer z : zimmerListe) {
            switch (z.getTyp()) {
                case EINZELZIMMER:
                    ezGesamt++;
                    if (z.isBelegt()) ezBelegt++;
                    break;
                case DOPPELZIMMER:
                    dzGesamt++;
                    if (z.isBelegt()) dzBelegt++;
                    break;
                case SUITE:
                    suiteGesamt++;
                    if (z.isBelegt()) suiteBelegt++;
                    break;
            }
        }
        int ezProzent = ezBelegt * 100 / ezGesamt;
        int dzProzent = dzBelegt * 100 / dzGesamt;
        int suiteProzent = suiteBelegt * 100 / suiteGesamt;
        System.out.println("Einzelzimmer: " + ezBelegt + "/" + ezGesamt + " belegt. " + "Auslastung: " + ezProzent + "%");
        System.out.println("Doppelzimmer: " + dzBelegt + "/" + dzGesamt + " belegt. " + "Auslastung: " + dzProzent + "%");
        System.out.println("Suiten: " + suiteBelegt + "/" + suiteGesamt + " belegt. " + "Auslastung: " + suiteProzent + "%");
    }
}
