package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.service.HotelService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    
    @Autowired
    private HotelService hotelService;

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Hotel> addHotel(@Valid @RequestBody Hotel hotel) {

        return ResponseEntity.ok(hotelService.add(hotel));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<Hotel> getHotelById(@RequestParam String id) {

        return ResponseEntity.ok(hotelService.getById(id).orElseThrow());
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Hotel>> getAllHotel() {

        return ResponseEntity.ok(hotelService.getAll());
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Hotel> updateHotel(@Valid @RequestBody Hotel hotel,
                                                 @RequestParam String id) throws EntityNotFoundException {

        return ResponseEntity.ok(hotelService.update(hotel, id));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity<?> deleteHotel(@RequestParam String id){

        if (hotelService.getById(id).isEmpty()){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        hotelService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

}
