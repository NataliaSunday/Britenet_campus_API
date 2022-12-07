package pl.britenet.campus_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus_api.model.OrderProduct;
import pl.britenet.campus_api.service.tableService.OrderProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderProduct")
public class OrderProductController {
    private final OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService){
        this.orderProductService = orderProductService;
    }

    @GetMapping
    public List<OrderProduct> getOrderProductAll(){
        return this.orderProductService.getOrderProductAll();
    }

    @GetMapping("/{orderProductId}")
    public OrderProduct getOrderProductOne(@PathVariable int orderProductId){
        return this.orderProductService.getOrderProductOne(orderProductId);
    }

    @PostMapping
    public OrderProduct insertOrderProduct(@RequestBody OrderProduct orderProduct){
        this.orderProductService.insertOrderProduct(orderProduct);
        return  orderProduct;
    }

    @PutMapping
    public void updateOrderProduct( @RequestBody OrderProduct orderProduct ){
        this.orderProductService.updateOrderProduct(orderProduct);
    }

    @DeleteMapping("/{orderProductId}")
    public void delOrderProduct(@PathVariable int orderProductId) {
         orderProductService.delOrderProduct(orderProductId);
    }

}

