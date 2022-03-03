package com.qulix.serovdo.core.command.function.project;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.service.ProjectServiceImpl;

import java.util.List;
import java.util.logging.Logger;

public class UpdateProjectCommand implements Command {
    private static final String PROJECT_ATTRIBUTE_NAME = "projects";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_ID = "id";
    private static final String PROJECT_PAGE = "/trainingtask/controller?command=project_page";

    private final ProjectServiceImpl service;
    private final RequestFactory requestFactory;

    private static final Logger logger = Logger.getLogger(UpdateProjectCommand.class.getName());

    UpdateProjectCommand() {
        this.service = ProjectServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String name = request.getParameter(PARAM_NAME);
        final String description = request.getParameter(PARAM_DESCRIPTION);
        try {
            boolean resultOperation=service.updateEntity(id, name, description);
            final List<Project> allProject = service.findAll();
            request.addAttributeToJsp(PROJECT_ATTRIBUTE_NAME, allProject);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Update project:" + e);
        }
        return requestFactory.createRedirectResponse(PROJECT_PAGE);
    }

    public static UpdateProjectCommand getInstance() {
        return UpdateProjectCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateProjectCommand INSTANCE =
                new UpdateProjectCommand();
    }
}
