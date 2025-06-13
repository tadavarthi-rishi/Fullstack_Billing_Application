package rishi.tadavarthi.Ecommerce_Clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rishi.tadavarthi.Ecommerce_Clone.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategoryId(String categoryId);
}
