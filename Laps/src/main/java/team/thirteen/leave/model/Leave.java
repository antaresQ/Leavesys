package team.thirteen.leave.model;

import java.time.LocalDateTime;

public class Leave {
	
	private int LeaveId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String category;
	private String reason;
	private int staffId;
	private String contactDetails;
	private String status;
	private String comment;
	
	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Leave(int leaveId, LocalDateTime startDate, LocalDateTime endDate, String category, String reason,
			int staffId, String contactDetails) {
		super();
		LeaveId = leaveId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.reason = reason;
		this.staffId = staffId;
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
	

}
