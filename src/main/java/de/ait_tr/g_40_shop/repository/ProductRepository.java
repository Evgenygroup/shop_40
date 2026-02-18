package de.ait_tr.g_40_shop.repository;

import de.ait_tr.g_40_shop.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //My edition 09.04.25
    Optional<Product> findProductByTitle(String title);

    @Override
    void delete(Product entity);
}
