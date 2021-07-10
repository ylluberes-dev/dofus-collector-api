package com.ylluberes.dofus.collector.api.dao;

import com.ylluberes.dofus.collector.api.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserDao extends MongoRepository<User, String> {
    Optional<User> findByUsername (String username);
}
