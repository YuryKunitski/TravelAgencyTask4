package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.repository.CountryDAO;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDAO countryDAO;

    @Override
    public Country add(Country country) {
        return countryDAO.save(country);
    }

    @Override
    public Country update(Country country, String id) throws EntityNotFoundException {
        Optional<Country> optionalCountry = countryDAO.findById(id);

        if (optionalCountry.isEmpty()){
            throw new EntityNotFoundException();
        }

        Country countryUpdate = countryDAO.findById(id).get();
        countryUpdate.setName(country.getName());

        return countryUpdate;
    }

    @Override
    public List<Country> getAll() {
        return countryDAO.findAll();
    }

    @Override
    public Optional<Country> getById(String id) {
        return countryDAO.findById(id);
    }

    @Override
    public void delete(String id) {
        countryDAO.deleteById(id);
    }
}
