package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.repository.TourDAO;
import by.epam.kunitski.travelagency.service.TourService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourDAO tourDAO;

    @Override
    public List<Tour> getAllByUserId(String userId) {
        return tourDAO.getAllByUserId(userId);
    }

    @Override
    public Tour add(Tour tour) {
        return tourDAO.save(tour);
    }

    @Override
    public Tour update(Tour tour, String id) throws EntityNotFoundException {

        if (!tourDAO.existsById(id)){
            throw new EntityNotFoundException("Couldn't find " + Tour.class.getSimpleName() + " with id=" + id);
        }

        tour.setId(id);

        return tourDAO.save(tour);
    }

    @Override
    public List<Tour> getAll() {
        return tourDAO.findAll();
    }

    @Override
    public Optional<Tour> getById(String id) {
        return tourDAO.findById(id);
    }

    @Override
    public void delete(String id) {
        tourDAO.deleteById(id);
    }
}
