package com.ylluberes.dofus.collector.api.service.impl;

import com.ylluberes.dofus.collector.api.domain.*;
import com.ylluberes.dofus.collector.api.dto.request.InMonsterExport;
import com.ylluberes.dofus.collector.api.dto.request.InMonsterUpdate;
import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import com.ylluberes.dofus.collector.api.service.UserService;
import com.ylluberes.dofus.collector.api.dto.responses.OutMonsterExport;
import com.ylluberes.dofus.collector.api.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    private UserService userService;

    @Override
    public GenericResponse<Monster> update(InMonsterUpdate inMonsterUpdate) {
        User user;
        GenericResponse response = new GenericResponse();
        boolean found = false;
        try {
            user = userService.findById(inMonsterUpdate.getUserId()).getData();
            if (user != null) {
                Game game = user.getGame();
                if (game != null) {
                    Mission mission = game.getMission();
                    for (Stage stage : mission.getStages()) {
                        for (Monster monster : stage.getSteps()) {
                            if (monster.get_id().equals(inMonsterUpdate.getMonsterId())) {
                                found = true;
                                boolean status = monster.isCaptured();
                                switch (inMonsterUpdate.getAction()) {
                                    case ADD:
                                        if (!status)
                                            monster.setCaptured(true);
                                        monster.setTimesCaptured(monster.getTimesCaptured() + 1);
                                        break;
                                    case REMOVE:
                                        if (status) {
                                            if (monster.getTimesCaptured() >= 1) {
                                                monster.setTimesCaptured(monster.getTimesCaptured() - 1);
                                                if (monster.getTimesCaptured() == 0) monster.setCaptured(false);
                                            }
                                        }
                                        break;
                                }
                                stage.updateStageDetails();
                                userService.saveOrUpdate(user);
                                response.setMessage("Monster updated successfully");
                                response.setSuccess(true);
                                response.setData(monster);
                                response.setServerStatus(HttpStatus.OK);
                                break;
                            }
                        }
                        if (found) break;
                    }
                    if (!found) {
                        response.setMessage("Cannot found the monster");
                        response.setSuccess(false);
                        response.setData(null);
                        response.setServerStatus(HttpStatus.NOT_FOUND);
                    }
                }
            } else {
                response.setMessage("The user does not exists");
                response.setSuccess(false);
                response.setData(null);
                response.setServerStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            response.setMessage("Unexpected error trying to update monster");
            response.setSuccess(false);
            response.setData(null);
            response.setServerStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public GenericResponse<List<OutMonsterExport>> export(InMonsterExport inMonsterExport) {
        GenericResponse<List<OutMonsterExport>> response = new GenericResponse<>();
        User user;
        List<OutMonsterExport> outMonsterExportList = new ArrayList<>();
        try {
            user = userService.findById(inMonsterExport.getUserId()).getData();
            if (user != null) {
                Game game = user.getGame();
                if (game != null) {
                    Mission mission = game.getMission();
                    if (mission != null) {
                        for (Stage stage : mission.getStages()) {
                            List<Monster> candidates = stage
                                    .getSteps()
                                    .stream()
                                    .filter(x -> x.getTimesCaptured() > inMonsterExport.getGreaterThan())
                                    .collect(Collectors.toList());
                            if (candidates.size() > 0) {
                                for (Monster monster : candidates) {
                                    outMonsterExportList.add(new OutMonsterExport(monster.getName(),
                                            monster.getTimesCaptured(),
                                            monster.isArchMonster()));
                                }
                            }
                        }
                        response.setMessage(outMonsterExportList.size() > 0 ? "Repeated monsters" : "No monster greater than " + inMonsterExport.getGreaterThan());
                        response.setSuccess(true);
                        response.setData(outMonsterExportList);
                        response.setServerStatus(HttpStatus.OK);
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
        return response;
    }


}
