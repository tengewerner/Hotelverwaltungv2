import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class StorageManager {       // Klasse zum Speichern und Laden der Hotelverwaltung
    private static final String DATEINAME = "hotelverwaltung.ser";

    public static void save(Hotel hotel) {      // Methode zum Speichern der Hotelverwaltung in eine Datei
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Path.of(DATEINAME)))) {
            oos.writeObject(hotel);
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public static Hotel load() {        // Methode zum Laden der Hotelverwaltung aus einer Datei
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Path.of(DATEINAME)))) {
            return (Hotel) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
            return new Hotel();
        }
    }
}
