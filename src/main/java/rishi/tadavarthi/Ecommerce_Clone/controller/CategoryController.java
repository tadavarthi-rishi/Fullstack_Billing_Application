package rishi.tadavarthi.Ecommerce_Clone.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.CategoryResponse;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @PostMapping
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){

    }

}
