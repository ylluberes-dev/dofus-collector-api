package com.ylluberes.dofus.collector.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class OutMonsterExport implements Serializable {

    private final String name;
    private final int timesCaptured;
    private final boolean archMonster;
}
