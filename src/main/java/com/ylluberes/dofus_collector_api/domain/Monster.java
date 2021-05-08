package com.ylluberes.dofus_collector_api.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ylluberes.dofus_collector_api.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;


/***
 * @Author Yasser Lluberes
 */
@AllArgsConstructor
@Getter
@Setter
public class Monster {

    @Id
    private int id;
    private String name;
    private boolean archMonster;
    private boolean captured;
    private int timesCaptured;
    private String details;
    private String itemIconPath;

    @Override
    public String toString() {
        try {
            return Utils.toJson(this);
        }catch (JsonProcessingException ex) {
            throw new RuntimeException("An Exception occurred during parsing to json String",ex);
        }
    }

}
