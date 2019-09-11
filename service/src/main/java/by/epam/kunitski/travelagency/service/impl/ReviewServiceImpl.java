package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.Review;
import by.epam.kunitski.travelagency.dao.repository.ReviewDAO;
import by.epam.kunitski.travelagency.service.ReviewService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List<Review> getAllByTourId(int tourId) {
        return reviewDAO.getAllByTourId(tourId);
    }

    @Override
    public List<Review> getAllByUserId(int userId) {
        return reviewDAO.getAllByUserId(userId);
    }

    @Override
    public Review add(Review review) {
        return reviewDAO.save(review);
    }

    @Override
    public Review update(Review review, String id) throws EntityNotFoundException {
        if (!reviewDAO.existsById(id)) {
            throw new EntityNotFoundException("Couldn't find " + Review.class.getSimpleName() + " with id=" + id);
        }

        review.setId(id);

        return reviewDAO.save(review);
    }

    @Override
    public List<Review> getAll() {
        return reviewDAO.findAll();
    }

    @Override
    public Optional<Review> getById(String id) {
        return reviewDAO.findById(id);
    }

    @Override
    public void delete(String id) {
        reviewDAO.deleteById(id);
    }
}
