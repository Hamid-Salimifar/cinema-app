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
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userName;
    private String email;
    private String password;

    @Lob
    private String profileImage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user-movie",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;


    @OneToMany(mappedBy = "user")
    private Set<Comment> comments=new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Rating> ratings =new HashSet<>();


    @Builder
    public Users(String userName, String email, String password, Set<Movie> movies,UserRole role) {

        this.userName = userName;
        this.email = email;
        this.password = password;
        this.movies = movies;
        this.role=role;
    }
}
