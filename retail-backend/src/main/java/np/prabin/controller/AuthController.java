package np.prabin.controller;

import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.AuthRequest;
import np.prabin.dto.response.AuthResponse;
import np.prabin.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //
//    @PostMapping("/signup")
//    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto) throws UserException {
//        return ResponseEntity.ok(authService.signup(userDto));
//    }
//
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody AuthRequest request) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }

}