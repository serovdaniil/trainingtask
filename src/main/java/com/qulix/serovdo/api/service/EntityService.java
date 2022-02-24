package com.qulix.serovdo.api.service;

import com.qulix.serovdo.api.entity.Employee;
import com.qulix.serovdo.api.entity.Entity;
import com.qulix.serovdo.core.exception.ServiceException;
import com.qulix.serovdo.core.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface EntityService<T extends Entity>{
    boolean removeEntity(Long id) throws ValidationException, ServiceException;

    List<T> findAll() throws ServiceException;

    Optional<T> findById(Long id) throws ValidationException, ServiceException;
}
