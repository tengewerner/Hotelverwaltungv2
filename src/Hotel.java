import java.util.*;

public class Hotel {        // Klasse Hotel
    private List<Zimmer> zimmerListe;
    private List<Bewertung> bewertungen = new ArrayList<>();
    private List<String> reinigungsplan = new ArrayList<>();
    private int zimmerserviceEssenGesamt = 0;

    public Hotel() {        // Konstruktor der Hotel-Klasse initialisiert die Zimmerliste
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

    public boolean reserveZimmer(int zimmernummer, String gastName) {       // Methode zum Reservieren eines Zimmers
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
    public void bestelleZimmerservice(int zimmernummer, int personen) {     // Methode zum Bestellen von Zimmerservice
        Zimmer zimmer = null;
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                zimmer = z;
                break;
            }
        }
        if (zimmer == null) {       // Falls Zimmer nicht gefunden
            System.out.println("Zimmer existiert nicht.");
            return;
        }
        if (!zimmer.isBelegt()) {       // Falls Zimmer nicht belegt
            System.out.println("Zimmer ist nicht belegt.");
            return;
        }
        int maxPersonen = zimmer.getMaxPersonen();
        if (personen < 1 || personen > maxPersonen) {       // Falls ungültige Personenanzahl
            System.out.println("Ungültige Personenanzahl. Maximal erlaubt: " + maxPersonen);
            return;
        }
        zimmerserviceEssenGesamt += personen;
        System.out.println("Zimmerservice für " + personen + " Person(en) auf Zimmer " + zimmernummer + " bestellt.");
    }

    public int getZimmerserviceEssenGesamt() {      // Methode zum Abrufen der Gesamtanzahl an Zimmerservice-Essen
        return zimmerserviceEssenGesamt;
    }

    public int berechneEssenVerbrauchGesamt() {     // Methode zum Berechnen des gesamten Essensverbrauchs
        int gesamt = 0;
        for (Zimmer zimmer : zimmerListe) {
            int personen = zimmer.getMaxPersonen();
            Verpflegung v = zimmer.getVerpflegung();
            if (v == Verpflegung.VOLLPENSION) {
                gesamt += personen * 3;
            } else if (v == Verpflegung.HALBPENSION) {
                gesamt += personen * 2;
            } else if (v == Verpflegung.FRUEHSTUECK) {
                gesamt += personen * 1;
            }
        }
        gesamt += zimmerserviceEssenGesamt;
        return gesamt;
    }

    public void zeigeAlleZimmer() {     // Methode zum Anzeigen aller Zimmer
        for (Zimmer z : zimmerListe) {
            System.out.println(z.toString() + ", Ausstattung: " + z.getAusstattung());
        }
    }

    public List<Zimmer> getZimmerListe() {      // getter für die Zimmerliste

        return zimmerListe;
    }

    public boolean checkIn(int zimmernummer) {      // Methode zum Check-in eines Zimmers
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

    public boolean checkOut(int zimmernummer) {     // Methode zum Check-out eines Zimmers
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                if (z.isBelegt()) {
                    z.setBelegt(false);
                    z.stornieren();
                    z.storniereVerpflegung();
                    String zeitpunkt = java.time.LocalDateTime.now().toString();
                    String eintrag = "Zimmer " + zimmernummer + " gereinigt am " + zeitpunkt;
                    reinigungsplan.add(eintrag);
                    System.out.println("Check-out erfolgreich für Zimmer " + zimmernummer + ". Reservierung und Verpflegung wurden entfernt. \nReinigung des Zimmers wird veranlasst und ist in kürze beendet.\n");
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

    public boolean bucheVerpflegung(int zimmernummer, Verpflegung verpflegung) {      // Methode zum Buchen von Verpflegung für ein Zimmer
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

    public void addBewertung(int sterne, String kommentar) {        // Methode zum Hinzufügen einer Bewertung
        bewertungen.add(new Bewertung(sterne, kommentar));
    }

    public void zeigeBewertungenStatistik() {       // Methode zum Anzeigen der Bewertungen und deren Statistik
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

    public double berechneEinnahmenGesamt() {       // Methode zum Berechnen der Gesamteinnahmen
        double summe = 0;
        for (Zimmer z : zimmerListe) {
            if (z.isBelegt() || z.isAusgecheckt()) {
                summe += z.getPreis();
            }
        }
        return summe;
    }

    public void zeigeAuslastungProKategorie() {     // Methode zum Anzeigen der Auslastung pro Zimmerkategorie
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
        int ezProzent = ezBelegt * 100 / ezGesamt;      // Berechnung der Auslastung in Prozent
        int dzProzent = dzBelegt * 100 / dzGesamt;
        int suiteProzent = suiteBelegt * 100 / suiteGesamt;
        System.out.println("Einzelzimmer: " + ezBelegt + "/" + ezGesamt + " belegt. " + "Auslastung: " + ezProzent + "%");
        System.out.println("Doppelzimmer: " + dzBelegt + "/" + dzGesamt + " belegt. " + "Auslastung: " + dzProzent + "%");
        System.out.println("Suiten: " + suiteBelegt + "/" + suiteGesamt + " belegt. " + "Auslastung: " + suiteProzent + "%");
    }

    public void zeigeReinigungsplan() {     // Methode zum Anzeigen des Reinigungsplans
        if (reinigungsplan.isEmpty()) {
            System.out.println("Noch keine Zimmer gereinigt.");
        } else {
            System.out.println("--- Reinigungsplan ---");
            for (String eintrag : reinigungsplan) {
                System.out.println(eintrag);
            }
        }
    }
}
