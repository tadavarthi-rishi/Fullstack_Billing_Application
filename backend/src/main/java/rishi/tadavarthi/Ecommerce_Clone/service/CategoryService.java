package rishi.tadavarthi.Ecommerce_Clone.service;

import org.springframework.web.multipart.MultipartFile;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request, MultipartFile file);
    List<CategoryResponse> read();

    void delete(String categoryId);

}
