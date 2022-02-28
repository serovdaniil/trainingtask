package com.qulix.serovdo.core.command.function.task;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.RequestFactory;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;
import com.qulix.serovdo.core.service.TaskServiceImpl;

import java.util.List;
import java.util.logging.Logger;

/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class RemoveTaskCommand implements Command {
    private static final String TASK_ATTRIBUTE_NAME = "tasks";
    private static final String PARAM_ID = "id";
    private static final String TASK_PAGE = "/controller?command=task_page";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final TaskServiceImpl service;
    private final RequestFactory requestFactory;

    RemoveTaskCommand() {
        this.service = TaskServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        try {
            boolean resultOperation=service.removeEntity(id);
            final List<Task> allTask = service.findAll();
            request.addAttributeToJsp(TASK_ATTRIBUTE_NAME, allTask);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Remove task:" + e);
        }
        return requestFactory.createRedirectResponse(TASK_PAGE);
    }

    public static RemoveTaskCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveTaskCommand INSTANCE =
                new RemoveTaskCommand();
    }
}
