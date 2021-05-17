package com.ylluberes.dofus_collector_api.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ylluberes.dofus_collector_api.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


/***
 * @Author Yasser Lluberes
 */
@AllArgsConstructor
@Getter
@Setter
public class Monster {

    private String _id = UUID.randomUUID().toString();
    private String name;
    private boolean archMonster;
    private boolean captured;
    private int timesCaptured;
    private boolean bossMonster;
    private String details;
    private String itemIconPath;
    private String rangeLevel;
    private String zone;
    private String subZone;
    private int stage;
   /* private String monsterType;*/

    public Monster () {

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
