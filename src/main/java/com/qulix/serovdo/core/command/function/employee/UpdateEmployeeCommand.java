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

public class UpdateEmployeeCommand implements Command {
    private static final String EMPLOYEE_ATTRIBUTE_NAME = "employees";
    private static final String PARAM_ID = "id";
    private static final String PARAM_FIRSTNAME = "firstName";
    private static final String PARAM_LASTNAME = "lastName";
    private static final String PARAM_PATRONYMIC = "patronymic";
    private static final String PARAM_POSITION = "position";
    private static final String EMPLOYEE_PAGE = "/controller?command=employee_page";

    private final EmployeeServiceImpl service;
    private final RequestFactory requestFactory;

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    UpdateEmployeeCommand() {
        this.service = EmployeeServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String firstName = request.getParameter(PARAM_FIRSTNAME);
        final String lastName = request.getParameter(PARAM_LASTNAME);
        final String patronymic = request.getParameter(PARAM_PATRONYMIC);
        final String position = request.getParameter(PARAM_POSITION);
        try {
            boolean resultOperation=service.updateEntity(id, firstName,lastName,patronymic,position);
            final List<Employee> allEmployee = service.findAll();
            request.addAttributeToJsp(EMPLOYEE_ATTRIBUTE_NAME, allEmployee);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Update employee:" + e);
        }
        return requestFactory.createRedirectResponse(EMPLOYEE_PAGE);
    }

    public static UpdateEmployeeCommand getInstance() {
        return UpdateEmployeeCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateEmployeeCommand INSTANCE =
                new UpdateEmployeeCommand();
    }
}
