package np.prabin.repo;

import np.prabin.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String name);

    Optional<UserEntity> findByUserId(String userId);
}
