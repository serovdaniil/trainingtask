package com.qulix.serovdo.core.command.page.employee;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.service.EmployeeServiceImpl;

import java.util.Optional;
import java.util.logging.Logger;

public class ShowUpdateEmployeePageCommand implements Command {
    private static final String EMPLOYEE_ATTRIBUTE_NAME = "employee";
    private static final String PARAM_ID = "id";
    private static final String EMPLOYEE_PAGE = "page.updateEmployee";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final EmployeeServiceImpl service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowUpdateEmployeePageCommand() {
        this.service = EmployeeServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        Employee employee=null;
        try {
            final Optional<Employee> employeeOptional = service.findById(id);
            if (employeeOptional.isPresent()) {
                employee = employeeOptional.get();
            }
            request.addAttributeToJsp(EMPLOYEE_ATTRIBUTE_NAME, employee);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Show id employee:" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(EMPLOYEE_PAGE));
    }

    public static ShowUpdateEmployeePageCommand getInstance() {
        return ShowUpdateEmployeePageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowUpdateEmployeePageCommand INSTANCE =
                new ShowUpdateEmployeePageCommand();
    }
}
