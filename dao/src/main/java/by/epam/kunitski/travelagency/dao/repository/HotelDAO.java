package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelDAO extends MongoRepository<Hotel, String> {
}
