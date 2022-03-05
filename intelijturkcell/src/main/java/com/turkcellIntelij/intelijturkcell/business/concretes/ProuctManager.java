package com.turkcellIntelij.intelijturkcell.business.concretes;

import com.turkcellIntelij.intelijturkcell.business.abstracts.ProductService;
import com.turkcellIntelij.intelijturkcell.business.dtos.ProductByNameDto;
import com.turkcellIntelij.intelijturkcell.business.dtos.ProductListDto;
import com.turkcellIntelij.intelijturkcell.business.requests.CreateProductRequest;
import com.turkcellIntelij.intelijturkcell.core.mapping.ModelMapperService;
import com.turkcellIntelij.intelijturkcell.core.results.*;
import com.turkcellIntelij.intelijturkcell.dataAccess.abstracts.ProductDao;
import com.turkcellIntelij.intelijturkcell.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProuctManager implements ProductService {

    private ProductDao productDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public ProuctManager(ProductDao productDao, ModelMapperService modelMapperService) {

        this.productDao = productDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ProductListDto>> getAll() {
        List<Product> result = this.productDao.findAll();
        List<ProductListDto> response = result.stream()
                .map(product -> this.modelMapperService.forDto().map(product, ProductListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ProductListDto>>(response, "Products listed successfully.");
    }

    @Override
    public Result add(CreateProductRequest createProductRequest) {
        Product product = this.modelMapperService.forRequest()
                .map(createProductRequest, Product.class);
        product.setProductId(0);
        this.productDao.save(product);
        return new SuccessResult("Product is added.");
    }

    @Override
    public DataResult<ProductByNameDto> getByProductName(String productName) {
        Product product = this.productDao.getByProductName(productName);

        if (product == null){
            return new ErrorDataResult<ProductByNameDto>(null,"Product is could not find!");
        }

        ProductByNameDto result = this.modelMapperService.forDto().map(product, ProductByNameDto.class);
        return new SuccessDataResult<ProductByNameDto>(result, "Product By Name Getted.");
    }

    @Override
    public DataResult<List<ProductListDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Product> result = this.productDao.findAll(pageable).getContent();
        List<ProductListDto> response = result.stream()
                .map(product -> this.modelMapperService.forDto().map(product, ProductListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ProductListDto>>(response,"Products are paged successfully.");
    }

    @Override
    public DataResult<List<ProductListDto>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC,"productName");
        List<Product> result = this.productDao.findAll(sort);
        List<ProductListDto> response = result.stream()
                .map(product -> this.modelMapperService.forDto().map(product, ProductListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ProductListDto>>(response,"Products are sorted successfully.");
    }
}
