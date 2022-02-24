package com.qulix.serovdo.api.command;

import java.util.Optional;

public interface CommandRequest {

    void addAttributeToJsp(String name, Object attribute);

    String getParameter(String name);

    boolean sessionExists();

    boolean addToSession(String name, Object value);

    Long retrieveFromSessionLong(String name);

    void clearSession();

    void createSession();
}
