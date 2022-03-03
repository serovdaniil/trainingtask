package com.qulix.serovdo.core.command.page.task;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.service.ProjectServiceImpl;
import com.qulix.serovdo.core.service.TaskServiceImpl;

import java.util.List;
import java.util.logging.Logger;

/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class ShowTaskPageCommand implements Command {
    private static final String PROJECT_ATTRIBUTE_NAME = "tasks";
    private static final String PROJECT_PAGE = "page.task";

    private static final Logger logger = Logger.getLogger(ShowTaskPageCommand.class.getName());

    private final TaskServiceImpl service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowTaskPageCommand() {
        this.service = TaskServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final List<Task> allTask = service.findAll();
            request.addAttributeToJsp(PROJECT_ATTRIBUTE_NAME, allTask);
        } catch (ServiceException e) {
            logger.warning("Show all tasks:" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(PROJECT_PAGE));
    }

    public static ShowTaskPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowTaskPageCommand INSTANCE =
                new ShowTaskPageCommand();
    }
}
