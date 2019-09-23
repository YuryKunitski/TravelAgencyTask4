package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.service.TourService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/tour")
public class TourController {
    
    @Autowired
    private TourService tourService;

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Tour> addTour(@Valid @RequestBody Tour tour) {

        return ResponseEntity.ok(tourService.add(tour));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<Tour> getTourById(@RequestParam String id) {

        return ResponseEntity.ok(tourService.getById(id).orElseThrow());
    }

    @GetMapping("get_all")
    public ResponseEntity<List<Tour>> getAllTour() {

        return ResponseEntity.ok(tourService.getAll());
    }

    @GetMapping("get_all_by_user_id")
    public ResponseEntity<List<Tour>> getAllTourByUserId(@RequestParam String id) {

        return ResponseEntity.ok(tourService.getAllByUserId(id));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Tour> updateTour(@Valid @RequestBody Tour tour,
                                                 @RequestParam String id) throws EntityNotFoundException {

        return ResponseEntity.ok(tourService.update(tour, id));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity<?> deleteTour(@RequestParam String id){

        if (tourService.getById(id).isEmpty()){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        tourService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }
}
