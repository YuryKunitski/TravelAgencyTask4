package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.google.common.collect.Lists;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Country> addCountry(@Valid @RequestBody Country country) {

        return ResponseEntity.ok(countryService.add(country));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<Country> getCountryById(@RequestParam String id) {

        return ResponseEntity.ok(countryService.getById(id).orElseThrow());
    }

    @GetMapping("get_all")
    public ResponseEntity<List<Country>> getAllCountry() {

        return ResponseEntity.ok(countryService.getAll());
    }

    @GetMapping("get_all_pagination")
    public List<Country> getAllCountryByPagination(@RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page, 3, Sort.by("name"));
        Page<Country> countryPage = countryService.findAll(pageable);
        return Lists.newArrayList(countryPage);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Country> updateCountry(@RequestParam String id,
                                                 @Valid @RequestBody Country country) throws EntityNotFoundException {

        return ResponseEntity.ok(countryService.update(country, id));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity<?> deleteCountry(@RequestParam String id) {

        if (countryService.getById(id).isEmpty()) {
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        countryService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

}
