package dev.srikanth.productservice.thirdpartyclients.productservice.fakestore;

import dev.srikanth.productservice.dto.GenericProductDto;
import dev.srikanth.productservice.exception.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreProductServiceClient {


    private final RestTemplateBuilder restTemplateBuilder;

    private final String specificRequestUrl = "https://fakestoreapi.com/products/{id}";

    private final String baseRequestUrl = "https://fakestoreapi.com/products/";


    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // We create DTOs for incoming objects/ sending objects
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificRequestUrl, FakeStoreProductDto.class, id);

        if (response.getBody() == null) {
            throw new NotFoundException("Product with id:" + id + " doesn't exist");
        }

        return response.getBody();
    }


    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(baseRequestUrl, product, FakeStoreProductDto.class);
        return response.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(baseRequestUrl, FakeStoreProductDto[].class);
        return Arrays.stream(Objects.requireNonNull(response.getBody())).toList();
    }


    public FakeStoreProductDto updateProductById(GenericProductDto product, Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        // We create DTOs for incoming objects/ sending objects
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        return Objects.requireNonNull(response).getBody();
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return Objects.requireNonNull(response).getBody();
    }

}