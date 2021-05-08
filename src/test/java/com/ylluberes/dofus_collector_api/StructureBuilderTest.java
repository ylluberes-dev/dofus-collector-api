package com.ylluberes.dofus_collector_api;


import com.ylluberes.dofus_collector_api.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class StructureBuilderTest {

	@Test
	void buildDocumentStructure () {
		List<Monster> monsters = new ArrayList<>();
		for (int i =0; i <= 2; i++) {
			Monster monster = new Monster(1,"Jabalí de las llanuras",false,false,0,"None","/Path/to/some/image");
			monsters.add(monster);
		}
		List<Stage> stages = new ArrayList<>();
		for (int i = 0; i <= 2; i++){
			Stage stage = new Stage(0,"Etapa I",true,"None", monsters);
			stages.add(stage);
		}
		List<Mission> missions = new ArrayList<>();
		Mission mission = new Mission(1,"Eternal Harvest",false,"Mission I",stages);
		missions.add(mission);

		List<Game> games = new ArrayList<>();
		Game dofus_touch = new Game(1,"Dofus-touch","2.45",missions);
		Game dofus_pc = new Game(2," Dofus-PC","2.0",missions);
		games.add(dofus_touch);
		games.add(dofus_pc);

		User user = new User(1,"ylluberes","12345",games);

		System.out.println(user);

	}

}
