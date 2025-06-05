package rishi.tadavarthi.Ecommerce_Clone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rishi.tadavarthi.Ecommerce_Clone.entity.CategoryEntity;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryResponse;
import rishi.tadavarthi.Ecommerce_Clone.repository.CategoryRepository;
import rishi.tadavarthi.Ecommerce_Clone.service.CategoryService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse add(CategoryRequest request) {
        CategoryEntity newCategory = convertToEntity(request);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);

    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
       return CategoryResponse.builder()
                .categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                .imgUrl(newCategory.getImgUrl())
                .createdAt(String.valueOf(newCategory.getCreatedAt()))
                .updatedAt(String.valueOf(newCategory.getUpdatedAt()))
                .build();
    }

    private CategoryEntity convertToEntity(CategoryRequest request) {

        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}

