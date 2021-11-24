/**
 * 
 */
package com.anvl.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vaibhav
 *
 */
@Data
@Entity
@Table(name = "TBL_ROLE")
@NoArgsConstructor
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private BigDecimal id;
	
	private String name;
	
	private String description;
	
}
