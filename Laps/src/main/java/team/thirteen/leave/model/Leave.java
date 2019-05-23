package team.thirteen.leave.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Leave {
	
	@Id
	private int LeaveId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String category;
	private String reason;
	private String contactDetails;
	private String status;
	private String comment;
	
	@ManyToOne
    @JoinColumn(name="staffId")
    private Employee employee;
	
	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Leave(int leaveId, LocalDateTime startDate, LocalDateTime endDate, String category, String reason,
			Employee employee, String contactDetails) {
		super();
		LeaveId = leaveId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.reason = reason;
		this.employee = employee;
		this.contactDetails = contactDetails;
		this.status = "Pending";
	}

	public int getLeaveId() {
		return LeaveId;
	}

	public void setLeaveId(int leaveId) {
		LeaveId = leaveId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public Employee getemployee() {
		return employee;
	}


	public void setemployee(Employee employee) {
		this.employee = employee;
	}


	public String getContactDetails() {
		return contactDetails;
	}


	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
