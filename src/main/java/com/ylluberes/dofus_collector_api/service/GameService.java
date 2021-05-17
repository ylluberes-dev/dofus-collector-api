package com.ylluberes.dofus_collector_api.service;

import com.ylluberes.dofus_collector_api.domain.Mission;
import com.ylluberes.dofus_collector_api.domain.Game;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.domain.types.MissionType;
import com.ylluberes.dofus_collector_api.exceptions.InvalidGameTypeException;
import lombok.NonNull;

import java.io.IOException;

public interface GameService {
    Game addNewGame (@NonNull GameType gameType) throws InvalidGameTypeException;
    Mission addNewMission(GameType gameType, MissionType missionType) throws IOException;

}
