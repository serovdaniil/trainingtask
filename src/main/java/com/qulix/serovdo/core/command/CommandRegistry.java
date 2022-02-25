package com.qulix.serovdo.core.command;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.core.command.function.employee.CreateEmployeeCommand;
import com.qulix.serovdo.core.command.function.employee.RemoveEmployeeCommand;
import com.qulix.serovdo.core.command.function.employee.UpdateEmployeeCommand;
import com.qulix.serovdo.core.command.function.project.CreateProjectCommand;
import com.qulix.serovdo.core.command.function.project.RemoveProjectCommand;
import com.qulix.serovdo.core.command.function.project.UpdateProjectCommand;
import com.qulix.serovdo.core.command.page.employee.ShowCreateEmployeePageCommand;
import com.qulix.serovdo.core.command.page.employee.ShowEmployeePageCommand;
import com.qulix.serovdo.core.command.page.employee.ShowUpdateEmployeePageCommand;
import com.qulix.serovdo.core.command.page.project.ShowCreateProjectPageCommand;
import com.qulix.serovdo.core.command.page.project.ShowProjectPageCommand;
import com.qulix.serovdo.core.command.page.project.ShowUpdateProjectPageCommand;

public enum CommandRegistry {
    PROJECT_PAGE(ShowProjectPageCommand.getInstance(), "project_page"),
    REMOVE_PROJECT(RemoveProjectCommand.getInstance(), "remove_project"),
    UPDATE_PROJECT(UpdateProjectCommand.getInstance(), "update_project"),
    SHOW_UPDATE_PROJECT_PAGE(ShowUpdateProjectPageCommand.getInstance(), "show_update_project_page"),
    CREATE_PROJECT(CreateProjectCommand.getInstance(), "create_project"),
    SHOW_CREATE_PROJECT_PAGE(ShowCreateProjectPageCommand.getInstance(), "show_create_project_page"),
    EMPLOYEE_PAGE(ShowEmployeePageCommand.getInstance(), "employee_page"),
    REMOVE_EMPLOYEE(RemoveEmployeeCommand.getInstance(), "remove_employee"),
    SHOW_CREATE_EMPLOYEE_PAGE(ShowCreateEmployeePageCommand.getInstance(), "show_create_employee_page"),
    CREATE_EMPLOYEE(CreateEmployeeCommand.getInstance(), "create_employee"),
    SHOW_UPDATE_EMPLOYEE_PAGE(ShowUpdateEmployeePageCommand.getInstance(), "show_update_employee_page"),
    UPDATE_EMPLOYEE(UpdateEmployeeCommand.getInstance(), "update_employee"),
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
