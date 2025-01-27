package com.mysite.rest.employee.entity;

import com.mysite.rest.employee.dto.EmployeeDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table (name = "employees")
public class Employee {
	// Entity : DB의 테이블의 컬럼을 연결 
	
		
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column (name = "first_name")
	private String firstName; 
	
	@Column (name = "last_name")
	private String lastName; 
	
	@Column (name = "email_id")
	private String emailId; 
	
	public Employee (EmployeeDTO employeeDTO) {
		this.firstName = employeeDTO.getFirstName();
		this.lastName = employeeDTO.getLastName(); 
		this.emailId = employeeDTO.getEmailId(); 
	}
	
	
}
