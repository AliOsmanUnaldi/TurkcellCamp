package com.turkcellIntelij.intelijturkcell.business.concretes;

import com.turkcellIntelij.intelijturkcell.business.abstracts.CategoryService;
import com.turkcellIntelij.intelijturkcell.business.dtos.CategoryListDto;
import com.turkcellIntelij.intelijturkcell.business.requests.CreateCategoryRequest;
import com.turkcellIntelij.intelijturkcell.core.mapping.ModelMapperService;
import com.turkcellIntelij.intelijturkcell.core.results.DataResult;
import com.turkcellIntelij.intelijturkcell.core.results.Result;
import com.turkcellIntelij.intelijturkcell.core.results.SuccessDataResult;
import com.turkcellIntelij.intelijturkcell.core.results.SuccessResult;
import com.turkcellIntelij.intelijturkcell.dataAccess.abstracts.CategoryDao;
import com.turkcellIntelij.intelijturkcell.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {

    private CategoryDao categoryDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CategoryManager(CategoryDao categoryDao, ModelMapperService modelMapperService) {
        this.categoryDao = categoryDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CategoryListDto>> getAll() {

        List<Category> result = this.categoryDao.findAll();

        List<CategoryListDto> response = result.stream()
                .map(category -> this.modelMapperService.forDto().map(category, CategoryListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<CategoryListDto>>(response,"Categories lists successfully.");
    }

    @Override
    public Result add(CreateCategoryRequest createCategoryRequest) {
        Category category = this.modelMapperService.forRequest()
                .map(createCategoryRequest, Category.class);
        this.categoryDao.save(category);
        return new SuccessResult("Category is added.");
    }

}
