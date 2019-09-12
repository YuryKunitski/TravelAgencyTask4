package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryDAO extends MongoRepository<Country, String> {
}
