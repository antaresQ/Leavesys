package team.thirteen.leave.model;

import javax.persistence.*;

import team.thirteen.leave.model.Role;

@Entity(name="employee")
@Table(name="employee")
public class Employee {
	
	@Id
	private int staffId;
	private String name;
	private String userId;
	private String password;
	private int managerId;
	private String email;
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int staffId, String name, String userId, String password, int managerId, Role role,
			String email) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.userId = userId;
		this.password = password;
		this.managerId = managerId;
		this.role = role;
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
	public Role getRole() {
		return this.role;
	}
	public void setRole(Role role) {
		this.role = role;
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
				+ ", managerId=" + managerId + ", Role=" + role + ", email=" + email + "]";
	}
	
	
	
}
