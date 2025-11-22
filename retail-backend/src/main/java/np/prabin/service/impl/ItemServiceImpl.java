package np.prabin.service.impl;

import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.ItemRequest;
import np.prabin.dto.response.ItemResponse;
import np.prabin.model.Category;
import np.prabin.model.Item;
import np.prabin.repo.CategoryRepository;
import np.prabin.repo.ItemRepository;
import np.prabin.service.FileUploadService;
import np.prabin.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;


    @Override
    public ItemResponse add(ItemRequest request, MultipartFile file) {
        String fileName = fileUploadService.uploadFile(file);
        Item item = convertToEntity(request);
        item.setImgUrl(fileName);
        Category category = categoryRepository.findByCategoryId(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        item.setCategory(category);
        Item savedItem = itemRepository.save(item);
        return convertToResponse(savedItem);
    }

    @Override
    public List<ItemResponse> fetchItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map((e) -> convertToResponse(e)).collect(Collectors.toList());
    }

    @Override
    public void deleteItems(String itemId) {
        Item item = itemRepository.findByItemId(itemId).orElseThrow(
                () -> new RuntimeException("item not found"));
        boolean isDeleted = fileUploadService.deleteFile(item.getImgUrl());
        if (isDeleted) {
            itemRepository.delete(item);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete");
        }
    }


    private ItemResponse convertToResponse(Item savedItem) {
        return ItemResponse.builder()
                .itemId(savedItem.getItemId())
                .name(savedItem.getName())
                .description(savedItem.getDescription())
                .price(savedItem.getPrice())
                .imgUrl(savedItem.getImgUrl())
                .categoryId(savedItem.getCategory().getCategoryId())
                .categoryName(savedItem.getCategory().getName())
                .createdAt(savedItem.getCreatedAt())
                .updatedAt(savedItem.getUpdatedAt())
                .build();
    }

    private Item convertToEntity(ItemRequest request) {
        return Item.builder()
                .itemId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }


}
