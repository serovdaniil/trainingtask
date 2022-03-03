package com.qulix.serovdo.core.command.page.employee;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.service.EmployeeServiceImpl;

import java.util.List;
import java.util.logging.Logger;

/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class ShowEmployeePageCommand implements Command {
    private static final String EMPLOYEE_ATTRIBUTE_NAME = "employees";
    private static final String EMPLOYEE_PAGE = "page.employee";

    private static final Logger logger = Logger.getLogger(ShowEmployeePageCommand.class.getName());

    private final EmployeeServiceImpl service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowEmployeePageCommand() {
        this.service = EmployeeServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final List<Employee> allEmployee = service.findAll();
            request.addAttributeToJsp(EMPLOYEE_ATTRIBUTE_NAME, allEmployee);
        } catch (ServiceException e) {
            logger.warning("Show all employees:" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(EMPLOYEE_PAGE));
    }

    public static ShowEmployeePageCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowEmployeePageCommand INSTANCE =
                new ShowEmployeePageCommand();
    }
}
