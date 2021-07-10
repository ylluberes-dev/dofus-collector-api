package com.ylluberes.dofus.collector.api.service;


import com.ylluberes.dofus.collector.api.dto.request.InAuthLogin;
import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import com.ylluberes.dofus.collector.api.domain.User;
import com.ylluberes.dofus.collector.api.dto.responses.OutLoginResponse;

import java.util.List;

public interface UserService {
    GenericResponse<User> saveOrUpdate(User userId);
    GenericResponse<List<User>> getAllUsers();
    GenericResponse<User> findById(String userId);
    GenericResponse<User> attachMission(String userId);
    GenericResponse<OutLoginResponse> login(InAuthLogin inAuthLogin);
}
