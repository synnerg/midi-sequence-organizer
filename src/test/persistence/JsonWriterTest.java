package persistence;

import model.SequenceLibrary;
import model.MidiSequence;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JsonWriter for saving SequenceLibrary data to a file.
 */
public class JsonWriterTest {

    @Test
    void testSaveLibrary() throws IOException {
        SequenceLibrary library = new SequenceLibrary();
        library.addSequence(new MidiSequence("Test Sequence", 1, 120, 60));

        JsonWriter writer = new JsonWriter("data/testSave.json");
        writer.save(library);

        assertTrue(true); // If no exception, test passes
    }
}
