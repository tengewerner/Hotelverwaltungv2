import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Startklasse für die Hotelverwaltung.
 * <p>
 * Stellt Hauptmenü, Gästeverwaltung, Mitarbeiterverwaltung und Statistik bereit.
 * </p>
 */
public class Main {

    /**
     * Hilfsmethode zum Einlesen einer Menüauswahl mit Bereichsprüfung.
     *
     * @param scanner Scanner-Objekt für die Benutzereingabe
     * @param prompt  Text, der dem Benutzer angezeigt wird
     * @param min     Minimale gültige Zahl
     * @param max     Maximale gültige Zahl
     * @return Ausgewählter Menüpunkt innerhalb des gültigen Bereichs
     */
    public static int liesMenueAuswahl(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int eingabe = Integer.parseInt(scanner.nextLine());
                // Prüfen, ob Eingabe im gültigen Bereich liegt
                if (eingabe >= min && eingabe <= max) {
                    return eingabe;
                } else {
                    System.out.println("Ungültige Auswahl! Bitte nur " + min + " bis " + max + " eingeben.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
            }
        }
    }

    /**
     * Hauptmethode der Anwendung. Zeigt das Hauptmenü an und verwaltet Benutzerinteraktionen.
     *
     */
    public static void main(String[] args) {
        Hotel hotel;
        MitarbeiterVerwaltung mitarbeiterVerwaltung;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Laden der Hotelverwaltung beim Start
        hotel = StorageManager.load();
        if (hotel == null) hotel = new Hotel();

        // Laden der Mitarbeiterverwaltung
        try (ObjectInputStream ois = new ObjectInputStream(
                java.nio.file.Files.newInputStream(java.nio.file.Path.of("mitarbeiterverwaltung.ser")))) {
            mitarbeiterVerwaltung = (MitarbeiterVerwaltung) ois.readObject();
        } catch (Exception e) {
            mitarbeiterVerwaltung = new MitarbeiterVerwaltung();
        }

        // Hauptschleife für das Menü
        while (running) {
            System.out.println("\n--- Hauptmenü ---");
            System.out.println("1. Gästeverwaltung");
            System.out.println("2. Mitarbeiterverwaltung");
            System.out.println("3. Statistik");
            System.out.println("0. Beenden");

            int hauptwahl = liesMenueAuswahl(scanner, "Bitte wählen: ", 0, 3);

            switch (hauptwahl) { // Auswahl nach Menüpunkt
                case 1: // Gästeverwaltung
                    boolean gastMenue = true;
                    while (gastMenue) {
                        System.out.println("\n--- Gästeverwaltung ---");
                        System.out.println("1. Zimmer einsehen");
                        System.out.println("2. Gast einchecken");
                        System.out.println("3. Gast auschecken");
                        System.out.println("4. Zimmer reservieren");
                        System.out.println("5. Verpflegung buchen");
                        System.out.println("6. Reinigungsplan anzeigen");
                        System.out.println("7. Zimmerservice bestellen");
                        System.out.println("0. Zurück zum Hauptmenü");

                        int gastwahl = liesMenueAuswahl(scanner, "Bitte wählen: ", 0, 7);

                        switch (gastwahl) {
                            case 1:
                                hotel.zeigeAlleZimmer();
                                break;

                            case 2: // Check-in eines Gastes
                                System.out.print("Zimmernummer für Check-in: ");
                                int checkInNr = -1;
                                while (checkInNr == -1) {
                                    try {
                                        checkInNr = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                        System.out.print("Zimmernummer für Check-in: ");
                                    }
                                }
                                System.out.print("Name der Hauptperson für Check-in: ");
                                String hauptGast = scanner.nextLine();
                                hotel.checkIn(checkInNr, hauptGast);
                                break;

                            case 3: // Check-out eines Gastes
                                System.out.print("Zimmernummer für Check-out: ");
                                int checkOutNr = -1;
                                while (checkOutNr == -1) { // Prüfen auf gültige Zahl
                                    try {
                                        checkOutNr = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                        System.out.print("Zimmernummer für Check-out: ");
                                    }
                                }

                                boolean checkoutErfolg = hotel.checkOut(checkOutNr);

                                // Bewertung abfragen, wenn Check-out erfolgreich
                                if (checkoutErfolg) {
                                    int sterne = -1;
                                    while (sterne < 1 || sterne > 5) {
                                        System.out.print("Bitte geben Sie eine Bewertung von 1 bis 5 Sternen ab: ");
                                        String sterneInput = scanner.nextLine();
                                        try {
                                            sterne = Integer.parseInt(sterneInput);
                                            if (sterne < 1 || sterne > 5) {
                                                System.out.println("Nur Werte von 1 bis 5 erlaubt!");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                        }
                                    }

                                    String kommentar = "";
                                    while (kommentar.trim().isEmpty()) { // Kommentar darf nicht leer sein
                                        System.out.print("Kommentar zur Bewertung: ");
                                        kommentar = scanner.nextLine();
                                        if (kommentar.trim().isEmpty()) {
                                            System.out.println("Kommentar darf nicht leer sein!");
                                        }
                                    }
                                    hotel.addBewertung(sterne, kommentar);
                                    System.out.println("Vielen Dank für Ihre Bewertung!");
                                }
                                break;

                            case 4: // Zimmer reservieren
                                System.out.print("Zimmernummer für Reservierung: ");
                                int resNr = -1;
                                while (resNr == -1) {
                                    try {
                                        resNr = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                        System.out.print("Zimmernummer für Reservierung: ");
                                    }
                                }
                                System.out.print("Gastname: ");
                                String gastName = scanner.nextLine();
                                hotel.reserveZimmer(resNr, gastName);
                                break;

                            case 5: // Verpflegung buchen
                                System.out.print("Zimmernummer für Verpflegung: ");
                                int verpfNr = -1;
                                while (verpfNr == -1) {
                                    try {
                                        verpfNr = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                        System.out.print("Zimmernummer für Verpflegung: ");
                                    }
                                }

                                // Auswahl der Verpflegungsoption
                                System.out.println("Verpflegungsoptionen: 1. Vollpension 2. Halbpension 3. Frühstück");
                                int auswahl = -1;
                                while (auswahl < 1 || auswahl > 3) {
                                    System.out.print("Bitte wählen Sie eine Option (1, 2 oder 3): ");
                                    try {
                                        auswahl = Integer.parseInt(scanner.nextLine());
                                        if (auswahl < 1 || auswahl > 3) {
                                            System.out.println("Nur 1, 2 oder 3 erlaubt!");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                    }
                                }

                                Verpflegung verpflegung;
                                if (auswahl == 1) verpflegung = Verpflegung.VOLLPENSION;
                                else if (auswahl == 2) verpflegung = Verpflegung.HALBPENSION;
                                else verpflegung = Verpflegung.FRUEHSTUECK;

                                hotel.bucheVerpflegung(verpfNr, verpflegung);
                                break;

                            case 6: // Reinigungsplan anzeigen
                                hotel.zeigeReinigungsplan();
                                break;

                            case 7: // Zimmerservice bestellen
                                System.out.print("Zimmernummer für Zimmerservice: ");
                                int zsNr = -1;
                                while (zsNr == -1) {
                                    try {
                                        zsNr = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                        System.out.print("Zimmernummer für Zimmerservice: ");
                                    }
                                }

                                System.out.print("Wie viele Portionen Essen sollen bestellt werden? ");
                                int pers = -1;
                                while (pers == -1) {
                                    try {
                                        pers = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                        System.out.print("Für wie viele Personen soll Essen bestellt werden? ");
                                    }
                                }

                                hotel.bestelleZimmerservice(zsNr, pers);
                                break;

                            case 0: // Zurück zum Hauptmenü
                                gastMenue = false;
                                break;

                            default:
                                System.out.println("Ungültige Auswahl!");
                        }
                    }
                    break;

                case 2: // Mitarbeiterverwaltung
                    boolean mitarbeiterMenue = true;
                    while (mitarbeiterMenue) {
                        System.out.println("\n--- Mitarbeiterverwaltung ---");
                        System.out.println("1. Mitarbeiter erstellen");
                        System.out.println("2. Mitarbeiter auflisten");
                        System.out.println("3. Einsatzplan anzeigen");
                        System.out.println("0. Zurück zum Hauptmenü");

                        int mitarbeiterwahl = liesMenueAuswahl(scanner, "Bitte wählen: ", 0, 3);

                        switch (mitarbeiterwahl) {
                            case 1: // Mitarbeiter erstellen
                                if (mitarbeiterVerwaltung == null) mitarbeiterVerwaltung = new MitarbeiterVerwaltung();
                                System.out.print("Name des Mitarbeiters: ");
                                String name = scanner.nextLine();

                                // Rolle auswählen
                                System.out.println("Rolle wählen: 1. Rezeptionist 2. Küchenpersonal 3. Reinigungspersonal");
                                int rolleNr = -1;
                                while (rolleNr < 1 || rolleNr > 3) {
                                    System.out.print("Bitte wählen Sie eine Rolle (1, 2 oder 3): ");
                                    try {
                                        rolleNr = Integer.parseInt(scanner.nextLine());
                                        if (rolleNr < 1 || rolleNr > 3) {
                                            System.out.println("Nur 1, 2 oder 3 erlaubt!");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                    }
                                }

                                Rolle rolle = null;
                                switch (rolleNr) {
                                    case 1: rolle = Rolle.REZEPTIONIST; break;
                                    case 2: rolle = Rolle.KUECHENPERSONAL; break;
                                    case 3: rolle = Rolle.REINIGUNGSPERSONAL; break;
                                    default: System.out.println("Ungültige Rolle!"); continue;
                                }

                                // Schicht auswählen
                                System.out.println("Schicht wählen: 1. Frühschicht 2. Mittagschicht 3. Spätschicht");
                                int schichtNr = -1;
                                while (schichtNr < 1 || schichtNr > 3) {
                                    System.out.print("Bitte wählen Sie eine Schicht (1, 2 oder 3): ");
                                    try {
                                        schichtNr = Integer.parseInt(scanner.nextLine());
                                        if (schichtNr < 1 || schichtNr > 3) {
                                            System.out.println("Nur 1, 2 oder 3 erlaubt!");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                    }
                                }

                                Schicht schicht = null;
                                switch (schichtNr) {
                                    case 1: schicht = Schicht.FRUEHSCHICHT; break;
                                    case 2: schicht = Schicht.MITTAGSCHICHT; break;
                                    case 3: schicht = Schicht.SPAETSCHICHT; break;
                                    default: System.out.println("Ungültige Schicht!"); continue;
                                }

                                mitarbeiterVerwaltung.addMitarbeiter(name, rolle, schicht);
                                break;

                            case 2: // Mitarbeiter auflisten
                                if (mitarbeiterVerwaltung == null) mitarbeiterVerwaltung = new MitarbeiterVerwaltung();
                                mitarbeiterVerwaltung.listMitarbeiter();
                                break;

                            case 3: // Einsatzplan anzeigen
                                if (mitarbeiterVerwaltung == null) mitarbeiterVerwaltung = new MitarbeiterVerwaltung();
                                mitarbeiterVerwaltung.zeigeEinsatzplan();
                                break;

                            case 0: // Zurück zum Hauptmenü
                                mitarbeiterMenue = false;
                                break;

                            default:
                                System.out.println("Ungültige Auswahl!");
                        }
                    }
                    break;

                case 3: // Statistik
                    boolean statistikMenue = true;
                    while (statistikMenue) { // Statistikmenü
                        System.out.println("\n--- Statistik ---");
                        System.out.println("1. Gesamteinnahmen (inkl. ausgecheckte Gäste) anzeigen");
                        System.out.println("2. Auslastung pro Kategorie anzeigen");
                        System.out.println("3. Bewertungsdurchschnitt und Kommentare anzeigen");
                        System.out.println("4. Essensverbrauch insgesamt anzeigen");
                        System.out.println("0. Zurück zum Hauptmenü");

                        int statistikwahl = liesMenueAuswahl(scanner, "Bitte wählen: ", 0, 4);

                        switch (statistikwahl) {
                            case 1: // Gesamteinnahmen anzeigen
                                double gesamt = hotel.berechneEinnahmenGesamt();
                                System.out.println("Gesamteinnahmen (inkl. ausgecheckte Gäste): " + gesamt);
                                break;

                            case 2: // Auslastung pro Kategorie anzeigen
                                hotel.zeigeAuslastungProKategorie();
                                break;

                            case 3: // Bewertungsdurchschnitt und Kommentare anzeigen
                                hotel.zeigeBewertungenStatistik();
                                break;

                            case 4: // Essensverbrauch insgesamt anzeigen
                                int essenGesamt = hotel.berechneEssenVerbrauchGesamt();
                                System.out.println("Insgesamt verbrauchtes Essen: " + essenGesamt + " Portionen");
                                break;

                            case 0: // Zurück zum Hauptmenü
                                statistikMenue = false;
                                break;

                            default:
                                System.out.println("Ungültige Auswahl!");
                        }
                    }
                    break;

                case 0: // Programm beenden
                    running = false;
                    System.out.println("Programm beendet.");
                    break;

                default:
                    System.out.println("Ungültige Auswahl!");
            }
        }

        // Speichern der Hotelverwaltung beim Beenden
        StorageManager.save(hotel);

        // Speichern der Mitarbeiterverwaltung
        try (ObjectOutputStream oos = new ObjectOutputStream(
                java.nio.file.Files.newOutputStream(java.nio.file.Path.of("mitarbeiterverwaltung.ser")))) {
            oos.writeObject(mitarbeiterVerwaltung);
        } catch (Exception e) {
            System.err.println("Fehler beim Speichern der MitarbeiterVerwaltung: " + e.getMessage());
        }

        scanner.close();
    }
}