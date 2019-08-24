package com.yq.security;



import com.yq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义Spring Security认证处理类的时候
 * 我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应 方法即可。 
 * */
@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter{

	// 依赖注入用户服务类
	@Autowired
    private UserService userService;
	
	// 依赖注入加密接口
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	// 依赖注入用户认证接口
	@Autowired
    private AuthenticationProvider authenticationProvider;
	
	// 依赖注入认证处理成功类，验证用户成功后处理不同用户跳转到不同的页面
	@Autowired
	AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;
	
	/*
	 *  BCryptPasswordEncoder是Spring Security提供的PasswordEncoder接口是实现类
	 *  用来创建密码的加密程序，避免明文存储密码到数据库
	 */
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 
	// DaoAuthenticationProvider是Spring Security提供AuthenticationProvider的实现
	@Bean
    public AuthenticationProvider authenticationProvider() {
		// 创建DaoAuthenticationProvider对象
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 不要隐藏"用户未找到"的异常
        provider.setHideUserNotFoundExceptions(false);
        // 通过重写configure方法添加自定义的认证方式。
        provider.setUserDetailsService(userService);
        // 设置密码加密程序认证
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("AppSecurityConfigurer configure auth......");
    	// 设置认证方式。
    	auth.authenticationProvider(authenticationProvider);

    }

    /**
     * 设置了登录页面，而且登录页面任何人都可以访问，然后设置了登录失败地址，也设置了注销请求，注销请求也是任何人都可以访问的。 
     * permitAll表示该请求任何人都可以访问，.anyRequest().authenticated(),表示其他的请求都必须要有权限认证。
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println("AppSecurityConfigurer configure http......");
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
		
}
