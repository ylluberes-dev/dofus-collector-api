package com.ylluberes.dofus.collector.api;


import com.ylluberes.dofus.collector.api.domain.Game;
import com.ylluberes.dofus.collector.api.domain.Mission;
import com.ylluberes.dofus.collector.api.domain.User;
import com.ylluberes.dofus.collector.api.loaders.MissionLoader;
import com.ylluberes.dofus.collector.api.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class AppTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindAllUsers() {
        this.userDao.findAll().stream().forEach(x -> {
            System.out.println(x);
        });
    }

    @Test
    public void testFindUserById() {
        System.out.println(this.userDao.findById("609a4cf01e8c0a4dfffc055e").orElse(null));
    }

    @Test
    public void testAdd5000Users () {
        Instant now = Instant.now();
        for (int i = 1; i <= 5000; i++) {
            User user = new User();
            user.setUsername("User "+i);
            user.setPassword(UUID.randomUUID().toString());
            List<Game> gameList = new ArrayList<>();
            for (int g = 1; g <= 3; g++){
                try {
                  //  gameList.add(gameService.addNewGame(GameType.DOFUS_TOUCH));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
           // user.setGame(gameList);
            //userDao.save(user);
        }
        Instant after = Instant.now();

        System.out.println("Total time execution: "+ Duration.between(now,after));

    }

    @Test
    public void testLoadMission (){
       try {
           Mission mission = MissionLoader.getInstance().getMission();
           System.out.println("Mission: ");
           System.out.println();
           System.out.println(mission);
       }catch (Exception ex){
           System.out.println("Error while loading mission: "+ex);
       }
    }



}
