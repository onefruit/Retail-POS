package np.prabin.service;

import np.prabin.dto.request.CategoryRequest;
import np.prabin.dto.response.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request, MultipartFile file);

    List<CategoryResponse> getAllCategory();

    CategoryResponse getCategory(Long id);

    void deleteCategory(String categoryId);

}
