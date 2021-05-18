package com.ylluberes.dofus_collector_api.dao;

import com.ylluberes.dofus_collector_api.domain.Mission;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.domain.types.MissionType;

import java.io.IOException;

public interface GameContentDao {
    Mission map(GameType gameType, MissionType mission) throws IOException;
}
