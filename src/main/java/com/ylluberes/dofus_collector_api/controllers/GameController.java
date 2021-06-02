package com.ylluberes.dofus_collector_api.controllers;

import com.ylluberes.dofus_collector_api.domain.*;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.domain.types.Action;
import com.ylluberes.dofus_collector_api.domain.types.MissionType;
import com.ylluberes.dofus_collector_api.dto.responses.Response;
import com.ylluberes.dofus_collector_api.service.GameService;
import com.ylluberes.dofus_collector_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/dofus-collector/api/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @GetMapping("/showGames")
    public ResponseEntity<Response<List<Game>>> showGames() {
        List<Game> gameList = new ArrayList<>();
        for(GameType gameType : GameType.values()){
            Game game = new Game();
            game.setGameType(gameType);
            game.setVersion("2.0");
            game.setName(gameType.name());
            gameList.add(game);
        }
        Response<List<Game>> response = new Response<>();
        response.setMessage("Available game list");
        response.setSuccess(true);
        response.setServerStatus(HttpStatus.OK);
        response.setData(gameList);
        return new ResponseEntity<>(response,response.getServerStatus());
    }

    @GetMapping("/showMissions")
    public ResponseEntity<MissionType[]> showMissions() {
        return new ResponseEntity<>(MissionType.values(), HttpStatus.OK);

    }

    @PatchMapping("/addNewMission/{userId}/{gameType}/{missionType}")
    public ResponseEntity<Response<MissionType>> addNewMission(@PathVariable String userId,
                                                               @PathVariable GameType gameType,
                                                               @PathVariable MissionType missionType) {
        Users user;
        Response<MissionType> response = new Response<>();
        try {
            user = userService.findById(userId);
            if (user != null) {
                List<Game> gameList = user.getGame();
                for (Game game : gameList) {
                    if (game.getGameType() == gameType) {
                        game.getMissions().add(gameService.addNewMission(gameType, missionType));
                        userService.saveOrUpdate(user);
                        response.setData(missionType);
                        response.setMessage("Mission added successfully");
                        response.setSuccess(true);
                        response.setServerStatus(HttpStatus.OK);
                        break;
                    }
                }
            } else {
                response.setData(missionType);
                response.setMessage("The user does not exists");
                response.setSuccess(false);
                response.setData(missionType);
                response.setServerStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            response.setMessage("Unexpected error");
            response.setSuccess(false);
            response.setData(missionType);
            response.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMessage("The user does not have the game [" + gameType + "]");
        response.setSuccess(false);
        response.setData(missionType);
        response.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(response, response.getServerStatus());
    }

    @PatchMapping("/addNewGame/{userId}/{gameType}")
    public ResponseEntity<Response<Game>> addNewGame(@PathVariable String userId,
                                                     @PathVariable GameType gameType) {
        Users user = null;
        Response<Game> response = new Response<>();
        try {
            user = userService.findById(userId);
            if (user != null) {
                List<Game> gameList = user.getGame();
                if (user.getGame() != null && user.getGame().size() > 0) {
                    for (Game game : gameList) {
                        if (gameType == game.getGameType()) {
                            response.setMessage("User already have the game [" + gameType.name() + "]");
                            response.setSuccess(false);
                            response.setData(game);
                            response.setServerStatus(HttpStatus.CONFLICT);
                            break;
                        }
                    }
                }
                Game newGame = gameService.addNewGame(gameType);
                user.getGame().add(newGame);
                userService.saveOrUpdate(user);

                response.setMessage("Game successfully added ");
                response.setSuccess(true);
                response.setData(newGame);
                response.setServerStatus(HttpStatus.OK);
            } else {
                response.setMessage("The user does not exists");
                response.setSuccess(false);
                response.setData(null);
                response.setServerStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            response.setMessage("Unexpected error");
            response.setSuccess(false);
            response.setData(null);
            response.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, response.getServerStatus());
    }

    @PatchMapping("/updateMonster/{userId}/{monsterId}/{action}")
    public ResponseEntity<Response<Monster>> markMonsterAsCaptured(@PathVariable String userId,
                                                                   @PathVariable String monsterId,
                                                                   @PathVariable Action action) {
        Users user;
        Response<Monster> response = new Response<>();
        try {

            user = userService.findById(userId);
            if (user != null) {
                List<Game> gameList = user.getGame();
                if (gameList != null && gameList.size() > 0) {
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
                                                            if (status)
                                                                monster.setTimesCaptured(monster.getTimesCaptured() + 1);
                                                            break;
                                                        case REMOVE:
                                                            if (status)
                                                                if (monster.getTimesCaptured() < 1)
                                                                    monster.setTimesCaptured(monster.getTimesCaptured() - 1);
                                                            break;

                                                    }
                                                    userService.saveOrUpdate(user);
                                                    response.setMessage("Monster updated successfully");
                                                    response.setSuccess(true);
                                                    response.setData(monster);
                                                    response.setServerStatus(HttpStatus.OK);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                response.setMessage("The user does not exists");
                response.setSuccess(false);
                response.setData(null);
                response.setServerStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            response.setMessage("Unexpected error");
            response.setSuccess(false);
            response.setData(null);
            response.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, response.getServerStatus());
    }

    @GetMapping("/exportDuplicates/{userId}")
    public ResponseEntity<Response<List<Monster>>> exportDuplicate(@PathVariable String userId) {
        Users user;
        Response<List<Monster>> response = new Response<>();
        try {
            user = userService.findById(userId);
            if (user != null) {
                List<Game> gameList = user.getGame();
                if (gameList != null && gameList.size() > 0) {
                    for (Game game : gameList) {
                        if (game.getGameType() == GameType.DOFUS_TOUCH) {
                            for (Mission mission : game.getMissions()) {
                                if (mission.getMissionType() == MissionType.ETERNAL_HARVEST) {
                                    for (Stage stage : mission.getStages()) {
                                        List<Monster> duplicatedMonster = stage
                                                .getSteps()
                                                .stream()
                                                .filter(x -> x.getTimesCaptured() > 1)
                                                .collect(Collectors.toList());
                                        response.setMessage("Monster updated successfully");
                                        response.setSuccess(true);
                                        response.setData(duplicatedMonster);
                                        response.setServerStatus(HttpStatus.OK);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                response.setMessage("The user does not exists");
                response.setSuccess(false);
                response.setData(null);
                response.setServerStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMessage("Unexpected error");
            response.setSuccess(false);
            response.setData(null);
            response.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, response.getServerStatus());
    }
}
