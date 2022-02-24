package com.qulix.serovdo.core.command;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.core.command.function.project.RemoveProjectCommand;
import com.qulix.serovdo.core.command.page.project.ShowProjectPageCommand;

public enum CommandRegistry {
    MAIN_PAGE(ShowProjectPageCommand.getInstance(), "main_page"),
    REMOVE_PROJECT(RemoveProjectCommand.getInstance(), "remove_project"),
    DEFAULT(ShowProjectPageCommand.getInstance(), "");


    private final Command command;
    private final String path;

    CommandRegistry(Command command, String path) {
        this.command = command;
        this.path = path;
    }

    public Command getCommand() {
        return command;
    }


    public static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
