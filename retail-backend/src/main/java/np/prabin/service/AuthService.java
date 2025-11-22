package np.prabin.service;


import np.prabin.Exception.UserException;
import np.prabin.dto.request.AuthRequest;
import np.prabin.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse signup(AuthRequest request);
    AuthResponse login(AuthRequest request) throws  Exception;

}