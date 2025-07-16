package rishi.tadavarthi.Ecommerce_Clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rishi.tadavarthi.Ecommerce_Clone.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserId(String userId);



}
