/**
 * 
 */
package com.anvl.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vaibhav
 *
 */
@Data
@Entity
@Table(name = "TBL_USER")
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private BigDecimal id;

	private String username;

	private String password;

	private String email;

	private String designation;

	@ManyToMany
	@JoinTable(name = "TBL_USER_ROLE", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "roe_id"))
	private List<Role> roles;

}
