package com.ylluberes.dofus_collector_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class InMonsterExport implements Serializable {

    private final int greaterThan;
    private final String userId;

}
