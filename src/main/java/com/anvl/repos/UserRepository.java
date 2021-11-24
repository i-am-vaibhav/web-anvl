/**
 * 
 */
package com.anvl.repos;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anvl.entities.User;

/**
 * @author DELL
 *
 */
public interface UserRepository extends JpaRepository<User, BigDecimal> {

	Optional<User> findByUsername(String username);

}
