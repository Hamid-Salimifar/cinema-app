package ir.maktabsharif;

import ir.maktabsharif.model.Comment;
import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.UserRole;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.BaseRepositoryImpl;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        BaseRepositoryImpl baseRepository=new UserRepositoryImpl();
        Object sina = baseRepository.findByName("sina");
        System.out.println(sina);




//        // Create a Movie object
//        Movie movie = Movie.builder()
//                .title("Inception")
//                .genre("Science Fiction")
//                .description("A thief who steals corporate secrets through dream-sharing technology is given a chance to erase his criminal record.")
//                .duration("2h 28m")
//                .releaseDate("2010-07-16")
//                .rate(9)
//
//                .build();

        Users user = Users.builder()
                .userName("jane_smith")
                .email("jane.smith@example.com")
                .password("securePassword123")
                .role(UserRole.ADMIN)
                .build();
        Comment comment = Comment.builder()
                .authorName("John Doe")
                .content("Amazing movie! Highly recommended.")
                .build();
    }
}
