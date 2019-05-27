package team.thriteen.leave.service;

import java.util.ArrayList;

import team.thirteen.leave.model.Employee;
import team.thirteen.leave.model.Leavedetail;
import team.thirteen.leave.model.Role;

public class ManagerSvc {

	int annualL = 0;
	int medicalL = 0;
	int aConsumed = 0;
	int mConsumed = 0;
	Role role;
	Employee employee;
	Employee manager;
	Leavedetail leavedetail; 
	ArrayList<Leavedetail> subLeave;
	ArrayList<Leavedetail> allLeave;
	
	public ManagerSvc() {
		super();
		annualL = 0;
		medicalL = 0;
		aConsumed = 0;
		mConsumed = 0;
	}
	
	public Role getRole() {
		
		return role;
	}
	
	public Employee getEmployee() {
		
		return employee;
	}
	
	public ArrayList<Leavedetail> getSubLeave(int managerId, ArrayList<Leavedetail> allLeave, ArrayList<Employee> allEmployees) {
		
		this.allLeave = allLeave;
		
		for (Employee e: allEmployees) {
			
			if (e.getManagerId() == managerId) {
		
				for (Leavedetail l: allLeave) {
					
					if (l.getemployee() == e) {
						subLeave.add(l);
					}
				}
			}
		}
		
		return subLeave;
	}
}
