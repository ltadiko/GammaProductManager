package nl.intergamma.services;


import nl.intergamma.entities.Product;

import java.util.List;

public interface ProductService {

    /**
     * @param storeId : id of the store
     *                Service to retrieve all products from database
     * @return list of stores near to longitude and latitude
     */
    List<Product> getProductsByStore(String storeId);

    /**
     * @param articleId : id of the article
     *                  Service to retrieve all products from database
     * @return list of stores near to longitude and latitude
     */
    List<Product> getProductsByArticle(String articleId);

    /**
     * @param articleId : id of the article
     *                  Service to retrieve all products from database
     * @return list of stores near to longitude and latitude
     */
    List<Product> getProductsByArticleAndStore(String articleId, String storeId);

    /**
     * Service returns products data based on productId
     *
     * @param product product details
     * @return products data
     */
    void addProduct(Product product);


    /**
     * service is used to delete store from database
     *
     * @param productId unique id of the product
     */
    void deleteProduct(String productId);

    /**
     * @param productId id of the products
     * @return product
     */
    Product getProductByProductId(String productId);
}
