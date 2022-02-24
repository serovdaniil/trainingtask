package com.qulix.serovdo.api.command;

import com.qulix.serovdo.core.command.CommandRegistry;

public interface Command {
    CommandResponse execute(CommandRequest request);

    static Command of(String name) {
        return CommandRegistry.of(name);
    }
}
