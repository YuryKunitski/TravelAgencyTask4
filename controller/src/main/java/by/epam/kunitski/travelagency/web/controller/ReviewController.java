package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Review;
import by.epam.kunitski.travelagency.service.ReviewService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addReview(@Valid @RequestBody Review review) {

        return ResponseEntity.ok(reviewService.add(review));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<Review> getReviewById(@RequestParam String id) {

        return ResponseEntity.ok(reviewService.getById(id).orElseThrow());
    }

    @GetMapping("get_all")
    public ResponseEntity<List<Review>> getAllReview() {

        return ResponseEntity.ok(reviewService.getAll());
    }

    @GetMapping("get_all_by_tour_id")
    public ResponseEntity<List<Review>> getAllReviewByTourId(@RequestParam String id) {

        return ResponseEntity.ok(reviewService.getAllByTourId(id));
    }

    @GetMapping("get_all_by_user_id")
    public ResponseEntity<List<Review>> getAllReviewByUserId(@RequestParam String id) {

        return ResponseEntity.ok(reviewService.getAllByUserId(id));
    }

    @PutMapping
    public ResponseEntity<Review> updateReview(@Valid @RequestBody Review review,
                                                 @RequestParam String id) throws EntityNotFoundException {

        return ResponseEntity.ok(reviewService.update(review, id));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@RequestParam String id){

        if (reviewService.getById(id).isEmpty()){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        reviewService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }
}
