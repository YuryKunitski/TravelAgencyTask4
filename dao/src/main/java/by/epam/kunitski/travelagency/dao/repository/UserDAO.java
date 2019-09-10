package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserDAO extends MongoRepository<User, String> {

    @Query("{ 'username' : ?0 }")
    User findUserByUsername(String username);
}
