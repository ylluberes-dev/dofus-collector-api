package com.ylluberes.dofus_collector_api.dto.request;

import com.ylluberes.dofus_collector_api.domain.types.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class InMonsterUpdate implements Serializable {

    private final String userId;
    private final String monsterId;
    private final Action action;

}
