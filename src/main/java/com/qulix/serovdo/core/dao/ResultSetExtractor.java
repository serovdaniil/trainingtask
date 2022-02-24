package com.qulix.serovdo.core.dao;

import com.qulix.serovdo.api.entity.Entity;
import com.qulix.serovdo.core.exception.EntityExtractionFailedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ResultSetExtractor <T extends Entity>{
    T extract(ResultSet resultSet) throws EntityExtractionFailedException;

    default List<T> extractAll(ResultSet resultSet) throws SQLException, EntityExtractionFailedException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            final T entity = this.extract(resultSet);
            entities.add(entity);
        }
        return entities;
    }
}
