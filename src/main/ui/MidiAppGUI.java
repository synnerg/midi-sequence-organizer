package ui;

import javax.swing.*;
import java.awt.*;

import model.EventLog;
import model.MidiSequence;
import model.SequenceLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.IOException;
import model.Event;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MidiAppGUI extends JFrame {
    private SequenceLibrary library;

    private JTextField nameField;
    private JTextField instrumentField;
    private JTextField tempoField;
    private JTextField durationField;
    private DefaultListModel<String> sequenceListModel;
    private JList<String> sequenceJList;
    private JLabel statusLabel;
    private JPanel displayPanel;

    public MidiAppGUI() {
        super("MIDI Sequence Organizer");
        library = new SequenceLibrary();
        EventLog.getInstance(); // Ensures eventlog is used

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        initializeUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString() + "\n");
                }
            }
        }); // closes addWindowListener
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns the input panel for entering MIDI sequence
    // details

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add MIDI Sequence"));

        nameField = new JTextField();
        instrumentField = new JTextField();
        tempoField = new JTextField();
        durationField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Instrument #:"));
        inputPanel.add(instrumentField);
        inputPanel.add(new JLabel("Tempo:"));
        inputPanel.add(tempoField);
        inputPanel.add(new JLabel("Duration:"));
        inputPanel.add(durationField);

        JButton addButton = new JButton("Add Sequence");
        addButton.addActionListener(e -> addSequence());
        inputPanel.add(addButton);

        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> removeSelectedSequence());
        inputPanel.add(removeButton);

        return inputPanel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns the display panel for entering MIDI sequences
    private JPanel createDisplayPanel() {
        displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Library"));

        sequenceListModel = new DefaultListModel<>();
        sequenceJList = new JList<>(sequenceListModel);
        JScrollPane scrollPane = new JScrollPane(sequenceJList);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // Visual Component (image)
        ImageIcon imageIcon = new ImageIcon("data/midi_visual.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        displayPanel.add(imageLabel, BorderLayout.SOUTH);

        return displayPanel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns the panel for save/load buttons
    private JPanel createFilePanel() {
        JPanel filePanel = new JPanel(new BorderLayout());
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save Library");
        saveButton.addActionListener(e -> saveLibrary());
    
        JButton loadButton = new JButton("Load Library");
        loadButton.addActionListener(e -> loadLibrary());
    
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
    
        // Status label setup
        statusLabel = new JLabel("");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setForeground(Color.BLUE);
    
        filePanel.add(buttonPanel, BorderLayout.NORTH);
        filePanel.add(statusLabel, BorderLayout.SOUTH);
    
        return filePanel;
    }

    // MODIFIES: this
    // EFFECTS: Initializes and lays out the UI components
    private void initializeUI() {
        setLayout(new BorderLayout());
        add(createInputPanel(), BorderLayout.NORTH);
        add(createDisplayPanel(), BorderLayout.CENTER);
        add(createFilePanel(), BorderLayout.SOUTH);

        statusLabel = new JLabel("");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setForeground(Color.BLUE);
        

    }

    // MODIFIES: this
    // EFFECTS: Adds a new MidiSequence to the SequenceLibrary
    private void addSequence() {
        try {
            String name = nameField.getText();
            int instrument = Integer.parseInt(instrumentField.getText());
            int tempo = Integer.parseInt(tempoField.getText());
            int duration = Integer.parseInt(durationField.getText());

            MidiSequence sequence = new MidiSequence(name, instrument, tempo, duration);
            library.addSequence(sequence);
            updateDisplay();

            showStatusMessage("Sequence added!");

            nameField.setText("");
            instrumentField.setText("");
            tempoField.setText("");
            durationField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the selected sequence from the library and updates the
    // display
    private void removeSelectedSequence() {
        int selectedIndex = sequenceJList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedValue = sequenceListModel.getElementAt(selectedIndex);
            String name = selectedValue.substring("MIDI Sequence: ".length(), selectedValue.indexOf(" ["));
            MidiSequence toRemove = library.findSequence(name);

            if (toRemove != null) {
                library.removeSequence(toRemove);
                updateDisplay();
                showStatusMessage("Sequence removed!");
            }
        } else {
            showStatusMessage("No sequence selected.");
        }
    }

    // MODIFIES: sequenceListModel
    // EFFECTS: Clears and reloads all sequences in the display list
    private void updateDisplay() {
        sequenceListModel.clear();
        for (MidiSequence seq : library.getSequences()) {
            sequenceListModel.addElement(seq.toString());
        }
        showStatusMessage("Library refreshed.");
    }

    // MODIFIES: this
    // EFFECTS: Displays a temporary status message to the user for 3 seconds
    private void showStatusMessage(String message) {
        statusLabel.setText(message);
        Timer timer = new Timer(3000, e -> statusLabel.setText(" "));
        timer.setRepeats(false);
        timer.start();
    }

    // MODIFIES: file system
    // EFFECTS: Saves the current state of the library to a file
    private void saveLibrary() {
        JsonWriter writer = new JsonWriter("data/sequences.json");
        try {
            writer.save(library);
            JOptionPane.showMessageDialog(this, "Library saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to save library.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the state of the library from a file
    private void loadLibrary() {
        JsonReader reader = new JsonReader("data/sequences.json");
        try {
            SequenceLibrary loaded = reader.load();
            library.getSequences().clear();
            library.getSequences().addAll(loaded.getSequences());
            updateDisplay();
            JOptionPane.showMessageDialog(this, "Library loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load library.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
