package com.qulix.serovdo.core.controller;

import com.qulix.serovdo.api.command.CommandRequest;

import javax.servlet.http.HttpServletRequest;


public class WrappingCommandRequest implements CommandRequest {
    private final HttpServletRequest request;

    public WrappingCommandRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addAttributeToJsp(String name, Object attribute) {
        request.setAttribute(name, attribute);
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }
}
