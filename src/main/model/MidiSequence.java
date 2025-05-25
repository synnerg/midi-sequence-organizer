package model;

import org.json.JSONObject;

// Represents a MIDI sequence with a name, instrument, tempo, and duration.
public class MidiSequence {
    private String name;
    private int instrument;
    private int tempo;
    private int duration;

    // Constructor: Constructs a new MIDI sequence with given parameters
    public MidiSequence(String name, int instrument, int tempo, int duration) {
        this.name = name;
        this.instrument = instrument;
        this.tempo = tempo;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getInstrument() {
        return instrument;
    }

    public int getTempo() {
        return tempo;
    }

    public int getDuration() {
        return duration;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // EFFECTS: Converts this MIDI sequence into a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("instrument", instrument);
        json.put("tempo", tempo);
        json.put("duration", duration);
        return json;
    }

    // EFFECTS: Constructs a MIDI sequence from a JSON object
    public static MidiSequence fromJson(JSONObject json) {
        return new MidiSequence(
                json.getString("name"),
                json.getInt("instrument"),
                json.getInt("tempo"),
                json.getInt("duration"));
    }

    // EFFECTS: Returns a string representation of the sequence
    @Override
    public String toString() {
        return "MIDI Sequence: " + name
                + " [Instrument: " + instrument
                + ", Tempo: " + tempo
                + ", Duration: " + duration + "]";
    }

}
