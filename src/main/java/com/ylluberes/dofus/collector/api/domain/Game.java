package com.ylluberes.dofus.collector.api.domain;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ylluberes.dofus.collector.api.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/***
 * @Author Yasser Lluberes
 */
@AllArgsConstructor
@Getter
@Setter
public  class Game {

    private String name;
    private String version;
    private Mission mission;

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