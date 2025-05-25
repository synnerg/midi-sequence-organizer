package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MidiSequenceTest {
    private MidiSequence sequence;

    @BeforeEach
    void runBefore() {
        sequence = new MidiSequence("Test Sequence", 120, 100, 60);
    }

    @Test
    void testGetName() {
        assertEquals("Test Sequence", sequence.getName());
    }

    @Test
    void testGetTempo() {
        assertEquals(100, sequence.getTempo());
    }

    @Test
    void testSetTempo() {
        sequence.setTempo(200);
        assertEquals(200, sequence.getTempo());
    }
}
