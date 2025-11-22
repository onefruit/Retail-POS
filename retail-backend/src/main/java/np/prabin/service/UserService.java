package np.prabin.service;

import np.prabin.dto.request.UserRequest;
import np.prabin.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> readUsers();
    void deleteUser(String id);
}
