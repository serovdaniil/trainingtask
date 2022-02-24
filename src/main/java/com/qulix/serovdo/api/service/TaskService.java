package com.qulix.serovdo.api.service;

import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.api.entity.StatusTask;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    boolean create(StatusTask status, String name, Project nameProject, String job, String startDate,
                   String finishDate, Employee employee) throws ValidationException, ServiceException;

    boolean updateEntity(Long id, StatusTask status, String name, Project nameProject, String job, String startDate,
                         String finishDate, Employee employee) throws ValidationException, ServiceException;

    boolean removeEntity(Long id) throws ValidationException, ServiceException;

    List<Task> findAll() throws ServiceException;

    Optional<Task> findById(Long id) throws ValidationException, ServiceException;
}
