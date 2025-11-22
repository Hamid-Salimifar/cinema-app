package ir.maktabsharif.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor

@Entity
@Table(name="rating",
        uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "user_id"}))
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Rating(Integer rate) {
        this.rate = rate;
    }
}
