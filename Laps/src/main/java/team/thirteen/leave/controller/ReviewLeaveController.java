package team.thirteen.leave.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import team.thirteen.leave.model.Employee;
import team.thirteen.leave.model.LeaveDetail;
import team.thirteen.leave.repository.*;

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
	
	@RequestMapping(path="/review/subordinateleave/{id}", method = RequestMethod.GET)
	public String ViewApplications(Integer managerId, Model model) {

		List<Employee> allEmployees = empRepo.findAll();
		ArrayList<Employee> subordinates = new ArrayList<Employee>();
		List<LeaveDetail> allLeave = lvRepo.findAll();
		ArrayList<LeaveDetail> subLeave = new ArrayList<>();
		
		for (Employee e: allEmployees) {
			if (e.getManagerId() == managerId) {
				
				subordinates.add(e);
				
				for (LeaveDetail l: allLeave) {
					
					if (l.getemployee() == e) {
						subLeave.add(l);
					}
					
				}
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
