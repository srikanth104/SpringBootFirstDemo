package dev.srikanth.productservice.service;

import dev.srikanth.productservice.dto.FakeStoreProductDto;
import dev.srikanth.productservice.dto.GenericProductDto;
import dev.srikanth.productservice.exception.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private String specificRequestUrl = "https://fakestoreapi.com/products/{id}";

    private String baseRequestUrl = "https://fakestoreapi.com/products/";


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // We create DTOs for incoming objects/ sending objects
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificRequestUrl, FakeStoreProductDto.class, id);

        if(response.getBody() == null) {
            throw new NotFoundException("Product with id:" + id + " doesn't exist");
        }

        return convertFakeStoreProductIntoGenericProductDto(response.getBody());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(baseRequestUrl, product, GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(baseRequestUrl, FakeStoreProductDto[].class);

        List<GenericProductDto> output = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto : Arrays.stream(Objects.requireNonNull(response.getBody())).toList()) {
            output.add(convertFakeStoreProductIntoGenericProductDto(fakeStoreProductDto));
        }

        return output;
    }

    /*private static List<GenericProductDto> getProductDtos(ResponseEntity<ArrayList> response) {
        List<GenericProductDto> output = new ArrayList<>();

        List<FakeStoreProductDto>  data = (List<FakeStoreProductDto>) Objects.requireNonNull(response.getBody());

        for (FakeStoreProductDto fakeStoreProductDto : data ) {
            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setImage(fakeStoreProductDto.getImage());
            genericProductDto.setTitle(fakeStoreProductDto.getTitle());
            genericProductDto.setPrice(fakeStoreProductDto.getPrice());
            genericProductDto.setDescription(fakeStoreProductDto.getDescription());
            genericProductDto.setCategory(fakeStoreProductDto.getCategory());
            output.add(genericProductDto);

        }
        return output;
    }*/

    @Override
    public GenericProductDto updateProductById(GenericProductDto product, Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        // We create DTOs for incoming objects/ sending objects

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificRequestUrl, HttpMethod.PUT,requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto =  response.getBody();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setPrice(fakeStoreProductDto.getPrice());

        return product;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return convertFakeStoreProductIntoGenericProductDto(response.getBody());
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProductDto(FakeStoreProductDto body) {
        FakeStoreProductDto productDto = body;
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(productDto.getId());
        genericProductDto.setImage(productDto.getImage());
        genericProductDto.setTitle(productDto.getTitle());
        genericProductDto.setPrice(productDto.getPrice());
        genericProductDto.setDescription(productDto.getDescription());
        genericProductDto.setCategory(productDto.getCategory());
        return genericProductDto;

    }


}
