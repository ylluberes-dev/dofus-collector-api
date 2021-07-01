package com.ylluberes.dofus_collector_api.service;


import com.ylluberes.dofus_collector_api.domain.User;
import com.ylluberes.dofus_collector_api.dto.responses.GenericResponse;
import java.util.List;

public interface UserService {
    GenericResponse<User> saveOrUpdate(User userId);
    GenericResponse<List<User>> getAllUsers();
    GenericResponse<User> findById(String userId);
    GenericResponse<User> attachMission(String userId);
}
