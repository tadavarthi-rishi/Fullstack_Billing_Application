package rishi.tadavarthi.Ecommerce_Clone.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rishi.tadavarthi.Ecommerce_Clone.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByOrderId(String orderId);
    List<OrderEntity> findAllByOrderByCreatedAtDesc();

}
