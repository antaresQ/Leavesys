package team.thirteen.leave.model;

import javax.persistence.*;

@Entity
public class Employee {
	
	@Id
	private int staffId;
	private String name;
	private String userId;
	private String password;
	private int managerId;
	private String Role;
	private String email;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int staffId, String name, String userId, String password, int managerId, String role,
			String email) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.userId = userId;
		this.password = password;
		this.managerId = managerId;
		Role = role;
		this.email = email;
	}

	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [staffId=" + staffId + ", name=" + name + ", userId=" + userId + ", password=" + password
				+ ", managerId=" + managerId + ", Role=" + Role + ", email=" + email + "]";
	}
	
	
	
}
