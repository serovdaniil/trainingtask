package com.qulix.serovdo.api.dao;

import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.api.entity.StatusTask;
import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    boolean create(StatusTask status, String name, Project nameProject, String job, String startDate,
                   String finishDate, Employee employee) throws DaoException;

    boolean updateEntity(Long id, StatusTask status, String name, Project nameProject, String job, String startDate,
                         String finishDate, Employee employee) throws DaoException;

    boolean removeEntity(Long id) throws DaoException;

    List<Task> findAll() throws DaoException;

    List<Task> findAllTaskInProject(Long id) throws DaoException;

    Optional<Task> findById(Long id) throws DaoException;
}
