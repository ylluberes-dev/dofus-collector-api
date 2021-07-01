package com.ylluberes.dofus_collector_api.service;

import com.ylluberes.dofus_collector_api.domain.Monster;
import com.ylluberes.dofus_collector_api.dto.request.InMonsterExport;
import com.ylluberes.dofus_collector_api.dto.request.InMonsterUpdate;
import com.ylluberes.dofus_collector_api.dto.responses.GenericResponse;
import com.ylluberes.dofus_collector_api.dto.responses.OutMonsterExport;

import java.util.List;

public interface MonsterService {

    GenericResponse<Monster> update(InMonsterUpdate inMonsterUpdate);
    GenericResponse<List<OutMonsterExport>> export(InMonsterExport inMonsterExport);
}
