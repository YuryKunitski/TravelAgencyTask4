package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.dao.entity.Tour;

import java.util.List;

public interface TourService extends EntityService<Tour> {

    List<Tour> getAllByUserId(int userId);
}
