package notes.service;

import notes.entity.Note;
import java.util.List;

public interface NotesService {
    void addNote(Note note) throws NotesException;
    List<Note> getAllNotes() throws NotesException;
    void updateNotes(Note note) throws NotesException;
    void deleteNote(Long noteId) throws NotesException;
}
