package com.ylluberes.dofus_collector_api.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class Stage {


    private String name;
    private boolean complete;
    private String details;
    private List<Monster> steps;

    public Stage() {

    }

    @Override
    public String toString() {
        try {
            return Utils.toJson(this);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("An Exception occurred during parsing to json String", ex);
        }
    }

    /**
     * Constant execution cause steps size is always 20
     */
    public void updateStageDetails() {
        int captured = 0;
        for (Monster monster : steps) {
            if (monster.isCaptured()) captured++;
        }
        setDetails(captured + "/" + steps.size());

    }

}
