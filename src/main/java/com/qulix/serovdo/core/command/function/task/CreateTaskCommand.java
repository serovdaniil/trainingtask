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
public class CreateTaskCommand implements Command {
    private static final String TASK_ATTRIBUTE_NAME = "projects";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ID_PROJECT = "nameProject";
    private static final String PARAM_JOB = "job";
    private static final String PARAM_START_DATE = "startDate";
    private static final String PARAM_FINISH_DATE = "finishDate";
    private static final String PARAM_STATUS = "nameStatus";
    private static final String PARAM_ID_EMPLOYEE = "idEmployee";
    private static final String TASK_PAGE = "/controller?command=task_page";

    private static final Logger logger = Logger.getLogger("com.wombat.nose");

    private final TaskServiceImpl service;
    private final RequestFactory requestFactory;

    CreateTaskCommand() {
        this.service = TaskServiceImpl.getInstance();
        this.requestFactory = RequestFactory.getInstance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String nameTask = request.getParameter(PARAM_NAME);
        final Long idProject = Long.parseLong(request.getParameter(PARAM_ID_PROJECT));
        final Long job = Long.parseLong(request.getParameter(PARAM_JOB));
        final String startDate =request.getParameter(PARAM_START_DATE);
        final String finishDate =request.getParameter(PARAM_FINISH_DATE);
        final Long statusTask =Long.parseLong(request.getParameter(PARAM_STATUS));
        final Long idEmployee = Long.parseLong(request.getParameter(PARAM_ID_EMPLOYEE));
        try {
            boolean resultOperation=service.create(statusTask,nameTask,idProject,job,startDate,finishDate,idEmployee);
            final List<Task> allTask = service.findAll();
            request.addAttributeToJsp(TASK_ATTRIBUTE_NAME, allTask);
        } catch (ServiceException | ValidationException e) {
            logger.warning("Create project:" + e);
        }
        return requestFactory.createRedirectResponse(TASK_PAGE);
    }

    public static CreateTaskCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateTaskCommand INSTANCE =
                new CreateTaskCommand();
    }
}
