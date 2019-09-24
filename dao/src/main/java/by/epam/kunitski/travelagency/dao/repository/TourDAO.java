package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.searchform.TourForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;

public interface TourDAO extends MongoRepository<Tour, String> {

    @Query("{'user.id': ?0}")
    List<Tour> getAllByUserId(String userId);

//    @Query("{ cost : { $gte: ?0 }, cost : { $lte: ?1 } }")
//    List<Tour> findToursByCriteria(TourForm tourForm);
}
