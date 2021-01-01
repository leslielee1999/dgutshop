package org.dgutstu.dgutshop.core.security.config;

import org.dgutstu.dgutshop.core.security.UserAuthenticationProvider;
import org.dgutstu.dgutshop.core.security.UserPermissionEvaluator;
import org.dgutstu.dgutshop.core.security.WxUserAuthenticationProvider;
import org.dgutstu.dgutshop.core.security.handler.*;
import org.dgutstu.dgutshop.core.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @Author: leesk
 * @Description: Spring Security的核心配置类
 * @Date: Create in 1:25 2020/12/21
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private JwtLoginSuccessHandler loginSuccessHandler;
    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private JwtLoginFailureHandler loginFailureHandler;
    /**
     * 自定义注销成功处理器
     */
    @Autowired
    private JwtLogoutSuccessHandler logoutSuccessHandler;
    /**
     * 自定义暂无权限处理器
     */
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    /**
     * 自定义未登录的处理器
     */
    @Autowired
    private JwtAuthenticationEntryPointHandler jwtAuthenticationEntryPointHandler;

    /**
     * 自定义登录逻辑验证器
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * 加密方式
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new UserPermissionEvaluator());
        return handler;
    }

    /**
     * 配置登录验证逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        //这里可启用自己写的登录验证逻辑
        auth.authenticationProvider(userAuthenticationProvider);
    }

        /**
         * 配置security的逻辑控制
         * @param httpSecurity
         * @throws Exception
         */
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .authorizeRequests()
                    // 不进行权限验证的请求或资源(从配置文件中读取)
                    .antMatchers(JwtSecurityProperties.antMatchers.split(",")).permitAll()
                    // 其他的需要登陆后才能访问
                    .anyRequest().authenticated()
                    .and()
                    // 配置未登录自定义处理类
                    .httpBasic().authenticationEntryPoint(jwtAuthenticationEntryPointHandler)
                    .and()
                    // 配置登录地址
                    .formLogin()
                    .loginProcessingUrl("/login/userLogin")
                    // 配置登录成功自定义处理类
                    .successHandler(loginSuccessHandler)
                    // 配置登录失败自定义处理类
                    .failureHandler(loginFailureHandler)
                    .and()
                    // 配置登出地址
                    .logout()
                    .logoutUrl("/login/userLogout")
                    // 配置用户登出自定义处理类
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .and()
                    // 配置没有权限自定义处理类
                    .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
                    .and()
                    // 开启跨域
                    .cors()
                    .and()
                    // 取消跨站请求伪造防护
                    .csrf().disable();
            ;
            // 禁用缓存
            httpSecurity.headers().cacheControl();
            // 添加JWT过滤器
            httpSecurity.addFilter(new JwtAuthenticationTokenFilter(authenticationManager()));
            // 基于Token不需要session
            httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

}
