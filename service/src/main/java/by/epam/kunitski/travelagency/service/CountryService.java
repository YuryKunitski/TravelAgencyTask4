package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.dao.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService extends EntityService<Country> {
    Page<Country> findAll(Pageable pageable);
}
