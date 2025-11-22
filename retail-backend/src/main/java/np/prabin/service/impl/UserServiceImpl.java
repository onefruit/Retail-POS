package np.prabin.service.impl;

import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.UserRequest;
import np.prabin.dto.response.UserResponse;
import np.prabin.model.UserEntity;
import np.prabin.repo.UserRepository;
import np.prabin.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserRequest request) {
        UserEntity user = convertToEntity(request);
        passwordEncoder.encode(request.getPassword());
        UserEntity savedUser = userRepository.save(user);
        UserResponse userResponse = convertToResponse(savedUser);
        return userResponse;
    }

    private UserEntity convertToEntity(UserRequest request) {
        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())  // generate unique user id
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toUpperCase())
                .name(request.getName())
                .build();
    }


    private UserResponse convertToResponse(UserEntity entity) {
        return UserResponse.builder()
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .role(entity.getRole())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }


    @Override
    public String getUserRole(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.getRole();

    }

    @Override
    public List<UserResponse> readUsers() {
        List<UserEntity> all = userRepository.findAll();
        return all.stream().map(e -> convertToResponse(e)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(userRepository.findByUserId(id).orElseThrow(()->new UsernameNotFoundException("user this id doesn't exists")));
    }
}
