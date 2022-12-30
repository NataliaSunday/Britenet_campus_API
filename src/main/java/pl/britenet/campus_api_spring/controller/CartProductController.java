package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.Order;
import pl.britenet.campus_api.model.OrderProduct;
import pl.britenet.campus_api.service.tableService.CartProductService;
import pl.britenet.campus_api.model.CartProduct;
import pl.britenet.campus_api.service.tableService.OrderProductService;
import pl.britenet.campus_api.service.tableService.OrderService;
import pl.britenet.campus_api_spring.service.AuthService;

import java.sql.SQLException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/cartProduct")
public class CartProductController {

    private final CartProductService cartProductService;
    private final AuthService authService;
    private final OrderProductService orderProductService;

    private  final OrderService orderService;

    @Autowired
    public CartProductController(CartProductService cartProductService, AuthService authService, OrderProductService orderProductService, OrderService orderService){
        this.cartProductService = cartProductService;
        this.authService = authService;
        this.orderProductService = orderProductService;
        this.orderService = orderService;
    }

    @GetMapping
    public List<CartProduct> getAllCartProducts(){
        return cartProductService.getCartProductAll();
    }

    @GetMapping("/{cartProductId}")
    public CartProduct getCartProductOne(@PathVariable int cartProductId){
        return this.cartProductService.getCartProductOne(cartProductId);
    }

    @PostMapping
    public CartProduct insertCartProduct(@RequestHeader ("Authorization") String token, @RequestBody CartProduct cartProduct){

        this.cartProductService.insertCartProduct(cartProduct);
        return cartProduct;
    }

    @PutMapping
    public void updateCartProduct( @RequestBody CartProduct cartProduct ){
        this.cartProductService.updateCartProduct(cartProduct);
    }

    @DeleteMapping("/{cartProductId}")
    public void delCartProduct(@PathVariable int cartProductId) {
        cartProductService.delCartProduct(cartProductId);
    }

    @CrossOrigin
    @GetMapping("/userCart")
    public List<CartProduct> getCartContentOfUser(@RequestHeader ("Authorization") String token){
       int userId = this.authService.getUserId(token);
        return cartProductService.getCartContentOfUser(userId);
    }

    @PostMapping("/addCartProductToUserProduct")
    public List<CartProduct> getCartProductToOrderProduct(@RequestHeader ("Authorization") String token){
        int userId = this.authService.getUserId(token);
        Order userOrder = this.orderService.getNewUserOrder(userId);

        List<CartProduct> cartProducts = this.cartProductService.getCartContentOfUser(userId);

        System.out.println(cartProducts);
        int i = 0;
        while(i < cartProducts.size()){
            OrderProduct orderProduct = new OrderProduct();
            int idProduct = cartProducts.get(i).getIdProduct();
            orderProduct.setIdProduct(idProduct);
            orderProduct.setIdOrder(userOrder.getIdOrder());
            int howMany = cartProducts.get(i).getHowMany();
            orderProduct.setHowMany(howMany);
            double price = cartProducts.get(i).getPrice();
            orderProduct.setPrice(price);

            orderProductService.insertOrderProduct(orderProduct);
            i++;
        }

        this.cartProductService.delUserCartProducts(userId);
        return cartProducts;

    }
}
