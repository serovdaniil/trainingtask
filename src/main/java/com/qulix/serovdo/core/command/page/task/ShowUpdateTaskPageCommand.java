package com.qulix.serovdo.core.command.page.task;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.service.EmployeeServiceImpl;
import com.qulix.serovdo.core.service.TaskServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ShowUpdateTaskPageCommand implements Command {
    private static final String TASK_ATTRIBUTE_NAME = "task";
    private static final String EMPLOYEE_ATTRIBUTE_NAME = "employees";
    private static final String PARAM_ID = "id";
    private static final String TASK_PAGE = "page.updateTask";

    private static final Logger logger = Logger.getLogger(ShowUpdateTaskPageCommand.class.getName());

    private final EmployeeServiceImpl serviceEmployee;
    private final TaskServiceImpl serviceTask;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowUpdateTaskPageCommand() {
        this.serviceEmployee = EmployeeServiceImpl.getInstance();
        this.serviceTask=TaskServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        Task task=null;
        try {
            final Long id = Long.parseLong(request.getParameter(PARAM_ID));
            Optional<Task> taskOptional= serviceTask.findById(id);
            final List<Employee> employees = serviceEmployee.findAll();
            request.addAttributeToJsp(EMPLOYEE_ATTRIBUTE_NAME, employees);
            if (taskOptional.isPresent()){
                task=taskOptional.get();
            }
            request.addAttributeToJsp(TASK_ATTRIBUTE_NAME,task);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Show update task:" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(TASK_PAGE));
    }

    public static ShowUpdateTaskPageCommand getInstance() {
        return ShowUpdateTaskPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowUpdateTaskPageCommand INSTANCE =
                new ShowUpdateTaskPageCommand();
    }
}
