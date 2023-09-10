package dev.srikanth.productservice.controller;

import dev.srikanth.productservice.dto.GenericProductDto;
import dev.srikanth.productservice.exception.NotFoundException;
import dev.srikanth.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController is an annotation.?
@RestController()
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // localhost:8080/products/123
    // 123 is mapped getProductsById is id parameter using @PathVariable annotation
    // RequestParam(?=) vs PathVariable

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }


    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto product, @PathVariable("id") Long id) {
        return productService.updateProductById(product, id);
    }


}
