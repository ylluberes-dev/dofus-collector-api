package com.ylluberes.dofus_collector_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class InMonsterExport implements Serializable {

    @NotNull(message = "greaterThan param should not be null")
    @Min(value = 1,message = "greaterThan param should be positive")
    private final int greaterThan;

    @NotNull(message = "userId param should not be null")
    private final String userId;

}
