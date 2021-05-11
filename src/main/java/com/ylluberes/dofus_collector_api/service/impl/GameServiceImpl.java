package com.ylluberes.dofus_collector_api.service.impl;

import com.ylluberes.dofus_collector_api.domain.games.Game;
import com.ylluberes.dofus_collector_api.domain.games.types.GameType;
import com.ylluberes.dofus_collector_api.exceptions.InvalidGameTypeException;
import com.ylluberes.dofus_collector_api.service.GameService;
import lombok.NonNull;

public class GameServiceImpl implements GameService {


    @Override
    public Game buildNewGame(@NonNull GameType gameType) throws InvalidGameTypeException {
        if(gameType.equals(GameType.DOFUS_TOUCH)){
            return new Game();
        }
        throw new InvalidGameTypeException("Invlaid GameType was provide [" + gameType + "]");

    }

}