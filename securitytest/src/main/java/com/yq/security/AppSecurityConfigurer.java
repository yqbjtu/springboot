package com.yq.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	/**
	 * 注入认证处理类，处理不同用户跳转到不同的页面
	 * */
	@Autowired
	AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;
	/**
	 * 用户授权操作 
	 * */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		System.out.println("AppSecurityConfigurer configure() HttpSecurity 调用......");
		http.authorizeRequests()
		// spring-security 5.0 之后需要过滤静态资源
		.antMatchers("/login","/css/**","/js/**","/img/*").permitAll() 
	  	.antMatchers("/", "/home").hasRole("USER")
	  	.antMatchers("/admin/**").hasAnyRole("ADMIN", "DBA")
	  	.anyRequest().authenticated()
	  	.and()
	  	.formLogin().loginPage("/login").successHandler(appAuthenticationSuccessHandler)
	  	.usernameParameter("loginName").passwordParameter("password")
	  	.and()
	  	.logout().permitAll()
	  	.and()
	  	.exceptionHandling().accessDeniedPage("/accessDenied");
    }

	/**
	 * 用户认证操作
	 * */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("AppSecurityConfigurer configureGlobal() 调用......");
    	// spring-security 5.0 之后需要密码编码器，否则会抛出异常：There is no PasswordEncoder mapped for the id "null"
    	auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("user1").password("userpw1").roles("USER");
       auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("admin").password("admin").roles("ADMIN","DBA");
    }

    
}
