package com.ylluberes.dofus_collector_api.domain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ylluberes.dofus_collector_api.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;


/***
 * @Author Yasser Lluberes
 */
@AllArgsConstructor
@Getter
@Setter
public class Mission {

    @Id
    private int id;
    private String name;
    private boolean complete;
    private String details;
    private List<Stage> stages;

    @Override
    public String toString() {
        try {
            return Utils.toJson(this);
        }catch (JsonProcessingException ex) {
            throw new RuntimeException("An Exception occurred during parsing to json String",ex);
        }
    }

}
