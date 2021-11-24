/**
 * 
 */
package com.anvl.repos;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anvl.entities.Role;

/**
 * @author DELL
 *
 */
public interface RoleRepository extends JpaRepository<Role, BigDecimal> {

}
