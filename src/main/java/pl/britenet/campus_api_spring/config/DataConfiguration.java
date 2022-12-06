package pl.britenet.campus_api_spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.campus_api.database.DatabaseService.DatabaseService;


import pl.britenet.campus_api.service.tableService.*;

@Configuration
public class DataConfiguration {

    private final DatabaseService databaseService;

    @Autowired
    public DataConfiguration(DatabaseService databaseService){
        this.databaseService = databaseService;
    }
    @Bean
    public ProductService getProductService(){
        return new ProductService(this.databaseService);
    }

    @Bean
    public UserService getUserService() { return  new UserService(this.databaseService); }

    @Bean
    public OrderProductService getOrderProductService() { return new OrderProductService(this.databaseService); }

    @Bean
    public OrderService getOrderService() { return new OrderService(this.databaseService); }

    @Bean
    public OpinionService getOpinionService() { return new OpinionService(this.databaseService); }

    @Bean
    public CategoryService getCategoryService() { return new CategoryService(this.databaseService); }

    @Bean
    public CartService getCartService() { return new CartService(this.databaseService); }
    @Bean
    public CartProductService getCartProductService() { return new CartProductService(this.databaseService); }
}
