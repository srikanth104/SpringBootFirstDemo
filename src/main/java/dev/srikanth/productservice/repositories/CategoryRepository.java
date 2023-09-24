package dev.srikanth.productservice.repositories;

import dev.srikanth.productservice.model.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Lazy
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
