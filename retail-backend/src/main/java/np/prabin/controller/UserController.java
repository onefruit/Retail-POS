package np.prabin.controller;

import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.UserRequest;
import np.prabin.dto.response.UserResponse;
import np.prabin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse user = userService.createUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> readUsers() {
        List<UserResponse> userResponses = userService.readUsers();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity readUsers(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
