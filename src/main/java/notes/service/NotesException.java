package notes.service;

public class NotesException extends RuntimeException {
    public NotesException(String message) {
        super(message);
    }

    public NotesException(String message, Throwable cause) {
        super(message, cause);
    }
}
