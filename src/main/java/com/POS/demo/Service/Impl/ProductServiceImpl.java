package com.POS.demo.Service.Impl;

import com.POS.demo.PayLoad.Dto.ProductDto;
import com.POS.demo.Repositories.CategoryRepository;
import com.POS.demo.Repositories.ProductRepository;
import com.POS.demo.Repositories.StoreRepository;
import com.POS.demo.Service.ProductService;
import com.POS.demo.mapper.ProductMapper;
import com.POS.demo.modal.Category;
import com.POS.demo.modal.Product;
import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository  storeRepository;
    private final CategoryRepository  categoryRepository;

    @Override
    public ProductDto createdProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(
                productDto.getStoreId()
        ).orElseThrow(
                () -> new Exception("Store not found")
        );


        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                () -> new Exception("Category not found")
        );


        Product product = ProductMapper.toEntity(productDto , store  ,category);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long Id, ProductDto productDto, User user) throws Exception {
        Optional<Product> product = productRepository.findById(Id);
        if(product.isEmpty()){
            throw new Exception("Product not found");
        }
        Product product1 = product.get();
        if(productDto.getCategoryId()  !=  null){
            Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    () -> new Exception("Category not found")
            );
            if(category != null){
                product1.setCategory(category);
            }
        }

        product1.setName(productDto.getName());
        product1.setDescription(productDto.getDescription());
        product1.setSku(productDto.getSku());
        product1.setImage(productDto.getImage());
        product1.setMrp(productDto.getMrp());
        product1.setSellingprice(productDto.getSellingprice());
        product1.setBrand(productDto.getBrand());
        product1.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product1);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public void deleteProduct(Long Id, User user) throws Exception {
        Optional<Product> product = productRepository.findById(Id);
        if(product.isEmpty()){
            throw new Exception("Product not found");
        }
        productRepository.delete(product.get());
    }

    @Override
    public List<ProductDto> getProductByStoreId(Long storeId) {
        List<Product>  products = productRepository.findByStoreId(storeId);
        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyWord(Long storeId, String keyword) {
        List<Product>  products = productRepository.searchProducts(storeId ,keyword);
        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());

    }
}
