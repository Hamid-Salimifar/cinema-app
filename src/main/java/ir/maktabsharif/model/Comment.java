package ir.maktabsharif.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String authorName;
    @Lob
    @Column(nullable = false)
    @Size(max = 2000,message = "the comment can not exceed 2000 character")
    private String content;

    @ManyToOne
    @JoinColumn(name = "movie-id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user-id")
    private Users user;

    @Builder
    public Comment(String authorName, String content) {
        this.authorName = authorName;
        this.content = content;
    }
}
