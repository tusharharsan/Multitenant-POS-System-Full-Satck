package com.POS.demo.Service;

import com.POS.demo.PayLoad.Dto.ProductDto;
import com.POS.demo.modal.User;

import java.util.List;

public interface ProductService {

    ProductDto createdProduct(ProductDto productDto , User user) throws Exception;
    ProductDto updateProduct(Long Id , ProductDto productDto , User user) throws Exception;
    void deleteProduct(Long Id , User user) throws Exception;
    List<ProductDto> getProductByStoreId(Long storeId);
    List<ProductDto> searchByKeyWord(Long storeId , String keyword);



}
