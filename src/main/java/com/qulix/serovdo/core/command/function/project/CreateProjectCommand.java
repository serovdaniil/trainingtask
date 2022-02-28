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

/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class CreateProjectCommand implements Command {
    private static final String PROJECT_ATTRIBUTE_NAME = "projects";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PROJECT_PAGE = "/controller?command=project_page";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final ProjectServiceImpl service;
    private final RequestFactory requestFactory;

    CreateProjectCommand() {
        this.service = ProjectServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(PARAM_NAME);
        final String description = request.getParameter(PARAM_DESCRIPTION);
        try {
            boolean resultOperation=service.create(name,description);
            final List<Project> allProject = service.findAll();
            request.addAttributeToJsp(PROJECT_ATTRIBUTE_NAME, allProject);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Create project:" + e);
        }
        return requestFactory.createRedirectResponse(PROJECT_PAGE);
    }

    public static CreateProjectCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateProjectCommand INSTANCE =
                new CreateProjectCommand();
    }
}
