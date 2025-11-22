package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.UserRepository;

public class UserRepositoryImpl extends BaseRepositoryImpl<Users> implements UserRepository {
    public UserRepositoryImpl() {
        super(Users.class);
    }
}
