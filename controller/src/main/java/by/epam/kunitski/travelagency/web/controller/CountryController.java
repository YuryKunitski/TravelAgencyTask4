package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

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

    @PutMapping
    public ResponseEntity<Country> updateCountry(@Valid @RequestBody Country country,
                                 @RequestParam String id) throws EntityNotFoundException{

        return ResponseEntity.ok(countryService.update(country, id));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCountry(@RequestParam String id){

        if (countryService.getById(id).isEmpty()){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        countryService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

}
