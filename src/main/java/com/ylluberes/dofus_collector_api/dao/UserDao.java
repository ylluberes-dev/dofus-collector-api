package com.ylluberes.dofus_collector_api.dao;

import com.ylluberes.dofus_collector_api.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserDao extends MongoRepository<User, String> { }
