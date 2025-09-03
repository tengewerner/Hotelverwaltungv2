import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Verwaltungsklasse für das Speichern und Laden der Hotelverwaltung.
 * <p>
 * Speichert die Hotelobjekte in einer Datei und lädt sie beim Programmstart wieder.
 * </p>
 */
public class StorageManager {

    private static final String DATEINAME = "hotelverwaltung.ser"; // Standarddateiname für die Speicherung

    /**
     * Speichert das übergebene Hotel-Objekt in einer Datei.
     *
     * @param hotel Hotel-Objekt, das gespeichert werden soll
     */
    public static void save(Hotel hotel) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Path.of(DATEINAME)))) {
            oos.writeObject(hotel); // Serialisierung des Hotel-Objekts
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    /**
     * Lädt die Hotelverwaltung aus der Datei.
     * <p>
     * Falls die Datei nicht existiert oder ein Fehler auftritt, wird ein neues Hotel-Objekt erzeugt.
     * </p>
     *
     * @return Geladenes Hotel-Objekt oder neues Hotel, falls Fehler
     */
    public static Hotel load() {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Path.of(DATEINAME)))) {
            return (Hotel) ois.readObject(); // Deserialisierung des Hotel-Objekts
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
            return new Hotel(); // Rückfall auf neues Hotelobjekt
        }
    }
}
