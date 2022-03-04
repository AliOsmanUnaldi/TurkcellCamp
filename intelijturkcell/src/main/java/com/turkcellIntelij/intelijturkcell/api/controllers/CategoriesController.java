package com.turkcellIntelij.intelijturkcell.api.controllers;

import com.turkcellIntelij.intelijturkcell.business.abstracts.CategoryService;
import com.turkcellIntelij.intelijturkcell.business.dtos.CategoryListDto;
import com.turkcellIntelij.intelijturkcell.business.requests.CreateCategoryRequest;
import com.turkcellIntelij.intelijturkcell.core.results.DataResult;
import com.turkcellIntelij.intelijturkcell.core.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall")
    public DataResult<List<CategoryListDto>> getAll() {

        return this.categoryService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return this.categoryService.add(createCategoryRequest);
    }
}
