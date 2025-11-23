package np.prabin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.ItemRequest;
import np.prabin.dto.response.ItemResponse;
import np.prabin.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final    ItemService itemService;

    @PostMapping("/admin/items")
    public ResponseEntity<ItemResponse> addItem(
            @RequestPart("item") String itemString,
            @RequestPart("file") MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ItemRequest request = objectMapper.readValue(itemString, ItemRequest.class);

            ItemResponse response = itemService.add(request, file);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Error while parsing item JSON: " + e.getMessage()
            );
        }
    }

    @GetMapping("/admin/items")
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        List<ItemResponse> items = itemService.fetchItems();
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/admin/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable String itemId) {
        itemService.deleteItems(itemId);
        return ResponseEntity.noContent().build();
    }
}
