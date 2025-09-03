import java.io.Serializable;
import java.util.*;

/**
 * Repräsentiert ein Hotel mit einer Liste von Zimmern, Bewertungen, Reinigungsplan
 * und einer Übersicht über den bestellten Zimmerservice.
 */
public class Hotel implements Serializable {        // Klasse Hotel
    private static final long serialVersionUID = 1L;
    private List<Zimmer> zimmerListe;
    private List<Bewertung> bewertungen = new ArrayList<>();
    private List<String> reinigungsplan = new ArrayList<>();
    private int zimmerserviceEssenGesamt = 0;

    /**
     * Konstruktor initialisiert das Hotel mit 12 Zimmern:
     * 5 Einzelzimmer, 5 Doppelzimmer und 2 Suiten.
     */
    public Hotel() {
        zimmerListe = new ArrayList<>();
        int nummer = 1;

        // 5 Einzelzimmer anlegen
        for (int i = 0; i < 5; i++) {
            zimmerListe.add(new Zimmer(nummer++, zimmerTyp.EINZELZIMMER, zimmerTyp.EINZELZIMMER.getAusstattung()));
        }
        // 5 Doppelzimmer anlegen
        for (int i = 0; i < 5; i++) {
            zimmerListe.add(new Zimmer(nummer++, zimmerTyp.DOPPELZIMMER, zimmerTyp.DOPPELZIMMER.getAusstattung()));
        }
        // 2 Suiten anlegen
        for (int i = 0; i < 2; i++) {
            zimmerListe.add(new Zimmer(nummer++, zimmerTyp.SUITE, zimmerTyp.SUITE.getAusstattung()));
        }
    }

    /**
     * Reserviert ein Zimmer für einen Gast, falls verfügbar.
     *
     * @param zimmernummer Zimmernummer, die reserviert werden soll
     * @param gastName     Name des Gastes
     * @return {@code true}, wenn Reservierung erfolgreich war, sonst {@code false}
     */
    public boolean reserveZimmer(int zimmernummer, String gastName) {
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                // Bedingung: Zimmer darf weder belegt noch reserviert sein
                if (!z.isBelegt() && !z.isReserviert()) {
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

    /**
     * Bestellt Zimmerservice für eine bestimmte Anzahl an Personen in einem Zimmer.
     *
     * @param zimmernummer Zimmernummer
     * @param personen     Anzahl der Personen
     */
    public void bestelleZimmerservice(int zimmernummer, int personen) {
        Zimmer zimmer = null;

        // Zimmer anhand der Nummer suchen
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                zimmer = z;
                break;
            }
        }

        if (zimmer == null) {   // Zimmer nicht gefunden
            System.out.println("Zimmer existiert nicht.");
            return;
        }
        if (!zimmer.isBelegt()) {   // Zimmerservice nur für belegte Zimmer erlaubt
            System.out.println("Zimmer ist nicht belegt.");
            return;
        }

        int maxPersonen = zimmer.getMaxPersonen();
        // Bedingung: Personenanzahl muss gültig sein
        if (personen < 1 || personen > maxPersonen) {
            System.out.println("Ungültige Personenanzahl. Maximal erlaubt: " + maxPersonen);
            return;
        }

        zimmerserviceEssenGesamt += personen;  // Zimmerservice summiert sich auf
        System.out.println("Zimmerservice für " + personen + " Person(en) auf Zimmer " + zimmernummer + " bestellt.");
    }

    /**
     * Gibt die Gesamtanzahl aller bestellten Zimmerservice-Essen zurück.
     *
     * @return Gesamtanzahl Zimmerservice-Essen
     */
    public int getZimmerserviceEssenGesamt() {
        return zimmerserviceEssenGesamt;
    }

    /**
     * Berechnet den gesamten Essensverbrauch aller Gäste basierend auf der Verpflegung
     * und den bestellten Zimmerservices.
     *
     * @return Gesamter Essensverbrauch
     */
    public int berechneEssenVerbrauchGesamt() {
        int gesamt = 0;

        // Durchlaufe alle Zimmer und berechne Essen pro Person je nach Verpflegung
        for (Zimmer zimmer : zimmerListe) {
            int personen = zimmer.getMaxPersonen();
            Verpflegung v = zimmer.getVerpflegung();

            // Je nach Verpflegung multiplizieren wir mit Anzahl der Mahlzeiten
            if (v == Verpflegung.VOLLPENSION) {
                gesamt += personen * 3;
            } else if (v == Verpflegung.HALBPENSION) {
                gesamt += personen * 2;
            } else if (v == Verpflegung.FRUEHSTUECK) {
                gesamt += personen * 1;
            }
        }

        // Addiere bestellten Zimmerservice hinzu
        gesamt += zimmerserviceEssenGesamt;
        return gesamt;
    }

    /**
     * Zeigt eine Übersicht aller Zimmer an.
     */
    public void zeigeAlleZimmer() {
        for (Zimmer z : zimmerListe) {
            System.out.println(z.toString() + ", Ausstattung: " + z.getAusstattung());
        }
    }

    /**
     * Gibt die Zimmerliste des Hotels zurück.
     *
     * @return Liste der Zimmer
     */
    public List<Zimmer> getZimmerListe() {
        return zimmerListe;
    }

    /**
     * Führt einen Check-in für ein bestimmtes Zimmer durch.
     *
     * @param zimmernummer Zimmernummer
     * @return {@code true}, wenn erfolgreich, sonst {@code false}
     */
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

    /**
     * Führt einen Check-out für ein bestimmtes Zimmer durch.
     * <p>
     * Dabei werden Reservierung und Verpflegung entfernt
     * und das Zimmer wird in den Reinigungsplan aufgenommen.
     *
     * </p>
     * @param zimmernummer Zimmernummer
     * @return {@code true}, wenn erfolgreich, sonst {@code false}
     */
    public boolean checkOut(int zimmernummer) {
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                if (z.isBelegt()) {
                    z.setBelegt(false);
                    z.stornieren();             // Reservierung wird entfernt
                    z.storniereVerpflegung();   // Verpflegung wird entfernt

                    // Zimmer wird mit aktuellem Zeitpunkt im Reinigungsplan erfasst
                    String zeitpunkt = java.time.LocalDateTime.now().toString();
                    String eintrag = "Zimmer " + zimmernummer + " gereinigt am " + zeitpunkt;
                    reinigungsplan.add(eintrag);

                    System.out.println("Check-out erfolgreich für Zimmer " + zimmernummer +
                            ". Reservierung und Verpflegung wurden entfernt. \nReinigung des Zimmers wird veranlasst.\n");
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

    /**
     * Bucht eine bestimmte Verpflegung für ein Zimmer.
     *
     * @param zimmernummer Zimmernummer
     * @param verpflegung  Gewünschte Verpflegungsart
     * @return {@code true}, wenn erfolgreich, sonst {@code false}
     */
    public boolean bucheVerpflegung(int zimmernummer, Verpflegung verpflegung) {
        for (Zimmer z : zimmerListe) {
            if (z.getZimmernummer() == zimmernummer) {
                if (!z.isBelegt()) {   // Verpflegung nur möglich, wenn das Zimmer belegt ist
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

    /**
     * Fügt eine Bewertung für das Hotel hinzu.
     *
     * @param sterne    Anzahl Sterne (1–5)
     * @param kommentar Kommentar zur Bewertung
     */
    public void addBewertung(int sterne, String kommentar) {
        bewertungen.add(new Bewertung(sterne, kommentar));
    }

    /**
     * Zeigt die durchschnittliche Bewertung sowie alle Kommentare an.
     */
    public void zeigeBewertungenStatistik() {
        if (bewertungen.isEmpty()) {
            System.out.println("Noch keine Bewertungen vorhanden.");
            return;
        }

        int summe = 0;
        // Schleife summiert alle Sterne auf
        for (Bewertung b : bewertungen) {
            summe += b.getSterne();
        }

        double avg = (double) summe / bewertungen.size();  // Durchschnitt berechnen
        System.out.println("Durchschnittliche Bewertung: " + avg + "/5");
        System.out.println("Alle Kommentare:");
        for (Bewertung b : bewertungen) {
            System.out.println(b);
        }
    }

    /**
     * Berechnet die Gesamteinnahmen basierend auf allen belegten und ausgecheckten Zimmern.
     *
     * @return Gesamteinnahmen
     */
    public double berechneEinnahmenGesamt() {
        double summe = 0;
        for (Zimmer z : zimmerListe) {
            // Einnahmen zählen, wenn Zimmer entweder noch belegt oder bereits ausgecheckt wurde
            if (z.isBelegt() || z.isAusgecheckt()) {
                summe += z.getPreis();
            }
        }
        return summe;
    }

    /**
     * Zeigt die Auslastung für jede Zimmerkategorie (EZ, DZ, Suite) an.
     */
    public void zeigeAuslastungProKategorie() {
        int ezGesamt = 0, ezBelegt = 0;
        int dzGesamt = 0, dzBelegt = 0;
        int suiteGesamt = 0, suiteBelegt = 0;

        // Zähle belegte und verfügbare Zimmer je Kategorie
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

        // Prozentuale Auslastung berechnen
        int ezProzent = ezBelegt * 100 / ezGesamt;
        int dzProzent = dzBelegt * 100 / dzGesamt;
        int suiteProzent = suiteBelegt * 100 / suiteGesamt;

        System.out.println("Einzelzimmer: " + ezBelegt + "/" + ezGesamt + " belegt. Auslastung: " + ezProzent + "%");
        System.out.println("Doppelzimmer: " + dzBelegt + "/" + dzGesamt + " belegt. Auslastung: " + dzProzent + "%");
        System.out.println("Suiten: " + suiteBelegt + "/" + suiteGesamt + " belegt. Auslastung: " + suiteProzent + "%");
    }

    /**
     * Zeigt den Reinigungsplan des Hotels an.
     */
    public void zeigeReinigungsplan() {
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