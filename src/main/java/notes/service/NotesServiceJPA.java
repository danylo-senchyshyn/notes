package notes.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import notes.entity.Note;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Transactional
public class NotesServiceJPA implements NotesService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addNote(Note note) {
        note.setCreated_at(new Date());
        note.setUpdated_at(new Date());
        entityManager.persist(note);
    }

    @Override
    public List<Note> getAllNotes() throws NotesException {
        return entityManager
                .createNamedQuery("Note.getNotes", Note.class)
                .getResultList();
    }

    @Override
    public void updateNote(Note note) throws NotesException {
        Note existingNote = entityManager.find(Note.class, note.getId());
        if (existingNote != null) {
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
            existingNote.setUpdated_at(new Date());
        } else {
            throw new NotesException("Note not found with ID: " + note.getId());
        }
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

    @Override
    public List<Note> searchByTitle(String query) {
        return entityManager.createQuery(
                        "SELECT n FROM Note n WHERE LOWER(n.title) LIKE LOWER(:query)", Note.class)
                .setParameter("query", "%" + query + "%")
                .getResultList();
    }

    @Override
    public List<Note> filterByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return entityManager.createQuery(
                        "SELECT n FROM Note n WHERE n.created_at >= :start AND n.created_at < :end", Note.class)
                .setParameter("start", Date.from(start.atZone(ZoneId.systemDefault()).toInstant()))
                .setParameter("end", Date.from(end.atZone(ZoneId.systemDefault()).toInstant()))
                .getResultList();
    }
}