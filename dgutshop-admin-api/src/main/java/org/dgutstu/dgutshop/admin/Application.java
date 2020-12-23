package org.dgutstu.dgutshop.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 20:25 2020/12/12
 */
@SpringBootApplication(scanBasePackages = {"org.dgutstu.dgutshop.db",
                    "org.dgutstu.dgutshop.core", "org.dgutstu.dgutshop.admin"})
@MapperScan("org.dgutstu.dgutshop.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //  让重复执行的filter实现一次执行过程的解决方案
//    @Bean
//    public FilterRegistrationBean registration(JwtAuthenticationTokenFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }
}
