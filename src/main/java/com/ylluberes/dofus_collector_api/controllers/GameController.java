package com.ylluberes.dofus_collector_api.controllers;

import com.ylluberes.dofus_collector_api.domain.*;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.domain.types.Action;
import com.ylluberes.dofus_collector_api.domain.types.MissionType;
import com.ylluberes.dofus_collector_api.service.GameService;
import com.ylluberes.dofus_collector_api.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dofus-collector/api/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @GetMapping("/showGames")
    public ResponseEntity<GameType[]> showGames() {
        return new ResponseEntity<>(GameType.values(), HttpStatus.OK);
    }

    @GetMapping("/showMissions")
    public ResponseEntity<MissionType[]> showMissions() {
        return new ResponseEntity<>(MissionType.values(), HttpStatus.OK);

    }

    @PutMapping("/addNewMission/{userId}/{gameType}/{missionType}")
    public ResponseEntity<String> addNewMission(@PathVariable String userId,
                                                @PathVariable GameType gameType,
                                                @PathVariable MissionType missionType) {
        Users user;
        try {
            user = userService.findById(userId);
            if (user != null) {
                List<Game> gameList = user.getGame();
                int index = 0;
                for (Game game : gameList) {
                    if (game.getGameType() == gameType) {
                        game.getMissions().add(gameService.addNewMission(gameType, missionType));
                        gameList.set(index, game);
                        userService.saveOrUpdate(user);
                        return new ResponseEntity<>("Mission added successfully", HttpStatus.OK);
                    }
                    index++;
                }
            } else {
                return new ResponseEntity<>("User dos not exists", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Unexpected error occurred adding new Mission ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Game not Found ", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/addNewGame/{userId}/{gameType}")
    public ResponseEntity<String> addNewGame(@PathVariable String userId,
                                             @PathVariable GameType gameType) {
        Users user = null;
        try {
            user = userService.findById(userId);
            if (user != null) {
                List<Game> gameList = user.getGame();
                if (user.getGame() != null && user.getGame().size() > 0) {
                    for (Game game : gameList) {
                        if (gameType == game.getGameType()) {
                            return new ResponseEntity<>
                                    ("User already have the game [" + gameType.name() + "] ",
                                            HttpStatus.CONFLICT);
                        }
                    }
                }
                Game newGame = gameService.addNewGame(gameType);
                user.getGame().add(newGame);
                userService.saveOrUpdate(user);
            } else {
                return new ResponseEntity<>("User dos not exists", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Unexpected error occurred adding new Mission ", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Game successfully added ", HttpStatus.OK);
    }

    @PutMapping("/updateMonster/{userId}/{monsterId}/{action}")
    public ResponseEntity<String> markMonsterAsCaptured(@PathVariable String userId,
                                                        @PathVariable String monsterId,
                                                        @PathVariable Action action) {
        Users user;
        try {
            user = userService.findById(userId);
            if (user != null) {
                for (Game game : user.getGame()) {
                    if (game.getGameType() == GameType.DOFUS_TOUCH) {
                        for (Mission mission : game.getMissions()) {
                            if (mission.getMissionType() == MissionType.ETERNAL_HARVEST) {
                                for (Stage stage : mission.getStages()) {
                                    for (Monster monster : stage.getSteps()) {
                                        if (monster.get_id().equals(monsterId)) {
                                            boolean status = monster.isCaptured();
                                            switch (action) {
                                                case CHECK:
                                                    if (!status)
                                                        monster.setCaptured(true);
                                                    break;
                                                case UNCHECK:
                                                    if (status)
                                                        monster.setCaptured(false);
                                                    break;
                                                case ADD:
                                                    monster.setTimesCaptured(monster.getTimesCaptured() + 1);
                                                    break;
                                                case REMOVE:
                                                    monster.setTimesCaptured(monster.getTimesCaptured() - 1);
                                                    break;

                                            }
                                            userService.saveOrUpdate(user);
                                            return new ResponseEntity<>("Monster updated successfully", HttpStatus.OK);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                return new ResponseEntity<>("User dos not exists", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Unexpected error occurred adding new Mission ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
