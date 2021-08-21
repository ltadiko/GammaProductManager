package nl.intergamma.controllers;

import nl.intergamma.entities.Product;
import nl.intergamma.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(path = "/stores/{storeId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(@PathVariable String storeId) {
        return productService.getProductsByStore(storeId);
    }

    @PostMapping(path = "/stores/{storeId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getProducts(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping(path = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable String productId) {
        return productService.getProductByProductId(productId);
    }

    @DeleteMapping(path = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }


    @GetMapping(path = "/articles/{articleId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductsByArticle(@PathVariable String articleId) {
        return productService.getProductsByArticle(articleId);
    }

    @GetMapping(path = "/stores/{storeId}/products/artifacts/{artifactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductsByArticleAndStore(@PathVariable String artifactId,
                                                      @PathVariable String storeId) {
        return productService.getProductsByArticleAndStore(artifactId, storeId);

    }
}
