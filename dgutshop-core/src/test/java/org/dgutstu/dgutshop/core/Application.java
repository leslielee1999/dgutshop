package org.dgutstu.dgutshop.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.dgutstu.dgutshop.db", "org.dgutstu.dgutshop.core"})
@MapperScan("org.dgutstu.dgutshop.db.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}