package notes.server.webservice;

import notes.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import notes.service.NotesService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:5173") // обязательно для React
public class NotesServiceRest {
    @Autowired
    NotesService notesService;

    @GetMapping
    public List<Note> getAllNotes() {
        return notesService.getAllNotes();
    }

    @PostMapping
    public void addNote(@RequestBody Note note) {
        notesService.addNote(note);
    }

    @PutMapping("/{id}")
    public void updateNote(@PathVariable Long id, @RequestBody Note note) {
        note.setId(id);
        notesService.updateNote(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        notesService.deleteNote(id);
    }

    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam String query) {
        return notesService.searchByTitle(query);
    }

    @GetMapping("/filter")
    public List<Note> filterByDate(@RequestParam String date) {
        return notesService.filterByDate(LocalDate.parse(date));
    }
}
