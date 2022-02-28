package com.qulix.serovdo.core.command.page.task;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.service.EmployeeServiceImpl;
import com.qulix.serovdo.core.service.ProjectServiceImpl;

import java.util.List;
import java.util.logging.Logger;

public class ShowCreateTaskPageCommand implements Command {
    private static final String PROJECT_ATTRIBUTE_NAME = "projects";
    private static final String EMPLOYEE_ATTRIBUTE_NAME = "employees";
    private static final String STATUS_ATTRIBUTE_NAME = "status";
    private static final String TASK_PAGE = "page.createTask";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final EmployeeServiceImpl serviceEmployee;
    private final ProjectServiceImpl serviceProject;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCreateTaskPageCommand() {
        this.serviceEmployee = EmployeeServiceImpl.getInstance();
        this.serviceProject = ProjectServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final List<Project> projects = serviceProject.findAll();
            final List<Employee> employees = serviceEmployee.findAll();
            final String status = "new";
            request.addAttributeToJsp(PROJECT_ATTRIBUTE_NAME, projects);
            request.addAttributeToJsp(EMPLOYEE_ATTRIBUTE_NAME, employees);
            request.addAttributeToJsp(STATUS_ATTRIBUTE_NAME, status);
        } catch (ServiceException e) {
            logger.warning("Show id project:" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(TASK_PAGE));
    }

    public static ShowCreateTaskPageCommand getInstance() {
        return ShowCreateTaskPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCreateTaskPageCommand INSTANCE =
                new ShowCreateTaskPageCommand();
    }
}
