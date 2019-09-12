package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {
    T add(T entity);

    T update(T entity, String id) throws EntityNotFoundException;

    List<T> getAll();

    Optional<T> getById(String id);

    void delete(String id);
}
