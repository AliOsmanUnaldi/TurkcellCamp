package com.turkcellIntelij.intelijturkcell.business.abstracts;

import com.turkcellIntelij.intelijturkcell.business.dtos.CategoryListDto;
import com.turkcellIntelij.intelijturkcell.business.requests.CreateCategoryRequest;
import com.turkcellIntelij.intelijturkcell.core.results.DataResult;
import com.turkcellIntelij.intelijturkcell.core.results.Result;

import java.util.List;

public interface CategoryService {
    DataResult<List<CategoryListDto>> getAll();

    Result add(CreateCategoryRequest createCategoryRequest);
}
