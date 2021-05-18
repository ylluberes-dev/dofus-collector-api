package com.ylluberes.dofus_collector_api.domain.types;

public enum MissionType {

    ETERNAL_HARVEST("cosecha-eterna.json");

    MissionType(String origin){
        this.origin = origin;
    }
    private final String origin;

    public String getOrigin (){
        return origin;
    }
}
