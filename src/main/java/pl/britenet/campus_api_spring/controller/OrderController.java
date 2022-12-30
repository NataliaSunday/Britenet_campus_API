package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.CartProduct;
import pl.britenet.campus_api.model.Order;
import pl.britenet.campus_api.service.tableService.CartProductService;
import pl.britenet.campus_api.service.tableService.OrderService;
import pl.britenet.campus_api.service.tableService.ProductService;
import pl.britenet.campus_api_spring.service.AuthService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final AuthService authService;

    private final CartProductService cartProductService;
    @Autowired
    public OrderController(OrderService orderService, AuthService authService, CartProductService cartProductService){
        this.orderService = orderService;
        this.authService = authService;
        this.cartProductService = cartProductService;
    }

    @GetMapping
    public List<Order> getOrderAll(){
        return  orderService.getOrderAll();
    }

    @GetMapping("/{orderId}")
    public Order getOrderOne(@PathVariable int orderId){
        return this.orderService.getOrderOne(orderId);
    }
    @GetMapping("/userOrder")
    public  List<Order> getUserOrders(@RequestHeader ("Authorization") String token){
        int userId = this.authService.getUserId(token);
        return this.orderService.getUserOrders(userId);
    }

    @PostMapping
    public Order insertOrder(@RequestHeader ("Authorization") String token, @RequestHeader ("cartId") String cartId, @RequestBody Order order){
            int userId = this.authService.getUserId(token);
            List<CartProduct> cartProducts = this.cartProductService.getCartContentOfUser(userId);
        System.out.println(cartProducts);
          if(cartProducts.size()>0 ){
              order.setIdUser(userId);
              this.orderService.insertOrder(order);

              return  order;
          }else{
              throw new IllegalStateException();
          }

    }

    @PutMapping
    public void updateOrder( @RequestBody Order order ){
        this.orderService.updateOrder(order);
    }

    @DeleteMapping("/{orderId}")
    public void delOrder(@PathVariable int orderId) {
        orderService.delOrders(orderId);
    }

    @GetMapping("/getNewUserOrder")
    public Order getNewUserOrder(@RequestHeader ("Authorization") String token){
        int userId = this.authService.getUserId(token);
        return this.orderService.getNewUserOrder(userId);
    }
}
