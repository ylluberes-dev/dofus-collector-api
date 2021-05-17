package com.ylluberes.dofus_collector_api.service.impl;

import com.ylluberes.dofus_collector_api.dao.GameContentDao;
import com.ylluberes.dofus_collector_api.domain.Mission;
import com.ylluberes.dofus_collector_api.domain.Game;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.domain.types.MissionType;
import com.ylluberes.dofus_collector_api.service.GameService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameContentDao gameContentDao;

    @Override
    public Game addNewGame(@NonNull GameType gameType) {
        Game game = new Game();
        List<Mission> missions = new ArrayList<>();
        if (gameType.equals(GameType.DOFUS_TOUCH)) {

            game.setName("Dofus Touch");
            game.setVersion("2.57");
            game.setGameType(gameType);
            game.setMissions(missions);
            return game;

        } else if (gameType.equals(GameType.DOFUS_PC)) {

            game.setName("Dofus PC");
            game.setVersion("2.64");
            game.setGameType(gameType);
            game.setMissions(missions);
            return game;

        } else {

            game.setName("Dofus Eratz");
            game.setVersion("1.29");
            game.setGameType(gameType);
            game.setMissions(missions);
            return game;
        }
    }

    @Override
    public Mission addNewMission(GameType gameType, MissionType missionType) throws IOException {
        Mission mission = null;
        try {
            mission = gameContentDao.map(gameType, missionType);
        } catch (IOException ex) {
            throw (ex);
        }
        return mission;
    }


}