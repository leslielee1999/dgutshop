package org.dgutstu.dgutshop.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 9:58 2020/12/12
 */

@SpringBootApplication(scanBasePackages = {"org.dgutstu.dgutshop.db"})
@MapperScan("org.dgutstu.dgutshop.db.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
