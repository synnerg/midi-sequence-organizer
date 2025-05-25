package persistence;

import model.SequenceLibrary;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Reads sequence library data from a JSON file.
 */
public class JsonReader {
    private String filePath;

    /**
     * Constructs a JsonReader.
     *
     * @param filePath the file path to read from
     */
    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the sequence library from file.
     *
     * @return the loaded sequence library
     * @throws IOException if an error occurs while reading
     */
    public SequenceLibrary load() throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(jsonData);
        return SequenceLibrary.fromJson(jsonObject);
    }
}

