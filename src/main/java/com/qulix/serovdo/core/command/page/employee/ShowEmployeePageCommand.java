package com.qulix.serovdo.core.command.page.employee;

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

/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class ShowEmployeePageCommand implements Command {
    private static final String PROJECT_ATTRIBUTE_NAME = "employees";
    private static final String PROJECT_PAGE = "page.employee";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

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
            request.addAttributeToJsp(PROJECT_ATTRIBUTE_NAME, allEmployee);
        } catch (ServiceException e) {
            logger.warning("Show all projects:" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(PROJECT_PAGE));
    }

    public static ShowEmployeePageCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowEmployeePageCommand INSTANCE =
                new ShowEmployeePageCommand();
    }
}
