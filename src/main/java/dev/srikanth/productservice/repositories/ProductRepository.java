package dev.srikanth.productservice.repositories;

import dev.srikanth.productservice.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitleEquals(String title);

    Product findByTitleEqualsAndPrice_Price(String title, double price);

    List<Product> findAllByPrice_Currency(String currency);

    @Query("select Product from Product where Product.price.currency= :currency and Product.title = :title")
    List<Product>  getValuesBasesOnCurrency(String title, String currency);

}
