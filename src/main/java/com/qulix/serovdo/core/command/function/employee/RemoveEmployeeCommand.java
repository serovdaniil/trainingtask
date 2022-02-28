package com.qulix.serovdo.core.command.function.employee;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.service.EmployeeServiceImpl;

import java.util.List;
import java.util.logging.Logger;

/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class RemoveEmployeeCommand implements Command {
    private static final String EMPLOYEE_ATTRIBUTE_NAME = "employees";
    private static final String PARAM_ID = "id";
    private static final String EMPLOYEE_PAGE = "/controller?command=employee_page";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final EmployeeServiceImpl service;
    private final RequestFactory requestFactory;

    RemoveEmployeeCommand() {
        this.service = EmployeeServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        try {
            boolean resultOperation=service.removeEntity(id);
            final List<Employee> allProject = service.findAll();
            request.addAttributeToJsp(EMPLOYEE_ATTRIBUTE_NAME, allProject);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Remove employee:" + e);
        }
        return requestFactory.createRedirectResponse(EMPLOYEE_PAGE);
    }

    public static RemoveEmployeeCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveEmployeeCommand INSTANCE =
                new RemoveEmployeeCommand();
    }
}
