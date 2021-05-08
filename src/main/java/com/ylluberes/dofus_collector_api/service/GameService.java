package com.ylluberes.dofus_collector_api.service;

import com.ylluberes.dofus_collector_api.domain.games.Game;
import com.ylluberes.dofus_collector_api.domain.games.types.GameType;
import com.ylluberes.dofus_collector_api.exceptions.InvalidGameTypeException;
import lombok.NonNull;

public interface GameService {
    Game buildNewGame(@NonNull GameType gameType) throws InvalidGameTypeException;
}
