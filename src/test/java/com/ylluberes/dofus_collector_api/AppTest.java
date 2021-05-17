package com.ylluberes.dofus_collector_api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ylluberes.dofus_collector_api.dao.GameContentDao;
import com.ylluberes.dofus_collector_api.dao.UserDao;
import com.ylluberes.dofus_collector_api.domain.Mission;
import com.ylluberes.dofus_collector_api.domain.types.GameType;
import com.ylluberes.dofus_collector_api.domain.types.MissionType;
import com.ylluberes.dofus_collector_api.util.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@SpringBootTest
class AppTest {

    @Autowired
    private UserDao userDao;


    @Autowired
    private GameContentDao gameContentDao;

    @Test
    public void testFindAllUsers() {
        this.userDao.findAll().stream().forEach(x -> {
            System.out.println(x);
        });
    }

    @Test
    public void testFindUserById() {
        System.out.println(this.userDao.findById("609a4cf01e8c0a4dfffc055e").orElse(null));
    }

    @Test
    public void testLoadMonster() {
       /* MonsterMapper monsterMapper = new MonsterMapper();
        try {
            List<String[]> csvLines = gameContentDao.retrieveCsvLinesOf(GameType.DOFUS_TOUCH, Origin.MONSTER);
            List<Monster> monsters = monsterMapper.map(csvLines);
            List<Monster> archMonster = monsters.stream().filter(x -> x.isArchMonster()).collect(Collectors.toList());
            System.out.println("***** Output *****");
            System.out.println("Listing all dofus-touch archmonsters...");
            archMonster.forEach(x -> {
                System.out.println("Archmonster: " + x.getName());
            });
            System.out.println("***** Output *****");
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }


    @Test
    public void testLoadMissions() {
       /* MissionMapper missionMapper = new MissionMapper();
        try {
            List<String[]> csvLines = gameContentDao.retrieveCsvLinesOf(GameType.DOFUS_TOUCH, Origin.MISSION);
            List<Mission> missionList = missionMapper.map(csvLines);
            System.out.println("***** Output *****");
            missionList.forEach(x -> {
                System.out.println("Mission: " + x.getName());
            });
            System.out.println("***** Output *****");
        } catch (Exception e) {
            e.printStackTrace();
        }

        */
    }


    @Test
    public void testEternalHarvest() {
      /*  List<Mission> missions = new ArrayList<>();

        //Creating mission
        Mission eternalHarvest = new Mission();
        eternalHarvest.setName("La Cosecha Eterna");
        eternalHarvest.setDetails("Captura todos los monstros del mundo de los 12");
        eternalHarvest.setComplete(false);

        //Collecting all monsters of eternalHarvest
        List<Monster> monsters = null;
        try {
            monsters = new MonsterMapper().map(gameContentDao.retrieveCsvLinesOf(GameType.DOFUS_TOUCH, Origin.MONSTER));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Stage> stages = new ArrayList<>();
        //Creating Stages<T>
        for (int stageNo = 1; stageNo <= 35; stageNo++) {
            Stage currentStage = new Stage();
            currentStage.setName("Etapa: " + stageNo);
            currentStage.setDetails("0% completado");
            currentStage.setComplete(false);

            List<Monster> currentMonsters = new ArrayList<>();
            for (Monster monster : monsters) {
                if (monster.getStage() == stageNo) {
                    currentMonsters.add(monster);
                }
            }
            currentStage.setSteps(currentMonsters);
            stages.add(currentStage);
        }
        eternalHarvest.setStages(stages);
        missions.add(eternalHarvest);
        System.out.println(missions);

        System.out.println("Stop");*/


    }

    @Test
    public void testFromJson() {
        final String path = "C:/Users/ylluberes/IdeaProjects/dofus-collector-api/src/test/resources/dofus-touch/missions/cosecha-eterna.json";
        final ObjectMapper mapper = new ObjectMapper();
        String eternalHarvestOrigin = null;
        try {
            eternalHarvestOrigin = Utils.readFile(path, StandardCharsets.UTF_8);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        Mission eternalHarvest = null;
        try {
            eternalHarvest = mapper.readValue(eternalHarvestOrigin, Mission.class);
        }catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

     /*   Gson gson = new Gson();
        Mission eternalHarvestGson = gson.fromJson(eternalHarvestOrigin,Mission.class);
        System.out.println("Hi there");*/

        System.out.println("");

    }

    @Test
    public void testLoadMission (){
        Mission eternalHarvest = null;
        try{
           eternalHarvest = gameContentDao.map(GameType.DOFUS_TOUCH, MissionType.ETERNAL_HARVEST);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println(eternalHarvest);
    }



}
