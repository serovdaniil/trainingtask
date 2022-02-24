package com.qulix.serovdo.api.service;

import com.qulix.serovdo.api.entity.Project;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;

public interface ProjectService extends EntityService<Project> {
    boolean create(String name, String description) throws ValidationException, ServiceException;

    boolean updateEntity(Long id, String name, String description) throws ValidationException, ServiceException;
}
