package com.usc.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.usc.handler.AccessDeniedHandlerImpl;
import com.usc.handler.AuthenticationEntryPointImpl;
import com.usc.handler.AuthenticationFailureHandlerImpl;
import com.usc.handler.AuthenticationSuccessHandlerImpl;
import com.usc.handler.LogoutSuccessHandlerImpl;


//@EnableWebSecurity
//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
//	@Autowired 
//	CustomerServiceImpl customerServiceImpl;
//	
//
//	@Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests()
//		  .requestMatchers("/auth/**").permitAll()
//		  .requestMatchers("/checkout/**").permitAll()
//		  .requestMatchers("/products/**").permitAll()
//		  .anyRequest().authenticated();
//		
//        return http.build();
//    }
//	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration ) 
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder(11);
//		return encoder;
//	}
	@Autowired
	AuthenticationEntryPointImpl authenticationEntryPointImpl;

	@Autowired
	AccessDeniedHandlerImpl accessDeniedHandlerImpl;

	@Autowired
	AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;
	
	@Autowired
	AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;
	

	
	@Autowired
	LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//cross site request forgery
		http.csrf().disable().cors() // cors config.
													// anyone permits to browse those websites. 
				.and().authorizeRequests().antMatchers("/index.html", "/products", "/products/*").permitAll().and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl).and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandlerImpl).and().formLogin().permitAll().loginProcessingUrl("/login")
				.successHandler(authenticationSuccessHandlerImpl).failureHandler(authenticationFailureHandlerImpl)
				.usernameParameter("username").passwordParameter("password").and().logout().permitAll()
				.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandlerImpl)
//				.invalidateHttpSession(true).deleteCookies("auth_code", "JSESSIONID")
				.and().rememberMe();
	}

	/*
	 * cors support
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:4200"); // You should only set trusted site here.
																	// localhost:4200
//        e.g. http://localhost:4200 means only this site can access.
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder(11);
		return encoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
 }
