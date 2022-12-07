package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.service.tableService.CartProductService;
import pl.britenet.campus_api.model.CartProduct;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cartProduct")
public class CartProductController {

    private final CartProductService cartProductService;

    @Autowired
    public CartProductController(CartProductService cartProductService){
        this.cartProductService = cartProductService;
    }

    @GetMapping
    public List<pl.britenet.campus_api.model.CartProduct> getAllCartProducts(){
        return cartProductService.getCartProductAll();
    }

    @GetMapping("/{cartProductId}")
    public CartProduct getCartProductOne(@PathVariable int cartProductId){
        return this.cartProductService.getCartProductOne(cartProductId);
    }

    @PostMapping
    public CartProduct insertCartProduct(@RequestBody CartProduct cartProduct){
        this.cartProductService.insertCartProduct(cartProduct);
        return cartProduct;
    }

    @PutMapping
    public void updateCartProduct( @RequestBody CartProduct cartProduct ){
        this.cartProductService.updateCartProduct(cartProduct);
    }

    @DeleteMapping("/{orderCartProductId}")
    public void delCartProduct(@PathVariable int orderCartProductId) {
        cartProductService.delCartProduct(orderCartProductId);
    }
}
