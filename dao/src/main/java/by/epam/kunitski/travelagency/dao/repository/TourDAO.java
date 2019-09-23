package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.searchform.TourForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface TourDAO extends MongoRepository<Tour, String>, QuerydslPredicateExecutor<Tour> {

    @Query("{'user.id': ?0}")
    List<Tour> getAllByUserId(String userId);

    List<Tour> findToursByCriteria(TourForm tourForm);
}
