package com.tech.health.config;

import com.tech.health.handler.LoginFailureHandler;
import com.tech.health.handler.LoginSuccessHandler;
import com.tech.health.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther: wdd
 * @Date: 2019/12/29 14:12
 * @Description:
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService adminService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//.addFilterAfter(dynamicallyUrlInterceptor(), FilterSecurityInterceptor.class)
                .authorizeRequests()
                .antMatchers("/manager/login").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/mystatic/**","/layuiadmin/**","/font-awesome-4.7.0/**").permitAll()
                //.antMatchers("/**")
                //.fullyAuthenticated()
                .anyRequest().access("@rbacConfig.hasPermission(request,authentication)")
                .and()
                .formLogin().loginPage("/manager/login").successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
                .and()
                .headers().frameOptions().disable() // ?????????Refused to display in a frame because it set 'X-Frame-Options' to 'DENY'??????
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService)
                .passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public DynamicallyUrlInterceptor dynamicallyUrlInterceptor(){
//        DynamicallyUrlInterceptor interceptor = new DynamicallyUrlInterceptor();
//        interceptor.setSecurityMetadataSource(new MyFilterSecurityMetadataSource());
//
//        //??????RoleVoter??????
//        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
//        decisionVoters.add(new RoleVoter());
//        //???????????????????????????
//        interceptor.setAccessDecisionManager(new DynamicallyUrlAccessDecisionManager(decisionVoters));
//        return interceptor;
//    }
}
