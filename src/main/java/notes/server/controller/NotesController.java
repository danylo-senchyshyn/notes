package notes.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @GetMapping
    public List<String> getNotes() {
        return List.of("Note 1", "Note 2");
    }
}