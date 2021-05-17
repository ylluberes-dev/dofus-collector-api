package com.ylluberes.dofus_collector_api.domain;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/***
 * @Author Yasser Lluberes
 */
@AllArgsConstructor
@Getter
@Setter
public  class Game {

    private String name;
    private String version;
    private GameType gameType;
    private List<Mission> missions;

    public Game(){

    }

    @Override
    public String toString() {
        try {
            return Utils.toJson(this);
        }catch (JsonProcessingException ex) {
            throw new RuntimeException("An Exception occurred during parsing to json String",ex);
        }
    }




}
