package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.repository.CountryDAO;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {


    @Autowired
    private CountryDAO countryDAO;

    @Override
    public Page<Country> findAll(Pageable pageable) {
        return countryDAO.findAll(pageable);
    }

    @Override
    public Country add(Country country) {
        return countryDAO.save(country);
    }

    @Override
    public Country update(Country country, String id) throws EntityNotFoundException {

        if (!countryDAO.existsById(id)) {
            throw new EntityNotFoundException("Couldn't find " + Country.class.getSimpleName() + " with id=" + id);
        }

        country.setId(id);

        return countryDAO.save(country);
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
