package rishi.tadavarthi.Ecommerce_Clone.service;

import rishi.tadavarthi.Ecommerce_Clone.io.CategoryRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryResponse;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request);
}
