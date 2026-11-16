package com.tasgroup.spring_course.controller;

import com.tasgroup.spring_course.controller.model.ProductRequest;
import com.tasgroup.spring_course.controller.model.ProductResponse;
import com.tasgroup.spring_course.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/products")
public class ProductController {
    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {


        return service.create(request);
    }


    @GetMapping
    public List<ProductResponse> findAll() {
        return service.findAll();
    }


    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable String id) {
        return service.findById(id);
    }


    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable String id,
            @Valid @RequestBody ProductRequest request) {


        return service.update(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }


    @GetMapping("/category/{category}")
    public List<ProductResponse> findByCategory(@PathVariable String category) {


        return service.findByCategory(category);
    }


    @GetMapping("/search")
    public List<ProductResponse> searchByName(
            @RequestParam String name) {


        return service.searchByName(name);
    }
}
