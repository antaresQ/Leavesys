package team.thriteen.leave.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import team.thirteen.leave.model.Employee;
import team.thirteen.leave.model.Leavedetail;
import team.thirteen.leave.model.Role;


@Service
public class ManagerSvc {

	int annualL;
	int medicalL;
	int aConsumed;
	int mConsumed;
	int managerId;
	ArrayList<Employee> allEmployees;
	ArrayList<Employee> subordinates;
	ArrayList<Leavedetail> subLeave;
	ArrayList<Leavedetail> allLeave;
	
//	public ManagerSvc() {
//		super();
//		annualL = 0;
//		medicalL = 0;
//		aConsumed = 0;
//		mConsumed = 0;
//	}
	
	public ManagerSvc(int managerId, ArrayList<Leavedetail> allLeave, ArrayList<Employee> allEmployees) {
		super();
		this.managerId = managerId;
		this.allLeave = allLeave;
		this.allEmployees = allEmployees;
		
		for (Employee e: allEmployees) {
			
			if (e.getManagerId() == managerId) {
		
				for (Leavedetail l: allLeave) {
					
					if (l.getemployee() == e) {
						subLeave.add(l);
					}
				}
			}
		}
		
		for (Employee e: allEmployees) {
			
			if (e.getManagerId() == managerId) {
				
				subordinates.add(e);
			}
		}
	}

	public Leavedetail getLeaveById(int id) {
		
		Leavedetail leave = new Leavedetail();
		for (Leavedetail l: subLeave) {
			if (l.getLeaveId() == id) {
				leave = l;
			}
		}
		
		return leave;
	}

	
	public Role getRoleWBal(Employee e) {
		
		annualL = 0;
		medicalL = 0;
		aConsumed = 0;
		mConsumed = 0;
		
		Role role = e.getRole();
		
		for (Leavedetail l: allLeave) {
			
			if (l.getemployee() == e) {

				if(l.getStatus().equals("Approved")) {
					
					if (l.getCategory().equals("Annual Leave")) {
						aConsumed++;
					}
					else if(l.getCategory().equals("Medical Leave")) {
						mConsumed++;
					}
					
				}
					
			}
		}
	
		annualL = e.getRole().getAnnualLeave();
		medicalL = e.getRole().getMedicalLeave();
		annualL = annualL - aConsumed;
		medicalL = medicalL - mConsumed;
		
		role.setAnnualLeave(annualL);
		role.setMedicalLeave(medicalL);
		
		return role;
	}
	
	public Leavedetail checkValid(Leavedetail leave) {
		annualL = 0;
		medicalL = 0;
		aConsumed = 0;
		mConsumed = 0;
		
		Role roleWBal = getRoleWBal(leave.getemployee());
		
		return leave;
	}
	
	public String checkValidMsg(Leavedetail leave, Role role) {
		
		String message = new String();
		
		if (leave.getStatus() == null) {
			leave.setStatus("Applied/Updated");
			message = "Invalid Submission. Please Select an Option.";
		}
		
		else if (leave.getCategory().equals("Annual Leave")  && leave.getStatus().equals("Approved") && role.getAnnualLeave() > 0) {
			int annualL = role.getAnnualLeave();
			role.setAnnualLeave(annualL - 1);
			message = "Leave is Approved successfully";
		}
		
		else if (leave.getCategory().equals("Medical Leave") && leave.getStatus().equals("Approved") && medicalL > 0) {
			int medicalL = role.getMedicalLeave();
			role.setMedicalLeave(medicalL - 1);
			message = "Leave is Approved successfully";
		}
		
		else if (leave.getStatus().equals("Rejected") && leave.getComment().isEmpty()) {
			leave.setStatus("Applied/Updated");
			message = "Comment required for Rejection";
		}
		
		else if (leave.getStatus().equals("Rejected")) {
			message = "Leave is Rejected. Comment:" + leave.getComment();
		}
		
		
		return message;
	}
	
	public Boolean toSave(String message) {

		Boolean save = false;
		
		if (message == "Invalid Submission. Please Select an Option.") {
			save = false;
		}
		
		else if (message == "Leave is Approved successfully") {
			save = true;
		}
		
		else if (message == "Comment required for Rejection") {
			save = false;
		}

		return save;
	}
	
	public ArrayList<Employee> getSubordinates() {
		return subordinates;
	}

	public ArrayList<Leavedetail> getSubLeave() {
		return subLeave;
	}
}