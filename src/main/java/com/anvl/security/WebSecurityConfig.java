/**
 * 
 */
package com.anvl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @author Vaibhav
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan("com.anvl")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private CustomAuthenticationFailureHandler failureHandler;

	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;

	@Autowired
	private CustomLogoutSuccessHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// making login page and guest page
		http.authorizeRequests().antMatchers("/guest/**", "/authenticate").permitAll();

		// making spring security to validate all requests other than specified above
		http.authorizeRequests().anyRequest().authenticated();

		// adding custom form login and handlers
		http.userDetailsService(customUserDetailsService).formLogin().loginPage("/guest")
				.loginProcessingUrl("/authenticate").defaultSuccessUrl("/home").successHandler(successHandler)
				.failureUrl("/login?error").failureHandler(failureHandler);

		// configuring logout
		http.logout().logoutSuccessHandler(logoutHandler).clearAuthentication(true).invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").logoutSuccessUrl("/login?logout").permitAll();

		// Session Management
		http.sessionManagement().invalidSessionUrl("/login?session")
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation().migrateSession()
				.sessionAuthenticationStrategy(registerSessionAuthStr()).maximumSessions(1)
				.sessionRegistry(sessionRegistry()).maximumSessions(1).expiredUrl("/doLogin?error")
				.maxSessionsPreventsLogin(true);
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public RegisterSessionAuthenticationStrategy registerSessionAuthStr() {
		return new RegisterSessionAuthenticationStrategy(sessionRegistry());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/META-INF/resources/**", "/webjars/**", "/js/**", "/css/**", "/images/**",
				"/templates/**","/static/**");
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2A, 31);
	}

}
