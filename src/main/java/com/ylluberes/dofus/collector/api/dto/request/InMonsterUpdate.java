package com.ylluberes.dofus.collector.api.dto.request;

import com.ylluberes.dofus.collector.api.domain.types.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class InMonsterUpdate implements Serializable {

    @NotNull(message = "userId param should not be null")
    private final String userId;

    @NotNull(message = "monsterId param should not be null")
    private final String monsterId;

    @NotNull(message = "action param should not be null")
    private final Action action;

}
