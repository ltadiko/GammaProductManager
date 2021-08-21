package nl.intergamma.services;


import nl.intergamma.domain.exception.ProductNotFoundException;
import nl.intergamma.entities.Product;
import nl.intergamma.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductsByStore(String storeId) {
        return productRepository.findByStoreStoreId(storeId);
    }

    @Override
    public List<Product> getProductsByArticle(String articleId) {
        return productRepository.findByArticleArticleId(articleId);
    }

    @Override
    public List<Product> getProductsByArticleAndStore(String articleId, String storeId) {
        return productRepository.findByArticleArticleIdAndStoreStoreId(articleId, storeId);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product getProductByProductId(String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product Id" + productId + " not found"));
    }
}
