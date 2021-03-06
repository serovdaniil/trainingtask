package com.qulix.serovdo.core.command;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.core.command.function.employee.CreateEmployeeCommand;
import com.qulix.serovdo.core.command.function.employee.RemoveEmployeeCommand;
import com.qulix.serovdo.core.command.function.employee.UpdateEmployeeCommand;
import com.qulix.serovdo.core.command.function.project.CreateProjectCommand;
import com.qulix.serovdo.core.command.function.project.RemoveProjectCommand;
import com.qulix.serovdo.core.command.function.project.UpdateProjectCommand;
import com.qulix.serovdo.core.command.function.task.CreateTaskCommand;
import com.qulix.serovdo.core.command.function.task.RemoveTaskCommand;
import com.qulix.serovdo.core.command.function.task.UpdateTaskCommand;
import com.qulix.serovdo.core.command.page.employee.ShowCreateEmployeePageCommand;
import com.qulix.serovdo.core.command.page.employee.ShowEmployeePageCommand;
import com.qulix.serovdo.core.command.page.employee.ShowUpdateEmployeePageCommand;
import com.qulix.serovdo.core.command.page.project.ShowCreateProjectPageCommand;
import com.qulix.serovdo.core.command.page.project.ShowProjectPageCommand;
import com.qulix.serovdo.core.command.page.project.ShowUpdateProjectPageCommand;
import com.qulix.serovdo.core.command.page.task.ShowCreateTaskInProjectPageCommand;
import com.qulix.serovdo.core.command.page.task.ShowCreateTaskPageCommand;
import com.qulix.serovdo.core.command.page.task.ShowTaskPageCommand;
import com.qulix.serovdo.core.command.page.task.ShowUpdateTaskPageCommand;

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
    TASK_PAGE(ShowTaskPageCommand.getInstance(), "task_page"),
    REMOVE_TASK(RemoveTaskCommand.getInstance(), "remove_task"),
    SHOW_CREATE_TASK_PAGE(ShowCreateTaskPageCommand.getInstance(), "show_create_task_page"),
    SHOW_CREATE_TASK_IN_PROJECT_PAGE(ShowCreateTaskInProjectPageCommand.getInstance(), "show_create_task_in_project_page"),
    CREATE_TASK(CreateTaskCommand.getInstance(), "create_task"),
    SHOW_UPDATE_TASK_PAGE(ShowUpdateTaskPageCommand.getInstance(), "show_update_task_page"),
    UPDATE_TASK(UpdateTaskCommand.getInstance(), "update_task"),
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
