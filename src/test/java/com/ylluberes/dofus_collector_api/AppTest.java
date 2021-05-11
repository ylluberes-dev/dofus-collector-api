package com.ylluberes.dofus_collector_api;


import com.ylluberes.dofus_collector_api.dao.UserDao;
import com.ylluberes.dofus_collector_api.domain.*;
import com.ylluberes.dofus_collector_api.domain.games.Game;
import com.ylluberes.dofus_collector_api.domain.games.types.GameType;
import com.ylluberes.dofus_collector_api.exceptions.InvalidGameTypeException;
import com.ylluberes.dofus_collector_api.service.impl.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class AppTest {

    @Autowired
    private UserDao userDao;


    @Test
    public void testFindAllUsers () {
        this.userDao.findAll().stream().forEach(x->{
            System.out.println(x);
        });
    }

    @Test
    public void testFindUserById () {
        System.out.println(this.userDao.findById("609a4cf01e8c0a4dfffc055e").get());
    }

    @Test
    void buildDocumentStructure() {
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            Monster monster = new Monster("Jabalí de las llanuras", false, false, 0, "None", "/Path/to/some/image");
            monsters.add(monster);
        }
        List<Stage> stages = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            Stage stage = new Stage("Etapa I", true, "None", monsters);
            stages.add(stage);
        }
        List<Mission> missions = new ArrayList<>();
        Mission mission = new Mission("Eternal Harvest", false, "Mission I", stages);
        missions.add(mission);

        List<Game> games = new ArrayList<>();
        try {
            Game dofusTouch = new GameServiceImpl().buildNewGame(GameType.DOFUS_TOUCH);
            dofusTouch.setMissions(missions);
            games.add(dofusTouch);
        } catch (InvalidGameTypeException ex) {
            System.out.println("Unexpected error occurred trying to build a new game: " + ex);
        }

        Users user = new Users();
        user.setUsername("Player1");
        user.setPassword("pwd1234");
        user.setGame(games);

        userDao.save(user);

    }


}
