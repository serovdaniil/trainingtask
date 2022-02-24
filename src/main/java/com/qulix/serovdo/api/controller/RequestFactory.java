package com.qulix.serovdo.api.controller;

import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.core.controller.SimpleRequestFactory;

import javax.servlet.http.HttpServletRequest;

public interface RequestFactory {
    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponse(String path);

    static RequestFactory getInstance() {
        return SimpleRequestFactory.INSTANCE;
    }
}
