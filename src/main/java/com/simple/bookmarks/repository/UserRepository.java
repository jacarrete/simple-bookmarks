package com.simple.bookmarks.repository;

import com.simple.bookmarks.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by jcarretero on 29/01/2018.
 */
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'username' : ?0 }")
    User findUserByUsername(String username);

    @Query("{ 'email' : ?0 }")
    User findUserByEmail(String email);

}
