package com.qulix.serovdo.core.command.page.project;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.service.ProjectServiceImpl;
import com.qulix.serovdo.core.service.TaskServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ShowUpdateProjectPageCommand implements Command {
    private static final String PROJECT_ATTRIBUTE_NAME = "project";
    private static final String TASK_ATTRIBUTE_NAME = "tasks";
    private static final String PARAM_ID = "id";
    private static final String PROJECT_PAGE = "page.updateProject";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final ProjectServiceImpl serviceProject;
    private final TaskServiceImpl serviceTask;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowUpdateProjectPageCommand() {
        this.serviceProject = ProjectServiceImpl.getInstance();
        this.serviceTask = TaskServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        Project project=null;
        try {
            final Optional<Project> projectOptional = serviceProject.findById(id);
            final List<Task> tasks= serviceTask.findAllTaskInProject(id);
            System.out.println(tasks);
            if (projectOptional.isPresent()) {
                project = projectOptional.get();
            }
            request.addAttributeToJsp(PROJECT_ATTRIBUTE_NAME, project);
            request.addAttributeToJsp(TASK_ATTRIBUTE_NAME, tasks);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Show id project:" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(PROJECT_PAGE));
    }

    public static ShowUpdateProjectPageCommand getInstance() {
        return ShowUpdateProjectPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowUpdateProjectPageCommand INSTANCE =
                new ShowUpdateProjectPageCommand();
    }
}
