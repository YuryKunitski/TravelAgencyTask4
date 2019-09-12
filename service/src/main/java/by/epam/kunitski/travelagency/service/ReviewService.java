package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.dao.entity.Review;

import java.util.List;

public interface ReviewService extends EntityService<Review> {

    List<Review> getAllByTourId(String tourId);

    List<Review> getAllByUserId(String userId);
}
