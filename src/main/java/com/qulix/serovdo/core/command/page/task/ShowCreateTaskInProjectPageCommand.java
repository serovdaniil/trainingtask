package com.qulix.serovdo.core.command.page.task;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.service.EmployeeServiceImpl;
import com.qulix.serovdo.core.service.ProjectServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ShowCreateTaskInProjectPageCommand implements Command {
    private static final String PROJECT_ATTRIBUTE_NAME = "aloneProject";
    private static final String EMPLOYEE_ATTRIBUTE_NAME = "employees";
    private static final String STATUS_ATTRIBUTE_NAME = "status";
    private static final String PARAM_ID = "idProjectForNewTask";
    private static final String TASK_PAGE = "page.createTask";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final EmployeeServiceImpl serviceEmployee;
    private final ProjectServiceImpl serviceProject;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCreateTaskInProjectPageCommand() {
        this.serviceEmployee = EmployeeServiceImpl.getInstance();
        this.serviceProject = ProjectServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        Project project=null;
        try {
            final Long id = Long.parseLong(request.getParameter(PARAM_ID));
            final String status = "Create in Project";
            final Optional<Project> projectOptional= serviceProject.findById(id);
            final List<Employee> employees = serviceEmployee.findAll();
            if(projectOptional.isPresent()){
                project=projectOptional.get();
            }
            request.addAttributeToJsp(STATUS_ATTRIBUTE_NAME,status);
            request.addAttributeToJsp(PROJECT_ATTRIBUTE_NAME, project);
            request.addAttributeToJsp(EMPLOYEE_ATTRIBUTE_NAME, employees);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Show create task:" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(TASK_PAGE));
    }

    public static ShowCreateTaskInProjectPageCommand getInstance() {
        return ShowCreateTaskInProjectPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCreateTaskInProjectPageCommand INSTANCE =
                new ShowCreateTaskInProjectPageCommand();
    }
}
