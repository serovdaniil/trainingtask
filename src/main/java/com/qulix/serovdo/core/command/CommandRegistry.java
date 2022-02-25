package com.qulix.serovdo.core.command;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.core.command.function.project.CreateProjectCommand;
import com.qulix.serovdo.core.command.function.project.RemoveProjectCommand;
import com.qulix.serovdo.core.command.function.project.UpdateProjectCommand;
import com.qulix.serovdo.core.command.page.project.ShowCreateProjectPageCommand;
import com.qulix.serovdo.core.command.page.project.ShowProjectPageCommand;
import com.qulix.serovdo.core.command.page.project.ShowUpdateProjectPageCommand;

public enum CommandRegistry {
    PROJECT_PAGE(ShowProjectPageCommand.getInstance(), "main_page"),
    REMOVE_PROJECT(RemoveProjectCommand.getInstance(), "remove_project"),
    UPDATE_PROJECT(UpdateProjectCommand.getInstance(), "update_project"),
    SHOW_UPDATE_PROJECT_PAGE(ShowUpdateProjectPageCommand.getInstance(), "show_update_project_page"),
    CREATE_PROJECT(CreateProjectCommand.getInstance(), "create_project"),
    SHOW_CREATE_PROJECT_PAGE(ShowCreateProjectPageCommand.getInstance(), "show_create_project_page"),
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
