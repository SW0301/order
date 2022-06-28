package com.order2;

import com.order2.model.Order;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MappedTypes(Order.class)
@MapperScan("com.order2.repository")
@SpringBootApplication
//@EnableSwagger2
public class Order2Application {

    public static void main(String[] args) {
        SpringApplication.run(Order2Application.class, args);
    }

}
