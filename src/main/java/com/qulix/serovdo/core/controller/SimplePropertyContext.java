package com.qulix.serovdo.core.controller;

import com.qulix.serovdo.api.controller.PropertyContext;

public class SimplePropertyContext implements PropertyContext {

    private SimplePropertyContext() {
    }

    @Override
    public String get(String name) {
        if (name.startsWith("page.")) {
            return PagePaths.of(name.substring(5)).getPath();
        }
        return null;
    }

    public static SimplePropertyContext getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final SimplePropertyContext INSTANCE = new SimplePropertyContext();
    }
}
