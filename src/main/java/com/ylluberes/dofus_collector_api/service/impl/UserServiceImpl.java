package com.ylluberes.dofus_collector_api.service.impl;

import com.ylluberes.dofus_collector_api.dao.UserDao;
import com.ylluberes.dofus_collector_api.domain.Users;
import com.ylluberes.dofus_collector_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Users saveOrUpdate (Users user) {
        Users createdUser;
        try {
            createdUser =  userDao.save(user);
        }catch (Exception ex){
            throw(ex);
        }
        return createdUser;
    }

    @Override
    public List<Users> getAllUsers() {
        try{
            return userDao.findAll();
        }catch (Exception e){
            throw(e);
        }
    }

    @Override
    public Users findById(String id) {
        try{
            return userDao.findById(id).orElse(null);
        }catch (Exception e){
            throw(e);
        }
    }

}
