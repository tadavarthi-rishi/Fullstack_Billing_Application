package rishi.tadavarthi.Ecommerce_Clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryResponse;
import rishi.tadavarthi.Ecommerce_Clone.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){

        return categoryService.add(request);
    }

    @GetMapping
    public List<CategoryResponse> fetchCategories() {
        return categoryService.read();

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{categoryId}")
    public void remove(@PathVariable String categoryId){

        try{
            categoryService.delete(categoryId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        }

}
