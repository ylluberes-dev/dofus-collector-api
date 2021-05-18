package com.ylluberes.dofus_collector_api.service;


import com.ylluberes.dofus_collector_api.domain.Users;

import java.util.List;

public interface UserService {
    Users  saveOrUpdate(Users user) throws Exception;
    List<Users> getAllUsers () throws Exception;
    Users findById (String id) throws Exception;
}
