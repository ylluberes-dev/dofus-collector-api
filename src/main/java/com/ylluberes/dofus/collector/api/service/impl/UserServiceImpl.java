package com.ylluberes.dofus.collector.api.service.impl;

import com.ylluberes.dofus.collector.api.dao.UserDao;
import com.ylluberes.dofus.collector.api.domain.Game;
import com.ylluberes.dofus.collector.api.domain.Mission;
import com.ylluberes.dofus.collector.api.domain.User;
import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import com.ylluberes.dofus.collector.api.loaders.MissionLoader;
import com.ylluberes.dofus.collector.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;


    @Override
    public GenericResponse<User> saveOrUpdate(User user) {
        GenericResponse<User> genericResponse = new GenericResponse<>();

        User createdUser;
        try {
            createdUser = userDao.save(user);
            genericResponse.setData(createdUser);
            genericResponse.setSuccess(true);
            genericResponse.setMessage("User successfully created");
            genericResponse.setServerStatus(HttpStatus.OK);

        } catch (Exception ex) {
            genericResponse.setData(null);
            genericResponse.setSuccess(false);
            genericResponse.setMessage("Unexpected error occurred during save or update user " + ex.getMessage());
            genericResponse.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return genericResponse;
        }
        return genericResponse;
    }

    @Override
    public GenericResponse<List<User>> getAllUsers() {
        GenericResponse<List<User>> genericResponse = new GenericResponse<>();
        try {
            List<User> userList = userDao.findAll();
            genericResponse.setData(userList);
            genericResponse.setSuccess(true);
            genericResponse.setMessage(userList.size() > 0 ? "User list" : "0 users in the database");
            genericResponse.setServerStatus(HttpStatus.OK);
        } catch (Exception e) {
            genericResponse.setData(null);
            genericResponse.setSuccess(false);
            genericResponse.setMessage("Unexpected error trying to retrieve all users");
            genericResponse.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return genericResponse;
    }

    @Override
    public GenericResponse<User> findById(String userId) {
        GenericResponse<User> response = new GenericResponse<>();
        User user = null;
        try {
            user = userDao.findById(userId).orElse(null);
            if (user == null) {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("User with id " + userId + " does not exists");
                response.setServerStatus(HttpStatus.NOT_FOUND);
            } else {
                response.setData(user);
                response.setSuccess(true);
                response.setMessage("User retrieve successfully");
                response.setServerStatus(HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Unexpected error trying to retrieve user with user id " + userId + " " + e.getMessage());
            response.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public GenericResponse<User> attachMission(String userId) {
        GenericResponse response = new GenericResponse();
        User user;
        try {
            user = userDao.findById(userId).orElse(null);
            if (user != null) {
                Game game = user.getGame();
                if (game != null) {
                    response.setData(null);
                    response.setSuccess(false);
                    response.setMessage("User already have a mission");
                    response.setServerStatus(HttpStatus.ALREADY_REPORTED);
                } else {
                    Game newGame = new Game();
                    newGame.setName("Dofus Touch");
                    newGame.setVersion("2.0");
                    Mission newMission = MissionLoader.getInstance().getMission();
                    newGame.setMission(newMission);
                    user.setGame(newGame);

                    userDao.save(user);
                    response.setData(user);
                    response.setMessage("Mission attached successfully");
                    response.setSuccess(true);
                    response.setServerStatus(HttpStatus.OK);
                }
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("User with id " + userId + " does not exists");
                response.setServerStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Unexpected error trying to attach mission");
            response.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
