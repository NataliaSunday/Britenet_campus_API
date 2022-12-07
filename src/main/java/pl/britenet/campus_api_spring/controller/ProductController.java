package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.Product;
import pl.britenet.campus_api.service.tableService.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getProductAll();
    }

    @GetMapping("/{productId}")
    public Product getProductOne(@PathVariable int productId){
        return this.productService.getProductOne(productId);
    }

    @PostMapping
    public Product insertProduct(@RequestBody Product product){
       this.productService.insertProduct(product);
       return product;
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product ){
        this.productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void delProduct(@PathVariable int productId){
        this.productService.delProduct(productId);
    }

}
//deleteee