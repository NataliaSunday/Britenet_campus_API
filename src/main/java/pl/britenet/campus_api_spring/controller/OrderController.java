package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.Order;
import pl.britenet.campus_api.service.tableService.OrderService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrderAll(){
        return  orderService.getOrderAll();
    }

    @GetMapping("/{orderId}")
    public Order getOrderOne(@PathVariable int orderId){
        return this.orderService.getOrderOne(orderId);
    }

    @PostMapping
    public Order insertOrder(@RequestBody Order order){
        this.orderService.insertOrder(order);
        return  order;
    }

    @DeleteMapping("/{orderId}")
    public void delOrder(@PathVariable int orderId) {
        orderService.delOrders(orderId);
    }
}
