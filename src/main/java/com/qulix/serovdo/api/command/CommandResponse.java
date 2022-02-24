package com.qulix.serovdo.api.command;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();
}
