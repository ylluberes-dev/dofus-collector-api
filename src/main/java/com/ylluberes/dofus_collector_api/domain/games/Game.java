package com.ylluberes.dofus_collector_api.domain.games;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ylluberes.dofus_collector_api.domain.Mission;
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
public abstract class Game {

    private String name;
    private String version;
    private List<Mission> missions;

    @Override
    public String toString() {
        try {
            return Utils.toJson(this);
        }catch (JsonProcessingException ex) {
            throw new RuntimeException("An Exception occurred during parsing to json String",ex);
        }
    }




}
