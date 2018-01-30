package com.simple.bookmarks.repository;

import com.simple.bookmarks.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jcarretero on 29/01/2018.
 */
public interface UserRepository extends MongoRepository<User, String> {

}
