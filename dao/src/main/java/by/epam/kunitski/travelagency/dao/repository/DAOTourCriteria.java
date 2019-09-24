package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.searchform.TourForm;

import java.util.List;

public interface DAOTourCriteria {

    List<Tour> findToursByCriteria(TourForm tourForm);
}
