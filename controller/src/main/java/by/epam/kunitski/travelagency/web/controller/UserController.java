package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.UserService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Authenticator;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestHighLevelClient client;

    @PostMapping("/index")
    public void index() throws IOException {

        CreateIndexRequest request = new CreateIndexRequest("users");
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 2)
        );

        Map<String, Object> message = new HashMap<>();
        message.put("type", "text");

        Map<String, Object> keyWordMap = new HashMap<>();
        Map<String, Object> keyWordValueMap = new HashMap<>();
        keyWordValueMap.put("type", "keyword");
        keyWordValueMap.put("ignore_above", 256);
        keyWordMap.put("keyword", keyWordValueMap);
        message.put("fields", keyWordMap);

        Map<String, Object> properties = new HashMap<>();
        properties.put("id", message);
        properties.put("username", message);
        properties.put("password", message);

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        request.mapping(mapping);

        GetIndexRequest getIndexRequest = new GetIndexRequest("users");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            System.out.println("response id: " + indexResponse.index());
        }
    }

    @GetMapping("/{id}")
    public User read(@PathVariable final String id) throws IOException {
        GetRequest getRequest = new GetRequest("users", id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        User user = new ObjectMapper().readValue(getResponse.getSourceAsString(), User.class);
        return user;
    }

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

    //    @Secured("ROLE_ADMIN")
    @GetMapping("get_all")
    public ResponseEntity<List<User>> getAllUser() {

        return ResponseEntity.ok(userService.getAll());
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user,
                                           @RequestParam String id) throws EntityNotFoundException {

        String bCryptPassword = getBCryptPassword(user.getPassword());
        user.setPassword(bCryptPassword);

        return ResponseEntity.ok(userService.update(user, id));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam String id) {

        if (userService.getById(id).isEmpty()) {
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        userService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

    private String getBCryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
