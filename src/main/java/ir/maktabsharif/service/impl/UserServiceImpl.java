package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.UserRole;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.util.JPAConnection;
import ir.maktabsharif.util.PasswordUtil;
import jakarta.persistence.EntityManager;

public class UserServiceImpl extends BaseServiceImpl<Users> implements UserService {
    public UserServiceImpl(UserRepositoryImpl userRepository) {
        super(userRepository);
    }


    @Override
    public Users save(Users entity) {
        EntityManager em=JPAConnection.getJPAInstance().getEntityManager();
        em.getTransaction().begin();
        entity.setRole(UserRole.USER);
        em.persist(entity);
        em.getTransaction().commit();

        em.close();
        return entity;

    }

    @Override
    public void addMovieToUser(Integer userId, Integer movieID) {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        em.getTransaction().begin();
        Users user = em.find(Users.class, userId);
        Movie movie = em.find(Movie.class, movieID);
        user.getMovies().add(movie);

        em.merge(user);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void removeMovieFromUserWatchlist(Integer userId, Integer movieID) {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        em.getTransaction().begin();
        Users user = em.find(Users.class, userId);
        Movie movie = em.find(Movie.class, movieID);
        user.getMovies().remove(movie);
        em.merge(user);
        em.getTransaction().commit();

        em.close();

    }

    @Override
    public Users loginUser(String userName, String password) {
        Users user =findByName(userName);
        if(user != null && PasswordUtil.verifyPassword(password,user.getPassword())==true){
            return user;
        }
        return null;
    }
}
