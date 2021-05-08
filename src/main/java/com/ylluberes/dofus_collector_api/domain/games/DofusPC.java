package com.ylluberes.dofus_collector_api.domain.games;


import com.ylluberes.dofus_collector_api.domain.Mission;
import lombok.Builder;
import lombok.ToString;
import java.util.List;

@ToString(callSuper = true)
public class DofusPC extends Game{

    @Builder
    public DofusPC (int id, String name, String version, List<Mission> missions){
        super(id,name,version,missions);
    }
}