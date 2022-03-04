package com.turkcellIntelij.intelijturkcell.business.abstracts;

import com.turkcellIntelij.intelijturkcell.business.dtos.ProductByNameDto;
import com.turkcellIntelij.intelijturkcell.business.dtos.ProductListDto;
import com.turkcellIntelij.intelijturkcell.business.requests.CreateProductRequest;
import com.turkcellIntelij.intelijturkcell.core.results.DataResult;
import com.turkcellIntelij.intelijturkcell.core.results.Result;
import com.turkcellIntelij.intelijturkcell.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    DataResult<List<ProductListDto>> getAll();

    Result add(CreateProductRequest createProductRequest);

    DataResult<ProductByNameDto> getByProductName(String productName);

    DataResult<List<ProductListDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<ProductListDto>> getAllSorted();
}
