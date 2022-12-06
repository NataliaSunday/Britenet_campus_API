package pl.britenet.campus_api_spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.Cart;
import pl.britenet.campus_api.service.tableService.CartService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")

public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }
    @GetMapping
    public List<Cart> getAllCarts(){
        return cartService.getCartAll();
    }

    @GetMapping("/{cartId}")
    public Cart getCartOne(@PathVariable int cartId){
        return this.cartService.getCartOne(cartId);
    }

    @PostMapping
    public Cart insertCart(@RequestBody Cart cart){
        this.cartService.insertCart(cart);
        return cart;
    }
    @DeleteMapping("/{cartId}")
    public void delCart(@PathVariable int cartId) {
        cartService.delCart(cartId);
    }
}
