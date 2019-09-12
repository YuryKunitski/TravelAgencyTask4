package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ReviewDAO extends MongoRepository<Review, String> {

    @Query("{'tour.id': ?0}")
    List<Review> getAllByTourId(String tourId);

    @Query("{'user.id': ?0}")
    List<Review> getAllByUserId(String userId);
}
