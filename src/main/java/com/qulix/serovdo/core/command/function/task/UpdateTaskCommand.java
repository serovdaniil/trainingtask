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

public class UpdateTaskCommand implements Command {
    private static final String TASK_ATTRIBUTE_NAME = "tasks";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_PROJECT_ID = "idProject";
    private static final String PARAM_JOB = "job";
    private static final String PARAM_START_DATE = "startDate";
    private static final String PARAM_FINISH_DATE = "finishDate";
    private static final String PARAM_NAME_STATUS = "nameStatus";
    private static final String PARAM_ID_EMPLOYEE = "idEmployee";
    private static final String PARAM_ID = "id";
    private static final String TASK_PAGE = "/controller?command=task_page";

    private final TaskServiceImpl service;
    private final RequestFactory requestFactory;

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    UpdateTaskCommand() {
        this.service = TaskServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String name = request.getParameter(PARAM_NAME);
        final Long idProject = Long.parseLong(request.getParameter(PARAM_PROJECT_ID));
        final Long idStatus = Long.parseLong(request.getParameter(PARAM_NAME_STATUS));
        final Long idEmployee = Long.parseLong(request.getParameter(PARAM_ID_EMPLOYEE));
        final Long job = Long.parseLong(request.getParameter(PARAM_JOB));
        final String startDate = request.getParameter(PARAM_START_DATE);
        final String finishDate = request.getParameter(PARAM_FINISH_DATE);
        try {
            boolean resultOperation=service.updateEntity(id,idStatus,name,idProject,job,startDate,finishDate,idEmployee);
            final List<Task> allTask = service.findAll();
            request.addAttributeToJsp(TASK_ATTRIBUTE_NAME, allTask);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Update task:" + e);
        }
        return requestFactory.createRedirectResponse(TASK_PAGE);
    }

    public static UpdateTaskCommand getInstance() {
        return UpdateTaskCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateTaskCommand INSTANCE =
                new UpdateTaskCommand();
    }
}
