package team.thirteen.leave.model;

import javax.persistence.*;

@Entity
public class Role {

	@Id
	private int roleId;
	private String title;
	private int annualLeave;
	private int medicalLeave;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int roleId, String title, int annualLeave, int medicalLeave) {
		super();
		this.roleId = roleId;
		this.title = title;
		this.annualLeave = annualLeave;
		this.medicalLeave = medicalLeave;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAnnualLeave() {
		return annualLeave;
	}

	public void setAnnualLeave(int annualLeave) {
		this.annualLeave = annualLeave;
	}

	public int getMedicalLeave() {
		return medicalLeave;
	}

	public void setMedicalLeave(int medicalLeave) {
		this.medicalLeave = medicalLeave;
	}
	
	
}
