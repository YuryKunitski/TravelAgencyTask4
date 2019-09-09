package by.epam.kunitski.travelagency.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EntityDAO<T> extends MongoRepository<T, Integer> {

//    List<T> getAllByCriteria(Specification<T> specification);

}
