package com.practice.fullstackbackendspringboot.controller;

import com.practice.fullstackbackendspringboot.model.AllProductModel;
import com.practice.fullstackbackendspringboot.model.ProductModel;
import com.practice.fullstackbackendspringboot.model.response.AllProductsPageResponse;
import com.practice.fullstackbackendspringboot.service.ProductService;
import com.practice.fullstackbackendspringboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @PostMapping(value = {"/save"},  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductModel saveProduct( @RequestPart("product") @Valid ProductModel model,
                                     @RequestPart("file") MultipartFile file,
                                     @RequestHeader("Authorization") String email){
        String user = userService.getUserFromToken(email);
        return productService.saveProduct(model,user,file);
    }

//correct
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AllProductsPageResponse getAllProducts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "50", required = false) int pageSize){
        return productService.getAllProducts(pageNo,pageSize);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductModel getProductById(@PathVariable (value="productId") String productId){
        return productService.getProductById(productId);
    }
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<AllProductModel> searchProduct(@RequestParam (value = "keyword") String search ){
        return productService.searchProduct(search);
    }
    @DeleteMapping("/delete/{productId}")
    public void delete(@PathVariable (value="productId") String productId, @RequestHeader("Authorization") String email){
        String user = userService.getUserFromToken(email);
        productService.delete(productId, user);
    }

}
