import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        MitarbeiterVerwaltung mitarbeiterVerwaltung = null;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n--- Hauptmenü ---");
            System.out.println("1. Gästeverwaltung");
            System.out.println("2. Mitarbeiterverwaltung");
            System.out.println("3. Statistik");
            System.out.println("0. Beenden");
            System.out.print("Bitte wählen: ");
            int hauptwahl = scanner.nextInt();
            scanner.nextLine();
            switch (hauptwahl) {
                case 1:
                    boolean gastMenue = true;
                    while (gastMenue) {
                        System.out.println("\n--- Gästeverwaltung ---");
                        System.out.println("1. Zimmer einsehen");
                        System.out.println("2. Gast einchecken");
                        System.out.println("3. Gast auschecken");
                        System.out.println("4. Zimmer reservieren");
                        System.out.println("5. Verpflegung buchen");
                        System.out.println("7. Reinigungsplan anzeigen");
                        System.out.println("8. Zimmerservice bestellen");
                        System.out.println("0. Zurück zum Hauptmenü");
                        System.out.print("Bitte wählen: ");
                        int gastwahl = scanner.nextInt();
                        scanner.nextLine();
                        switch (gastwahl) {
                            case 1:
                                hotel.zeigeAlleZimmer();
                                break;
                            case 2:
                                System.out.print("Zimmernummer für Check-in: ");
                                int checkInNr = scanner.nextInt();
                                scanner.nextLine();
                                hotel.checkIn(checkInNr);
                                break;
                            case 3:
                                System.out.print("Zimmernummer für Check-out: ");
                                int checkOutNr = scanner.nextInt();
                                scanner.nextLine();
                                boolean checkoutErfolg = hotel.checkOut(checkOutNr);
                                if (checkoutErfolg) {
                                    // Bewertung abfragen
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
                                    while (kommentar.trim().isEmpty()) {
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
                            case 4:
                                System.out.print("Zimmernummer für Reservierung: ");
                                int resNr = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Gastname: ");
                                String gastName = scanner.nextLine();
                                hotel.reserveZimmer(resNr, gastName);
                                break;
                            case 5:
                                System.out.print("Zimmernummer für Verpflegung: ");
                                int verpfNr = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Verpflegungsoptionen: 1. Vollpension 2. Halbpension 3. Frühstück");
                                int auswahl = -1;
                                while (auswahl < 1 || auswahl > 3) {
                                    System.out.print("Bitte wählen Sie eine Option (1, 2 oder 3): ");
                                    if (scanner.hasNextInt()) {
                                        auswahl = scanner.nextInt();
                                        scanner.nextLine();
                                        if (auswahl < 1 || auswahl > 3) {
                                            System.out.println("Nur 1, 2 oder 3 erlaubt!");
                                        }
                                    } else {
                                        System.out.println("Ungültige Eingabe! Bitte nur Zahlen eingeben.");
                                        scanner.nextLine();
                                    }
                                }
                                Verpflegung verpflegung;
                                if (auswahl == 1) {
                                    verpflegung = Verpflegung.VOLLPENSION;
                                } else if (auswahl == 2) {
                                    verpflegung = Verpflegung.HALBPENSION;
                                } else {
                                    verpflegung = Verpflegung.FRUEHSTUECK;
                                }
                                hotel.bucheVerpflegung(verpfNr, verpflegung);
                                break;
                            case 7:
                                hotel.zeigeReinigungsplan();
                                break;
                            case 8:
                                System.out.print("Zimmernummer für Zimmerservice: ");
                                int zsNr = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Für wie viele Personen soll Essen bestellt werden? ");
                                int pers = scanner.nextInt();
                                scanner.nextLine();
                                hotel.bestelleZimmerservice(zsNr, pers);
                                break;
                            case 0:
                                gastMenue = false;
                                break;
                            default:
                                System.out.println("Ungültige Auswahl!");
                        }
                    }
                    break;
                case 2:
                    boolean mitarbeiterMenue = true;
                    while (mitarbeiterMenue) {
                        System.out.println("\n--- Mitarbeiterverwaltung ---");
                        System.out.println("1. Mitarbeiter erstellen");
                        System.out.println("2. Mitarbeiter auflisten");
                        System.out.println("3. Einsatzplan anzeigen");
                        System.out.println("0. Zurück zum Hauptmenü");
                        System.out.print("Bitte wählen: ");
                        int mitarbeiterwahl = scanner.nextInt();
                        scanner.nextLine();
                        switch (mitarbeiterwahl) {
                            case 1:
                                if (mitarbeiterVerwaltung == null) mitarbeiterVerwaltung = new MitarbeiterVerwaltung();
                                System.out.print("Name des Mitarbeiters: ");
                                String name = scanner.nextLine();
                                System.out.println("Rolle wählen: 1. Rezeptionist 2. Küchenpersonal 3. Reinigungspersonal");
                                int rolleNr = scanner.nextInt();
                                scanner.nextLine();
                                Rolle rolle = null;
                                switch (rolleNr) {
                                    case 1: rolle = Rolle.REZEPTIONIST; break;
                                    case 2: rolle = Rolle.KUECHENPERSONAL; break;
                                    case 3: rolle = Rolle.REINIGUNGSPERSONAL; break;
                                    default: System.out.println("Ungültige Rolle!"); continue;
                                }
                                System.out.println("Schicht wählen: 1. Frühschicht 2. Mittagschicht 3. Spätschicht");
                                int schichtNr = scanner.nextInt();
                                scanner.nextLine();
                                Schicht schicht = null;
                                switch (schichtNr) {
                                    case 1: schicht = Schicht.FRUEHSCHICHT; break;
                                    case 2: schicht = Schicht.MITTAGSCHICHT; break;
                                    case 3: schicht = Schicht.SPAETSCHICHT; break;
                                    default: System.out.println("Ungültige Schicht!"); continue;
                                }
                                mitarbeiterVerwaltung.addMitarbeiter(name, rolle, schicht);
                                break;
                            case 2:
                                if (mitarbeiterVerwaltung == null) mitarbeiterVerwaltung = new MitarbeiterVerwaltung();
                                mitarbeiterVerwaltung.listMitarbeiter();
                                break;
                            case 3:
                                if (mitarbeiterVerwaltung == null) mitarbeiterVerwaltung = new MitarbeiterVerwaltung();
                                mitarbeiterVerwaltung.zeigeEinsatzplan();
                                break;
                            case 0:
                                mitarbeiterMenue = false;
                                break;
                            default:
                                System.out.println("Ungültige Auswahl!");
                        }
                    }
                    break;
                case 3:
                    boolean statistikMenue = true;
                    while (statistikMenue) {
                        System.out.println("\n--- Statistik ---");
                        System.out.println("1. Gesamteinnahmen (inkl. ausgecheckte Gäste) anzeigen");
                        System.out.println("2. Auslastung pro Kategorie anzeigen");
                        System.out.println("3. Bewertungsdurchschnitt und Kommentare anzeigen");
                        System.out.println("4. Essensverbrauch insgesamt anzeigen");
                        System.out.println("0. Zurück zum Hauptmenü");
                        System.out.print("Bitte wählen: ");
                        int statistikwahl = scanner.nextInt();
                        scanner.nextLine();
                        switch (statistikwahl) {
                            case 1:
                                double gesamt = hotel.berechneEinnahmenGesamt();
                                System.out.println("Gesamteinnahmen (inkl. ausgecheckte Gäste): " + gesamt);
                                break;
                            case 2:
                                hotel.zeigeAuslastungProKategorie();
                                break;
                            case 3:
                                hotel.zeigeBewertungenStatistik();
                                break;
                                case 4:
                                    int essenGesamt = hotel.berechneEssenVerbrauchGesamt();
                                    System.out.println("Insgesamt verbrauchtes Essen: " + essenGesamt + " Portionen");
                                    break;
                            case 0:
                                statistikMenue = false;
                                break;
                            default:
                                System.out.println("Ungültige Auswahl!");
                        }
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Programm beendet.");
                    break;
                default:
                    System.out.println("Ungültige Auswahl!");
            }
        }
        scanner.close();
    }
}