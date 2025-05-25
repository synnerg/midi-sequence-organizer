package persistence;

import model.SequenceLibrary;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes sequence library data to a JSON file.
 */
public class JsonWriter {
    private String filePath;

    /**
     * Constructs a JsonWriter.
     *
     * @param filePath the file path to save data
     */
    public JsonWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the sequence library to file.
     *
     * @param library the library to save
     * @throws IOException if an error occurs while writing
     */
    public void save(SequenceLibrary library) throws IOException {
        JSONObject json = library.toJson();
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(json.toString(4)); // Pretty print with indentation
        }
    }
}
