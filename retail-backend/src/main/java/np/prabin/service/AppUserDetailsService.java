package np.prabin.service;


import lombok.RequiredArgsConstructor;
import np.prabin.model.UserEntity;
import np.prabin.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity profile = profileRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Profile not found with email: " + email));

        return User.builder()
                .username(profile.getEmail())
                .password(profile.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(profile.getRole())))
                .build();

    }
}