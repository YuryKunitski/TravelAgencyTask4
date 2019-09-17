package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.dao.repository.UserDAO;
import by.epam.kunitski.travelagency.service.UserService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User add(User user) {

        user.setRole(User.UserRole.MEMBER);

        return userDAO.save(user);
    }

    @Override
    public User addAdmin(User userAdmin) {

        userAdmin.setRole(User.UserRole.ADMIN);

        return userDAO.save(userAdmin);
    }

    @Override
    public User update(User user, String id) throws EntityNotFoundException {

        if (!userDAO.existsById(id)){
            throw new EntityNotFoundException("Couldn't find " + User.class.getSimpleName() + " with id=" + id);
        }

        user.setId(id);

        return userDAO.save(user);
    }

    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> getById(String id) {
        return userDAO.findById(id);
    }

    @Override
    public void delete(String id) {
        userDAO.deleteById(id);
    }

}
