package com.POS.demo.mapper;


import com.POS.demo.PayLoad.Dto.CategoryDto;
import com.POS.demo.PayLoad.Dto.ProductDto;
import com.POS.demo.modal.Category;
import com.POS.demo.modal.Product;
import com.POS.demo.modal.Store;

public class ProductMapper {

    public static ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .mrp(product.getMrp())
                .categoryDto(CategoryMapper.toDto(product.getCategory()))
                .sellingprice(product.getSellingprice())
                .brand(product.getBrand())
                .storeId(product.getStore()!=null?product.getStore().getId():null)
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static Product toEntity(ProductDto productDto , Store store , Category  category){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setStore(store);
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setMrp(productDto.getMrp());
        product.setSellingprice(productDto.getSellingprice());
        product.setBrand(productDto.getBrand());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());
        return product;
    }
}
