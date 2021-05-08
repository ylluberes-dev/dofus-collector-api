package com.ylluberes.dofus_collector_api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    public static String toJson (Object obj) throws JsonProcessingException {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }catch (JsonProcessingException jsonEx){
            throw(jsonEx);
        }
    }
}
