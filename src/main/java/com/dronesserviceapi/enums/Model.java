package com.dronesserviceapi.enums;

public enum Model implements GlobalEnumInterface {

    LIGHTWEIGHT("LIGHTWEIGHT"),
    MIDDLEWEIGHT("MIDDLEWEIGHT"),
    CRUISERWEIGHT("CRUISERWEIGHT"),
    HEAVYWEIGHT("HEAVYWEIGHT");
    ;

    private String value;

    private Model(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value();
    }

}
