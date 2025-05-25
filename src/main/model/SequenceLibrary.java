package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Represents a library of MIDI sequences.
 * Allows adding, removing, retrieving, and serializing MIDI sequences.
 */
public class SequenceLibrary {
    private List<MidiSequence> sequences;

    /**
     * Constructs an empty sequence library.
     */
    public SequenceLibrary() {
        sequences = new ArrayList<>();
    }

    /**
     * REQUIRES: sequence is not null
     * MODIFIES: this
     * EFFECTS: Adds a new MIDI sequence to the library and logs the event.
     */
    public void addSequence(MidiSequence sequence) {
        sequences.add(sequence);
        EventLog.getInstance().logEvent(new Event("Added sequence: " + sequence.getName()));
    }

    /**
     * REQUIRES: sequence is not null and exists in the library
     * MODIFIES: this
     * EFFECTS: Removes the given MIDI sequence from the library and logs the event.
     */
    public void removeSequence(MidiSequence sequence) {
        sequences.remove(sequence);
        EventLog.getInstance().logEvent(new Event("Removed sequence: " + sequence.getName()));
    }

    /**
     * EFFECTS: Returns the list of all MIDI sequences in the library.
     */
    public List<MidiSequence> getSequences() {
        return sequences;
    }

    /**
     * EFFECTS: Converts this library into a JSON object for persistence.
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (MidiSequence seq : sequences) {
            jsonArray.put(seq.toJson());
        }

        json.put("sequences", jsonArray);
        return json;
    }

    /**
     * REQUIRES: json must be a valid JSON object containing a "sequences" array
     * EFFECTS: Constructs a SequenceLibrary from a JSON object
     */
    public static SequenceLibrary fromJson(JSONObject json) {
        SequenceLibrary library = new SequenceLibrary();
        JSONArray jsonArray = json.getJSONArray("sequences");

        for (int i = 0; i < jsonArray.length(); i++) {
            library.addSequence(MidiSequence.fromJson(jsonArray.getJSONObject(i)));
        }
        return library;
    }

    /**
     * REQUIRES: name is not null
     * EFFECTS: Returns the MIDI sequence with the given name, or null if not found.
     */
    public MidiSequence findSequence(String name) {
        for (MidiSequence seq : sequences) {
            if (seq.getName().equals(name)) {
                return seq;
            }
        }
        return null; 
    }
}
