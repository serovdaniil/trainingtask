package com.qulix.serovdo.api.dao;

import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.core.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProjectDao {
    boolean create(String name, String description) throws DaoException;

    boolean updateEntity(Long id, String name, String description) throws DaoException;

    boolean removeEntity(Long id) throws DaoException;

    List<Project> findAll() throws DaoException;

    Optional<Project> findById(Long id) throws DaoException;
}
