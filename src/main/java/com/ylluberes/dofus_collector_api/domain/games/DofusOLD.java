package com.ylluberes.dofus_collector_api.domain.games;

import com.ylluberes.dofus_collector_api.domain.Mission;
import lombok.*;

import java.util.List;


@ToString(callSuper = true)
public class DofusOLD extends Game{

    @Builder
    public DofusOLD (String name, String version, List<Mission> missions){
        super(name,version,missions);
    }

}
