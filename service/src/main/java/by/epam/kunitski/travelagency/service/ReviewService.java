package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.dao.entity.Review;

import java.util.List;

public interface ReviewService extends EntityService<Review> {

    List<Review> getAllByTourId(int tourId);

    List<Review> getAllByUserId(int userId);
}
