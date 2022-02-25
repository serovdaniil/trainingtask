package com.qulix.serovdo.core.command.page.employee;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;

public class ShowCreateEmployeePageCommand implements Command {
    private static final String EMPLOYEE_PAGE = "page.createEmployee";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCreateEmployeePageCommand() {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(EMPLOYEE_PAGE));
    }

    public static ShowCreateEmployeePageCommand getInstance() {
        return ShowCreateEmployeePageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCreateEmployeePageCommand INSTANCE =
                new ShowCreateEmployeePageCommand();
    }
}
