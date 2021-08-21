package nl.intergamma.service;

import info.solidsoft.mockito.java8.api.WithBDDMockito;
import nl.intergamma.domain.exception.ProductNotFoundException;
import nl.intergamma.entities.Product;
import nl.intergamma.repositories.ProductRepository;
import nl.intergamma.services.ProductService;
import nl.intergamma.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest implements WithBDDMockito {

    @Mock
    private ProductRepository productRepository;
    private ProductService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("Should return list of products")
    void getProductData() {
        // given
        Product product = new Product("10", "name", "description", "1", "1");
        List<Product> products = Collections.singletonList(product);
        given(productRepository.findByStoreStoreId("1")).willReturn(Collections.singletonList(product));

        // when
        List<Product> result = underTest.getProductsByStore("1");

        // then
        assertEquals(result, products);
        verify(productRepository).findByStoreStoreId("1");
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    @DisplayName("Should pass the unhandled exception")
    void getProductData_failure() {
        // given
        given(productRepository.findByStoreStoreId("1")).willThrow(RuntimeException.class);

        // when
        assertThrows(RuntimeException.class, () -> underTest.getProductsByStore("1"));

        // then
        verify(productRepository).findByStoreStoreId("1");
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    @DisplayName("Should return product based on product id WHEN uuid is exist in repository")
    void getProduct_success() {
        // given
        Product product = new Product("10", "name", "description", "1", "1");
        given(productRepository.findById("id")).willReturn(Optional.of(product));

        // when
        Product result = underTest.getProductByProductId("id");

        // then
        assertEquals(result, product);
        verify(productRepository).findById("id");
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    @DisplayName("Should throw product not found exception WHEN product id is not exist in repository")
    void getStore_when_not_found() {
        // given
        given(productRepository.findById("id")).willReturn(Optional.empty());

        // when
        assertThrows(ProductNotFoundException.class, () -> underTest.getProductByProductId("id"));

        // then
        verify(productRepository).findById("id");
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    @DisplayName("Should add product to repository")
    void addProduct() {
        // given
        Product product = new Product("10", "name", "description", "1", "1");

        // when
        underTest.addProduct(product);
        // then
        verify(productRepository).save(product);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    @DisplayName("Should delete product from repository")
    void deleteProduct() {
        // given

        // when
        underTest.deleteProduct("id");

        // then
        verify(productRepository).deleteById("id");
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    @DisplayName("Should return list of products based on articleid")
    void getProductDataByArticle() {
        // given
        Product product = new Product("10", "name", "description", "1", "1");
        List<Product> products = Collections.singletonList(product);
        given(productRepository.findByArticleArticleId("1")).willReturn(Collections.singletonList(product));

        // when
        List<Product> result = underTest.getProductsByArticle("1");

        // then
        assertEquals(result, products);
        verify(productRepository).findByArticleArticleId("1");
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    @DisplayName("Should return list of products based on articleid and store id")
    void getProductDataByArticleAndStoreId() {
        // given
        Product product = new Product("10", "name", "description", "1", "1");
        List<Product> products = Collections.singletonList(product);
        given(productRepository.findByArticleArticleIdAndStoreStoreId("1", "1")).willReturn(Collections.singletonList(product));

        // when
        List<Product> result = underTest.getProductsByArticleAndStore("1", "1");

        // then
        assertEquals(result, products);
        verify(productRepository).findByArticleArticleIdAndStoreStoreId("1", "1");
        verifyNoMoreInteractions(productRepository);
    }

}
