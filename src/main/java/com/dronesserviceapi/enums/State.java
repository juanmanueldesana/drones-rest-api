package com.dronesserviceapi.enums;

public enum State implements GlobalEnumInterface {

    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING"),
    ;

    private String value;

    private State(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value();
    }

}
