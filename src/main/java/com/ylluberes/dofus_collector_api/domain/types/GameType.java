package com.ylluberes.dofus_collector_api.domain.types;

public enum GameType {

    DOFUS_TOUCH("dofus-touch/"),
    DOFUS_PC("dofus-pc/"),
    DOFUS_OLD("dofus-old/");

    GameType(String baseDirectory){
        this.baseDirectory = baseDirectory;
    }
    private final String baseDirectory;

    public String getBaseDirectory () {
        return baseDirectory;
    }

}
