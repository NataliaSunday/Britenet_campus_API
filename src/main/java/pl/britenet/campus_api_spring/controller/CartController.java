package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.Cart;
import pl.britenet.campus_api.model.Order;
import pl.britenet.campus_api.service.tableService.CartService;
import pl.britenet.campus_api_spring.service.AuthService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/cart")

public class CartController {

    private final CartService cartService;

    private final AuthService authService;

    @Autowired
    public CartController(CartService cartService,  AuthService authService){
        this.cartService = cartService;
        this.authService = authService;
    }

    @GetMapping
    public List<Cart> getAllCarts(){
        return cartService.getCartAll();
    }

    @CrossOrigin
    @GetMapping("/cartUser")
    public Cart getCartUser(@RequestHeader ("Authorization") String token){
        int userId = this.authService.getUserId(token);

        Cart userCart = this.cartService.getCartUser(userId);
        System.out.println(userCart);
        if( userCart == null){
            Cart newCart = new Cart();
            newCart.setIdUser(userId);
            newCart.setDiscount(0.0);
            newCart.setTotalPrice(0.0);
            System.out.println(newCart);
            this.cartService.insertCart(newCart);
            return this.cartService.getCartUser(userId);
        }else{
            return this.cartService.getCartUser(userId);
        }

    }


    @PostMapping
    public Cart insertCart(@RequestBody Cart cart){
        this.cartService.insertCart(cart);
        return cart;
    }

    @PutMapping
    public void updateCart( @RequestBody Cart cart ){
        this.cartService.updateCart(cart);
    }

    @DeleteMapping("/{cartId}")
    public void delCart(@PathVariable int cartId) {
        cartService.delCart(cartId);
    }

}
