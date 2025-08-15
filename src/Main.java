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
                                hotel.checkOut(checkOutNr);
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
                                System.out.println("Verfügbare Verpflegung: 1. Frühstück 2. Mittagessen 3. Abendessen");
                                System.out.print("Bitte Nummer(n) mit Komma getrennt eingeben (z.B. 1,3): ");
                                String eingabe = scanner.nextLine();
                                String[] teile = eingabe.split(",");
                                Verpflegung[] verpflegungen = new Verpflegung[teile.length];
                                for (int i = 0; i < teile.length; i++) {
                                    switch (teile[i].trim()) {
                                        case "1": verpflegungen[i] = Verpflegung.FRUEHSTUECK; break;
                                        case "2": verpflegungen[i] = Verpflegung.MITTAGESSEN; break;
                                        case "3": verpflegungen[i] = Verpflegung.ABENDESSEN; break;
                                    }
                                }
                                hotel.bucheVerpflegung(verpfNr, verpflegungen);
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