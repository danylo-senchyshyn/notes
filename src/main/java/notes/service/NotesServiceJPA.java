package notes.service;

import notes.entity.Note;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class NotesServiceJPA implements NotesService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addNote(Note note) {
        entityManager.persist(note);
    }

    @Override
    public List<Note> getAllNotes() throws NotesException {
        return entityManager
                .createNamedQuery("Note.getNotes", Note.class)
                .getResultList();
    }

    @Override
    public void updateNotes(Note note) throws NotesException {
        entityManager.merge(note);
    }

    @Override
    public void deleteNote(Long noteId) throws NotesException {
        Note note = entityManager.find(Note.class, noteId);
        if (note != null) {
            entityManager.remove(note);
        } else {
            throw new NotesException("Note not found with ID: " + noteId);
        }
    }
}