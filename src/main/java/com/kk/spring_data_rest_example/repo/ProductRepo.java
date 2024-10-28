package com.kk.spring_data_rest_example.repo;

import com.kk.spring_data_rest_example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepo extends JpaRepository<Product, Integer> {
    /**
     * This method will search products by keyword in name, description, brand, or category.
     * Note that the :keyword is a named parameter, and it's the same as ?1.
     * The lower() and concat() functions are used to make the search case-insensitive.
     *
     * @param keyword the keyword to search for
     * @return a list of products that match the keyword
     */
    @Query("select p from Product p where " +
            "lower(p.name) like lower(concat('%', :keyword, '%')) or " +
            "lower(p.description) like lower(concat('%', :keyword, '%')) or " +
            "lower(p.brand) like lower(concat('%', :keyword, '%')) or " +
            "lower(p.category) like lower(concat('%', :keyword, '%'))"
    )
    List<Product> searchProducts(String keyword);
}