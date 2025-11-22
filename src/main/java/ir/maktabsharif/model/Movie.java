package ir.maktabsharif.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

        private String title;
        private String genre;
        private String description;
        private String duration;
        private String releaseDate;
        private Double overAllRating;

    @Lob
    private String posterImage;

    @OneToMany(mappedBy = "movie",orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "movies")
    private Set<Users> users = new HashSet<>();

    @OneToMany(mappedBy = "movie",orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Rating> ratings=new HashSet<>();
    @Builder
    public Movie(String title, String genre, String description, String duration, String releaseDate, Double overAllRating, String posterImage) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.overAllRating = overAllRating;
        this.posterImage = posterImage;
    }
}
