package np.prabin.service.impl;

import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.CategoryRequest;
import np.prabin.dto.response.CategoryResponse;
import np.prabin.model.Category;
import np.prabin.repo.CategoryRepository;
import np.prabin.service.CategoryService;
import np.prabin.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse add(CategoryRequest request, MultipartFile file) {
        String imgurl = fileUploadService.uploadFile(file);
        Category category = convertToEntity(request);
        category.setImgUrl(imgurl);
        Category savedCat = categoryRepository.save(category);
        return convertToResponse(savedCat);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAll().stream().map(
                e -> convertToResponse(e)).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Category with this id " + id + " doesn't exists"));
        return convertToResponse(category);
    }

    @Override
    public void deleteCategory(String  categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId).orElseThrow(()-> new RuntimeException("Category not found"));
        fileUploadService.deleteFile(category.getImgUrl());
        categoryRepository.delete(category);
    }

    private Category convertToEntity(CategoryRequest request) {
        return Category.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .bgColor(request.getBgColor())
                .description(request.getDescription())
                .build();
    }

    private CategoryResponse convertToResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .bgColor(category.getBgColor())
                .imgUrl(category.getImgUrl())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

}
