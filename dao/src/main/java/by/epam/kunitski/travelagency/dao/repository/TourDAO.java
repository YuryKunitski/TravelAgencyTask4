package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface TourDAO extends MongoRepository<Tour, String>, TourDAOCustom {

    @Query("{'user.id': ?0}")
    List<Tour> getAllByUserId(String userId);
}
