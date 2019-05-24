package team.thirteen.leave.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.thirteen.leave.model.Employee;
import team.thirteen.leave.model.Leavedetail;
import team.thirteen.leave.model.Role;
import team.thirteen.leave.repository.*;
import team.thirteen.leave.service.EmployeeService;
import team.thirteen.leave.service.LeaveService;

@Controller
public class ReviewLeaveController {

	private LeaveRepository lvRepo;
	private EmployeeRepository empRepo;
	
	@Autowired
	public void setLeaveRepository(LeaveRepository leaveRepository) {
		this.lvRepo = leaveRepository;
	}
	
	@Autowired
	private void setEmployeeRepository(EmployeeRepository empRepository) {
		this.empRepo = empRepository;
	}
	
	@RequestMapping(path="/viewsubleave", params = "managerId" ,method = RequestMethod.GET)
	public String ViewApplications(
			@RequestParam("managerId") int managerId, Model model) {
		
//		EmployeeService emps = new EmployeeService();
//		LeaveService les = new LeaveService();
//		
//		ArrayList<Employee> subordinates = emps.getSubordinates(managerId);
//		ArrayList<Leavedetail> subLeave = les.getAllSubLeave(subordinates);
			
		List<Employee> allEmployees = empRepo.findAll();
		ArrayList<Employee> subordinates = new ArrayList<Employee>();
		List<Leavedetail> allLeave = lvRepo.findAll();
		ArrayList<Leavedetail> subLeave = new ArrayList<>();
		

		for (Employee e: allEmployees) {
			
			if (e.getManagerId() == managerId) {
				
				int aConsumed = 0;
				int mConsumed = 0;
				
				for (Leavedetail l: allLeave) {
					
						if (l.getemployee() == e) {
							subLeave.add(l);

							if(l.getStatus().equals("Approved")) {
								
								if (l.getCategory().equals("Annual Leave")) {
									aConsumed = aConsumed + 1;
								}
								else if(l.getCategory().equals("Medical Leave")) {
									mConsumed = mConsumed +1;
								}
								
							}
								
						}
					}
				
				int annualL = e.getRole().getAnnualLeave();
				int medicalL = e.getRole().getMedicalLeave();
				annualL = annualL - aConsumed;
				medicalL = medicalL - mConsumed;
				
				e.getRole().setAnnualLeave(annualL);
				e.getRole().setMedicalLeave(medicalL);
				subordinates.add(e);
			}
		}
		
		model.addAttribute("subordinates", subordinates);
		model.addAttribute("subleave", subLeave);
		
		
		return "viewLeaveApplications";
	}
	
	
	@RequestMapping(path="/testpage", method = RequestMethod.GET)
	public String test() {
		return "viewLeaveApplications";
	}
}
