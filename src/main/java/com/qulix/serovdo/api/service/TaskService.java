package com.qulix.serovdo.api.service;

import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;

import java.util.List;

public interface TaskService extends EntityService<Task> {
    boolean create(Long idStatus, String name, Long idProject, Long job, String startDate,
                   String finishDate, Long idEmployee) throws ValidationException, ServiceException;

    boolean updateEntity(Long id, Long idStatus, String name, Long idProject, Long job, String startDate,
                         String finishDate, Long idEmployee) throws ValidationException, ServiceException;

    List<Task> findAllTaskInProject(Long id) throws ValidationException, ServiceException;
}
