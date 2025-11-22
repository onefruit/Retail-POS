package np.prabin.service.impl;

import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.AuthRequest;
import np.prabin.dto.response.AuthResponse;
import np.prabin.service.AppUserDetailsService;
import np.prabin.service.AuthService;
import np.prabin.service.UserService;
import np.prabin.utils.JwtHelper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService appUserDetailsService;
    private final JwtHelper helper;
    private final UserService userService;
    @Override
    public AuthResponse signup(AuthRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(AuthRequest request) throws  Exception {
        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(request.getEmail());
        String token = helper.generateToken(userDetails);
        String userRole = userService.getUserRole(request.getEmail());
        return new AuthResponse(request.getEmail(),token,userRole);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch (DisabledException e){
            throw new Exception("User Disabled");
        }catch (BadCredentialsException e){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "email or password is incorrect");
        }
    }
}