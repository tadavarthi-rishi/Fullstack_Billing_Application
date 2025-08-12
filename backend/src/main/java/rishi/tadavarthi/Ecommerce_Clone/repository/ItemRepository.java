package rishi.tadavarthi.Ecommerce_Clone.repository;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import rishi.tadavarthi.Ecommerce_Clone.entity.ItemEntity;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByItemId(String id);
    Integer countByCategoryId(Long id);


}
