package com.turkcellIntelij.intelijturkcell.api.controllers;

import com.turkcellIntelij.intelijturkcell.business.abstracts.ProductService;
import com.turkcellIntelij.intelijturkcell.business.dtos.ProductByNameDto;
import com.turkcellIntelij.intelijturkcell.business.dtos.ProductListDto;
import com.turkcellIntelij.intelijturkcell.business.requests.CreateProductRequest;
import com.turkcellIntelij.intelijturkcell.core.results.DataResult;
import com.turkcellIntelij.intelijturkcell.core.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall")
    public DataResult<List<ProductListDto>> getAll() {
        return this.productService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return this.productService.add(createProductRequest);
    }

    @GetMapping("/getByProductName")
    public DataResult<ProductByNameDto> getByProductName(String productName){
        return this.productService.getByProductName(productName);
    }

    @GetMapping("/getAllPaged")
    public DataResult<List<ProductListDto>> getAllPaged(int pageNo, int pageSize){
        return this.productService.getAllPaged(pageNo,pageSize);
    }

    @GetMapping("/getAllSorted")
    public DataResult<List<ProductListDto>> getAllSorted(){
        return this.productService.getAllSorted();
    }
}
