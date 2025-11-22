package np.prabin.service;

import np.prabin.dto.request.ItemRequest;
import np.prabin.dto.response.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    ItemResponse add(ItemRequest request, MultipartFile file);
    List<ItemResponse> fetchItems();
    void deleteItems(String itemId);

}
