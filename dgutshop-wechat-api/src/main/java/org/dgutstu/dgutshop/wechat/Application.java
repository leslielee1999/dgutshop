package org.dgutstu.dgutshop.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 19:29 2020/12/17
 */
@SpringBootApplication(scanBasePackages = {"org.dgutstu.dgutshop.db",
        "org.dgutstu.dgutshop.core", "org.dgutstu.dgutshop.wechat"})
@MapperScan("org.dgutstu.dgutshop.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
