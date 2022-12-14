package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.Category;
import pl.britenet.campus_api.service.tableService.CategoryService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private  final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) { this.categoryService = categoryService; }

    @GetMapping
    public List<Category> getCategoryAll(){
        return categoryService.getCategoryAll();
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryOne(@PathVariable int categoryId) {
        return this.categoryService.getCategoryOne(categoryId);
    }

    @PostMapping
    public Category insertCategory(@RequestBody Category category){
        this.categoryService.insertCategory(category);
        return category;
    }

    @PutMapping
    public void updateCategory( @RequestBody Category category ){
        this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/{categoryId}")
    public void delCategory(@PathVariable int categoryId) {
        categoryService.delCategory(categoryId);
    }


}
