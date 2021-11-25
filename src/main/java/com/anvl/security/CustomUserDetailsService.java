/**
 * 
 */
package com.anvl.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.anvl.entities.User;
import com.anvl.repos.UserRepository;

/**
 * @author Vaibhav
 *
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> findByEmail = userRepository.findByEmail(username);
		if(findByEmail.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return new AuthenticatedUser(findByEmail.get());
	}

}
