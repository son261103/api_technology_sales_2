package com.example.api_sell_clothes.Repository;

import com.example.api_sell_clothes.Entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long> {
    Optional<Permissions> findByPermissionName(String permissionName);

    boolean existsByPermissionName(String permissionName);
}
