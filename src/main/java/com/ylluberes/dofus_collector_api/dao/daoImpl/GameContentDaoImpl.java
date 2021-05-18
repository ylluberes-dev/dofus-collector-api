package com.ylluberes.dofus_collector_api.dao.daoImpl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ylluberes.dofus_collector_api.dao.GameContentDao;
import com.ylluberes.dofus_collector_api.domain.Mission;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.domain.types.MissionType;
import com.ylluberes.dofus_collector_api.util.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Repository
public class GameContentDaoImpl implements GameContentDao {

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public Mission map(GameType gameType, MissionType mission) throws IOException {
        Mission value;
        try {
            String jsonOrigin = Utils.readFile(baseUrl + gameType.getBaseDirectory()+ mission.getOrigin(), StandardCharsets.UTF_8);
            ObjectMapper missionMapper = new ObjectMapper();
            value = missionMapper.readValue(jsonOrigin, Mission.class);
            value.setMissionType(mission);

        } catch (IOException ex) {
            if (ex instanceof JsonMappingException) {
                throw new JsonMappingException("An exception occurred trying to convert jsonString to object. ", ex);
            } else {
                throw new IOException("An error occurred during reading json origin file. ", ex);
            }
        }
        return value;
    }
}
