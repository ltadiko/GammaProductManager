package nl.intergamma.repositories;

import nl.intergamma.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByStoreStoreId(String storeId);

    List<Product> findByArticleArticleId(String articleId);

    List<Product> findByArticleArticleIdAndStoreStoreId(String articleId, String storeId);

}
