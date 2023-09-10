package dev.srikanth.productservice.service;

import dev.srikanth.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.srikanth.productservice.dto.GenericProductDto;
import dev.srikanth.productservice.exception.NotFoundException;
import dev.srikanth.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductIntoGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductIntoGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> data = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.getAllProducts()) {
            data.add(convertFakeStoreProductIntoGenericProductDto(fakeStoreProductDto));
        }
        return data;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto product, Long id) {
        return convertFakeStoreProductIntoGenericProductDto(fakeStoreProductServiceClient.updateProductById(product, id));
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return convertFakeStoreProductIntoGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
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
