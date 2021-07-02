package com.ylluberes.dofus.collector.api.loaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ylluberes.dofus.collector.api.domain.Mission;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class MissionLoader {

    private static MissionLoader instance = null;
    private  Mission mission;

    /**
     *
     * @return
     * @throws JsonProcessingException
     */

    static {
        try {
            ClassLoader loader = MissionLoader.class.getClassLoader();
            InputStream missionStream = loader.getResourceAsStream("cosecha-eterna.json");
            if (missionStream == null) {
                throw new ExceptionInInitializerError("Error while loading mission");
            } else {
                String jsonResult = new BufferedReader(new InputStreamReader(missionStream))
                        .lines()
                        .collect(Collectors.joining("\n"));
                ObjectMapper mapper = new ObjectMapper();
                Mission eternalHarvest = mapper.readValue(jsonResult, Mission.class);
                instance = new MissionLoader(eternalHarvest);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static MissionLoader getInstance() {
        return instance;
    }

    private MissionLoader(Mission mission) {
        this.mission = mission;
    }

    public Mission getMission() {
        return mission;
    }
}
