package com.ylluberes.dofus_collector_api.controllers;

import com.ylluberes.dofus_collector_api.dto.request.InMonsterExport;
import com.ylluberes.dofus_collector_api.dto.request.InMonsterUpdate;
import com.ylluberes.dofus_collector_api.dto.responses.GenericResponse;
import com.ylluberes.dofus_collector_api.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dofus-collector/api/v1/monster")
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @PatchMapping("/update")
    public ResponseEntity<GenericResponse> update(@RequestBody InMonsterUpdate inMonsterUpdate) {
        GenericResponse response = monsterService.update(inMonsterUpdate);
        return new ResponseEntity<>(response, response.getServerStatus());
    }

    @GetMapping("/export")
    public ResponseEntity<GenericResponse> export(@Valid @RequestBody InMonsterExport inMonsterExport) {
        GenericResponse response = monsterService.export(inMonsterExport);
        return new ResponseEntity<>(response, response.getServerStatus());
    }

}
