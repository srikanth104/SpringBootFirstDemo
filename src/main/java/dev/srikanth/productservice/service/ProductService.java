package dev.srikanth.productservice.service;

import dev.srikanth.productservice.dto.GenericProductDto;
import dev.srikanth.productservice.exception.NotFoundException;

import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto updateProductById(GenericProductDto product, Long id);

    GenericProductDto deleteProductById(Long id);
}
