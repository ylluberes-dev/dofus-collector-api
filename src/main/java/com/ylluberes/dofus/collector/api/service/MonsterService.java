package com.ylluberes.dofus.collector.api.service;

import com.ylluberes.dofus.collector.api.dto.request.InMonsterExport;
import com.ylluberes.dofus.collector.api.dto.request.InMonsterUpdate;
import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import com.ylluberes.dofus.collector.api.dto.responses.OutMonsterExport;
import com.ylluberes.dofus.collector.api.domain.Monster;

import java.util.List;

public interface MonsterService {

    GenericResponse<Monster> update(InMonsterUpdate inMonsterUpdate);
    GenericResponse<List<OutMonsterExport>> export(InMonsterExport inMonsterExport);
}
