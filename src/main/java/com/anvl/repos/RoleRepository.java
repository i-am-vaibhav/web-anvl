/**
 * 
 */
package com.anvl.repos;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anvl.entities.Role;

/**
 * @author vaibhav
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, BigDecimal> {

}
