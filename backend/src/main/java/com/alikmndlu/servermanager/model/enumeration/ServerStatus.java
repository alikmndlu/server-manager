package com.alikmndlu.servermanager.model.enumeration;

public enum ServerStatus {

    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String status;

    ServerStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
