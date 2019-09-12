package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.repository.HotelDAO;
import by.epam.kunitski.travelagency.service.HotelService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;


    @Override
    public Hotel add(Hotel hotel) {
        return hotelDAO.save(hotel);
    }

    @Override
    public Hotel update(Hotel hotel, String id) throws EntityNotFoundException {

        if (!hotelDAO.existsById(id)){
            throw new EntityNotFoundException("Couldn't find " + Hotel.class.getSimpleName() + " with id=" + id);
        }

        hotel.setId(id);

        return hotelDAO.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelDAO.findAll();
    }

    @Override
    public Optional<Hotel> getById(String id) {
        return hotelDAO.findById(id);
    }

    @Override
    public void delete(String id) {
        hotelDAO.deleteById(id);
    }
}
