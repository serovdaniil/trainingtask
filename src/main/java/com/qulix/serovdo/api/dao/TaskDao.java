package com.qulix.serovdo.api.dao;

import com.qulix.serovdo.api.entity.Task;
import com.qulix.serovdo.core.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    boolean create(Long idStatus, String name, Long idProject, Long job, String startDate,
                   String finishDate, Long idEmployee) throws DaoException;

    boolean updateEntity(Long id, Long idStatus, String name, Long idProject, Long job, String startDate,
                         String finishDate, Long idEmployee) throws DaoException;

    boolean removeEntity(Long id) throws DaoException;

    List<Task> findAll() throws DaoException;

    List<Task> findAllTaskInProject(Long id) throws DaoException;

    Optional<Task> findById(Long id) throws DaoException;
}
