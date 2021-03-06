package com.ylluberes.dofus_collector_api.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ylluberes.dofus_collector_api.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/***
 * @Author Yasser Lluberes
 */

@Document(collection = "Users")
@AllArgsConstructor
@Getter
@Setter
public class Users implements Serializable {

    @Id
    private String _id;
    private String username;
    //private String email;
    private String password;
    private List<Game> game;

    public Users () {

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
