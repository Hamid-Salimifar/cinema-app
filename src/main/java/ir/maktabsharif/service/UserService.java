package ir.maktabsharif.service;

import ir.maktabsharif.model.Users;

public interface UserService extends BaseService<Users>{

    void addMovieToUser(Integer userId, Integer movieID);

    void removeMovieFromUserWatchlist(Integer userId, Integer movieID);

    Users loginUser(String userName,String password);



}
