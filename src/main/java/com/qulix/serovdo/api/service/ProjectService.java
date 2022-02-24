package com.qulix.serovdo.api.service;

import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    boolean create(String name, String description) throws ValidationException, ServiceException;

    boolean updateEntity(Long id, String name, String description) throws  ValidationException, ServiceException;

    boolean removeEntity(Long id) throws  ValidationException, ServiceException;

    List<Project> findAll() throws ServiceException;

    Optional<Project> findById(Long id) throws  ValidationException, ServiceException;
}
