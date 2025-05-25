# MIDI Sequence Organizer

## A Java Application for Managing MIDI Data

## **Project Description**

The MIDI Sequence Organizer is a Java desktop application that allows users to create, manage, and organize MIDI sequences. Users can define sequences with attributes such as tempo, instrument, and duration, store them in a library, and later retrieve or edit them. The project follows a standard step by step development approach, starting with a console-based interface, adding data persistence, and eventually implementing a graphical user interface (GUI). 

I have a strong passion for both music and software development, and this project allows me to combine these interests in a meaningful way. By creating a structured way to manage MIDI sequences, I can develop skills in object-oriented programming, data persistence, and user interface design while working on a topic that excites me.

The application uses Java's built-in MIDI package to allow users to program musical sequences. It is designed for musicians, producers, and hobbyists who work with MIDI files. Whether composing music, experimenting with sounds, or organizing compositions, the MIDI Sequence Organizer provides a structured and efficient way to manage musical ideas.

**Features:** 
- Create and manage MIDI notes with tempo, instrument, and duration
- Store sequences in a library for easy access and editing
- Modify and delete sequences in a list format
- Save and load sequences for long-term storage.
- GUI integration in later phases for enhanced User Interaction

**User Stories** 
- As a user, I want to be able to add a MIDI sequence to my library.
- As a user, I want to view a list of all MIDI sequences stored in my library.
- As a user, I want to edit a sequence to change its attributes.
- As a user, I want to delete a sequence I no longer need.
- As a user, I want to be able to save my sequence library to a file (if I choose to).
- As a user, I want to be able to load my sequence library from a file (if I choose to).

# Instructions for End User

- You can generate the first required action related to the user story "adding multiple MIDI Sequences to the Library" by entering the sequence details (Name, Instrument #, Tempo, Duration) into the input fields at the top of the window and clicking the **"Add Sequence"** button.

- You can generate the second required action related to this user story by clicking the **"Remove Sequence"** button, which removes the selected MIDI sequence currently in the library.

- You can locate my visual component at the bottom of the application window, where a colorful MIDI controller image is displayed to enhance the visual appeal of the interface.

- You can save the state of my application by clicking the **"Save Library"** button at the bottom of the window. This writes all current MIDI sequences to `data/sequences.json`.

- You can reload the state of my application by clicking the **"Load Library"** button at the bottom of the window. This reads MIDI sequences from the previously saved file and displays them in the interface.

# Phase 4: Task 2

- Sun Mar 30 09:39:07 PDT 2025
- Added sequence: TestSeq1

- Sun Mar 30 09:39:24 PDT 2025
- Added sequence: Fuel n Fire

- Sun Mar 30 09:39:37 PDT 2025
- Added sequence: Fade to black

- Sun Mar 30 09:39:41 PDT 2025
- Removed sequence: Fuel n Fire

## Phase 4: Task 3

The UML class diagram included in this project captures the final design of the MIDI Sequence Organizer application. It displays all key classes used in the program: `MidiAppGUI`, `SequenceLibrary`, `MidiSequence`, `JsonReader`, `JsonWriter`, `Event`, and `EventLog`. The relationships between these classes are clearly shown using associations and multiplicities. In particular, `EventLog` is represented as a singleton that implements `Iterable<Event>`, and `MidiAppGUI` is shown extending `JFrame`, accurately reflecting its role as the entry point for the graphical interface.

If I had more time to refine the project, I would consider improving the separation of concerns in the persistence layer. At present, `JsonReader` and `JsonWriter` are closely tied to specific file paths and data structures. Introducing a more abstract interface to handle storage could allow for greater flexibility and easier testing. Another potential improvement would be to enhance the logging system by allowing it to support different output modes, such as saving logs to a file, which could be useful in larger or production-level applications.



   