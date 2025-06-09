package notes.service;

import notes.entity.Note;

import java.time.LocalDate;
import java.util.List;

public interface NotesService {
    void addNote(Note note) throws NotesException;
    List<Note> getAllNotes() throws NotesException;
    void updateNote(Note note) throws NotesException;
    void deleteNote(Long noteId) throws NotesException;
    List<Note> searchByTitle(String query);
    List<Note> filterByDate(LocalDate date);
}