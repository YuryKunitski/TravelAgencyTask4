package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewDAO extends MongoRepository<Review, String> {

    @Query("{'tour.id': ?0}")
    List<Review> getAllreviewByTourId(int tourId);

    @Query("{'user.id': ?0}")
    List<Review> getAllByUserId(int userId);
}
