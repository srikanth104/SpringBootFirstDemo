package dev.srikanth.productservice.repositories;

import dev.srikanth.productservice.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {

}
