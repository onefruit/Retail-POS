package np.prabin.repo;

import np.prabin.dto.response.ItemResponse;
import np.prabin.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByItemId(String id);

    Integer countByCategoryId(Long id);
}
