package com.ylluberes.dofus_collector_api;


import com.ylluberes.dofus_collector_api.dao.GameContentDao;
import com.ylluberes.dofus_collector_api.dao.UserDao;
import com.ylluberes.dofus_collector_api.domain.Game;
import com.ylluberes.dofus_collector_api.domain.Mission;
import com.ylluberes.dofus_collector_api.domain.Users;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.domain.types.MissionType;
import com.ylluberes.dofus_collector_api.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class AppTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GameService gameService;


    @Autowired
    private GameContentDao gameContentDao;

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
            Users user = new Users();
            user.setUsername("User "+i);
            user.setPassword(UUID.randomUUID().toString());
            List<Game> gameList = new ArrayList<>();
            for (int g = 1; g <= 3; g++){
                try {
                    gameList.add(gameService.addNewGame(GameType.DOFUS_TOUCH));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            user.setGame(gameList);
            userDao.save(user);
        }
        Instant after = Instant.now();

        System.out.println("Total time execution: "+ Duration.between(now,after));

    }

    @Test
    public void testLoadMission (){
        Mission eternalHarvest = null;
        try{
           eternalHarvest = gameContentDao.map(GameType.DOFUS_TOUCH, MissionType.ETERNAL_HARVEST);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println(eternalHarvest);
    }



}
