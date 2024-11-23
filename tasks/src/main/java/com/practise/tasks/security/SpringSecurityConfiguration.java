package com.practise.tasks.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests( auth -> auth.anyRequest().permitAll())
		.formLogin(form -> form.loginPage("/").defaultSuccessUrl("/welcome", true));
			http.csrf(csrf -> csrf.disable())
			.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public InMemoryUserDetailsManager createUserDetailsManager() {
//		UserDetails user1 =  createNewUser("Karthik","Karthik123");
//		UserDetails user2 = createNewUser("Princy","Casey123");
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
	
//	private UserDetails createNewUser(String userName, String password) {
//		Function<String,String> passwordEncoder = input -> passwordEncoder().encode(input);
//		UserDetails userDetails = User.builder()
//				.passwordEncoder(passwordEncoder)
//				.username(userName).password(password)
//				.roles("USER","ADMIN").build();
//		return userDetails;
//	}
	
	
}
