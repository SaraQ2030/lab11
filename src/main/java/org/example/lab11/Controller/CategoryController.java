package org.example.lab11.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.lab11.API.ApiResponse;
import org.example.lab11.Model.Category;
import org.example.lab11.Model.User;
import org.example.lab11.Service.CategoryService;
import org.example.lab11.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/v1/blog/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategory(){
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added "));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.updateCategory(id,category);
        return ResponseEntity.status(200).body(new ApiResponse("Category Updated "));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted"));
    }
    @GetMapping("/search/{name}")
    public ResponseEntity searchByName(@PathVariable String name){
        Category c=categoryService.searchByName(name);
        return ResponseEntity.status(200).body(c);

    }
}
