package com.qulix.serovdo.core.command.page.project;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;

public class ShowCreateProjectPageCommand implements Command {
    private static final String PROJECT_PAGE = "page.createProject";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCreateProjectPageCommand() {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(PROJECT_PAGE));
    }

    public static ShowCreateProjectPageCommand getInstance() {
        return ShowCreateProjectPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCreateProjectPageCommand INSTANCE =
                new ShowCreateProjectPageCommand();
    }
}
