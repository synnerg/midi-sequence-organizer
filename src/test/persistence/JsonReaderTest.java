package persistence;

import model.SequenceLibrary;
import model.MidiSequence;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JsonReader for loading SequenceLibrary data from a file.
 */
public class JsonReaderTest {

    @Test
    void testLoadLibrary() throws IOException {
        JsonReader reader = new JsonReader("data/testSave.json");
        SequenceLibrary library = reader.load();

        MidiSequence seq = library.getSequences().get(0);
        assertEquals("Test Sequence", seq.getName());
        assertEquals(1, seq.getInstrument());
        assertEquals(120, seq.getTempo());
        assertEquals(60, seq.getDuration());

    }
}
