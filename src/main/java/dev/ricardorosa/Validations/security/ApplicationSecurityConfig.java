package dev.ricardorosa.Validations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails defaultUser = User.builder()
				.username("user1")
				.password(passwordEncoder.encode("123"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("123456"))
				.roles("ADMIN")
				.build();				
		
		return new InMemoryUserDetailsManager(defaultUser, admin);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/h2-console/**").permitAll()
				.antMatchers("/email").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().headers().frameOptions().sameOrigin()
			.and().csrf().ignoringAntMatchers("/h2-console/**")			
			.and().formLogin()
			.and().exceptionHandling().accessDeniedPage("/forbidden.html");
	}
}
