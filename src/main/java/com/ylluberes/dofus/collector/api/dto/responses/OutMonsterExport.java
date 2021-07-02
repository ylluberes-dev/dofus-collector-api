package com.ylluberes.dofus.collector.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OutMonsterExport {

    private final String name;
    private final int timesCaptured;
    private final boolean archMonster;
}
