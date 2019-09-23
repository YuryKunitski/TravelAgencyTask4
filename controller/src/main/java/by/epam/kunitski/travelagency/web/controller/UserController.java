package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.UserService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add_member")
    public ResponseEntity<User> addMember(@Valid @RequestBody User user) {

        user.setPassword(getBCryptPassword(user.getPassword()));

        return ResponseEntity.ok(userService.add(user));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add_admin")
    public ResponseEntity<User> addAdmin(@Valid @RequestBody User user) {

        user.setPassword(getBCryptPassword(user.getPassword()));

        return ResponseEntity.ok(userService.addAdmin(user));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<User> getUserById(@RequestParam String id) {

        return ResponseEntity.ok(userService.getById(id).orElseThrow());
    }


    @GetMapping("/get_by_username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {

        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("get_all")
    public ResponseEntity<List<User>> getAllUser() {

        return ResponseEntity.ok(userService.getAll());
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user,
                                                 @RequestParam String id) throws EntityNotFoundException {

        String bCryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(bCryptPassword);

        return ResponseEntity.ok(userService.update(user, id));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam String id){

        if (userService.getById(id).isEmpty()){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        userService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

    private String getBCryptPassword(String password){
        return passwordEncoder.encode(password);
    }
}
