package com.qulix.serovdo.core.command.page.project;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.service.ProjectServiceImpl;

import java.util.List;

/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class ShowProjectPageCommand implements Command {
    private static final String PROJECT_ATTRIBUTE_NAME = "projects";
    private static final String PROJECT_PAGE = "page.main";

    private final ProjectServiceImpl service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowProjectPageCommand() {
        this.service = ProjectServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final List<Project> allProject = service.findAll();
            request.addAttributeToJsp(PROJECT_ATTRIBUTE_NAME, allProject);
        } catch (ServiceException e) {
        }
        return requestFactory.createForwardResponse(propertyContext.get(PROJECT_PAGE));
    }

    public static ShowProjectPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowProjectPageCommand INSTANCE =
                new ShowProjectPageCommand();
    }
}
