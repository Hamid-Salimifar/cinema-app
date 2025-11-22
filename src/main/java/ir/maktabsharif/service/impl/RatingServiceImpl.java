package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.Rating;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.RatingRepository;
import ir.maktabsharif.repository.impl.BaseRepositoryImpl;
import ir.maktabsharif.repository.impl.RatingRepositoryImpl;
import ir.maktabsharif.service.RatingService;
import ir.maktabsharif.util.JPAConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class RatingServiceImpl extends BaseServiceImpl<Rating> implements RatingService {
    public RatingServiceImpl(RatingRepositoryImpl ratingRepository) {
        super(ratingRepository);
    }

    @Override
    public Rating findRatingByMovieAndUser(Movie movie, Users user) {
        EntityManager entityManager= JPAConnection.getJPAInstance().getEntityManager();
        try{
            return entityManager.createQuery("SELECT r from Rating r where r.movie= :movie and r.user= :user", Rating.class)
                    .setParameter("movie",movie)
                    .setParameter("user",user)
                    .getSingleResult();
        }catch (NoResultException e){
            e.printStackTrace();
            return null;
        }finally{
            entityManager.close();
        }
    }
}
