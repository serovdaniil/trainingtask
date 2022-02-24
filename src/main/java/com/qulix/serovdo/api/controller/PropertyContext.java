package com.qulix.serovdo.api.controller;

import com.qulix.serovdo.core.controller.SimplePropertyContext;

public interface PropertyContext {
    String get(String name);

    static PropertyContext instance() {
        return SimplePropertyContext.getInstance();
    }
}
