package notes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Note.getNotes", query = "SELECT n FROM Note n"),
        @NamedQuery(name = "Note.reset", query = "DELETE FROM Note")
})
public class Note implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.created_at = new Date();
        this.updated_at = new Date();
    }

    @PrePersist
    protected void onCreate() {
        this.created_at = new Date();
        this.updated_at = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    }
}
