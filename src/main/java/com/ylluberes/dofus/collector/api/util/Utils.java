package com.ylluberes.dofus.collector.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public final class Utils {

    public static final String toJson(Object obj) throws JsonProcessingException {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);

        } catch (JsonProcessingException jsonEx) {
            throw (jsonEx);
        }
    }

}
