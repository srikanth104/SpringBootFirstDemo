package dev.srikanth.productservice.service;

import dev.srikanth.productservice.dto.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto product, Long id) {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }
}
