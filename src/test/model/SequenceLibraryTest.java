package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SequenceLibraryTest {
    private SequenceLibrary library;
    private MidiSequence seq1;
    private MidiSequence seq2;

    @BeforeEach
    void runBefore() {
        library = new SequenceLibrary();
        seq1 = new MidiSequence("Test Sequence 1", 3, 140, 60);
        seq2 = new MidiSequence("Test Sequence 2", 10, 100, 90);
    }

    @Test
    void testAddSequence() {
        library.addSequence(seq1);
        library.addSequence(seq2);
        List<MidiSequence> sequences = library.getSequences();

        assertEquals(2, sequences.size());
        assertTrue(sequences.contains(seq1));
        assertTrue(sequences.contains(seq2));
    }

    @Test
    void testRemoveSequence() {
        library.addSequence(seq1);
        library.addSequence(seq2);
        library.removeSequence(seq1);

        List<MidiSequence> sequences = library.getSequences();
        assertEquals(1, sequences.size());
        assertFalse(sequences.contains(seq1));
        assertTrue(sequences.contains(seq2));
    }

    @Test
    void testFindSequence() {
        library.addSequence(seq1);
        library.addSequence(seq2);

        assertEquals(seq1, library.findSequence("Test Sequence 1"));
        assertEquals(seq2, library.findSequence("Test Sequence 2"));
        assertNull(library.findSequence("Nonexistent Sequence"));
    }

    @Test
    void testEmptyLibrary() {
        assertTrue(library.getSequences().isEmpty());
        assertNull(library.findSequence("Test Sequence 1"));
    }

}

