package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.UserService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {

        return ResponseEntity.ok(userService.add(user));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<User> getUserById(@RequestParam String id) {

        return ResponseEntity.ok(userService.getById(id).orElseThrow());
    }

    @GetMapping("/get_by_username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {

        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @GetMapping("get_all")
    public ResponseEntity<List<User>> getAllUser() {

        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user,
                                                 @RequestParam String id) throws EntityNotFoundException {

        return ResponseEntity.ok(userService.update(user, id));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam String id){

        if (userService.getById(id).isEmpty()){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        userService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }
}
