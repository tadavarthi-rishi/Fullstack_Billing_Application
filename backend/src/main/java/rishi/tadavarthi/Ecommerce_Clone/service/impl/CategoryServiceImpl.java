package rishi.tadavarthi.Ecommerce_Clone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rishi.tadavarthi.Ecommerce_Clone.entity.CategoryEntity;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryResponse;
import rishi.tadavarthi.Ecommerce_Clone.repository.CategoryRepository;
import rishi.tadavarthi.Ecommerce_Clone.service.CategoryService;
import rishi.tadavarthi.Ecommerce_Clone.service.FileUploadService;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;

    @Override
    public CategoryResponse add(CategoryRequest request, MultipartFile file) {
        String imgUrl = fileUploadService.uploadFile(file);
        CategoryEntity newCategory = convertToEntity(request);
        newCategory.setImgUrl(imgUrl);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);

    }

    @Override
    public List<CategoryResponse> read() {
       return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> convertToResponse(categoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
    CategoryEntity existingCategory = categoryRepository.findByCategoryId(categoryId)
            .orElseThrow(()-> new RuntimeException("Category not found: "+categoryId));
    fileUploadService.deleteFile(existingCategory.getImgUrl());

    categoryRepository.delete(existingCategory);
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

