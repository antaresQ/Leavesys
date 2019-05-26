package team.thirteen.leave.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import team.thirteen.leave.model.Employee;
import team.thirteen.leave.model.Leavedetail;
import team.thirteen.leave.model.Leaverecords;
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
		
		model.addAttribute("Leaverecords", new Leaverecords());
		
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
		
		
		return "viewSubLeaveApplications";
	}
	
	
	
	@RequestMapping(path="/viewIndiLeaveDetails", method = RequestMethod.GET)
	public String LeaveDetails(int leaveId, Model model) {
		
		Leavedetail leave = lvRepo.findById(leaveId).get();
		Employee subordinate = empRepo.findById(leave.getEmployee().getStaffId()).get();
		
		List<Leavedetail> allLeave = lvRepo.findAll();

		int aConsumed = 0;
		int mConsumed = 0;
		
		for (Leavedetail l: allLeave) {
			if (subordinate.getStaffId() == l.getEmployee().getStaffId()) {
				
				if(l.getStatus().equals("Approved")) {
					
					if (l.getCategory().equals("Annual Leave")) {
						aConsumed = aConsumed + 1;
					}
					else if(l.getCategory().equals("Medical Leave")) {
						mConsumed = mConsumed +1;
					}
				
					int annualL = subordinate.getRole().getAnnualLeave();
					int medicalL = subordinate.getRole().getMedicalLeave();
					annualL = annualL - aConsumed;
					medicalL = medicalL - mConsumed;
					
					subordinate.getRole().setAnnualLeave(annualL);
					subordinate.getRole().setMedicalLeave(medicalL);
				}
			}
		}
		
		model.addAttribute("subordinate", subordinate);
		model.addAttribute("leaveDetail", leave);
		
		return "individualLeaveDetails";
	}
	
	
	@RequestMapping(path="/submitreview", method = RequestMethod.POST)
	public String SubmitReview(Leavedetail leavedetail,Model model,RedirectAttributes redirectA) {
		
		Employee subordinate = empRepo.findById(leavedetail.getEmployee().getStaffId()).get();
		String message = new String();
		
		List<Leavedetail> allLeave = lvRepo.findAll();
		int annualL = 0;
		int medicalL = 0;
		int aConsumed = 0;
		int mConsumed = 0;
		
		for (Leavedetail l: allLeave) {
			if (subordinate.getStaffId() == l.getEmployee().getStaffId()) {
				
				if(l.getStatus().equals("Approved")) {
					
					if (l.getCategory().equals("Annual Leave")) {
						aConsumed = aConsumed + 1;
					}
					else if(l.getCategory().equals("Medical Leave")) {
						mConsumed = mConsumed +1;
					}
				
					annualL = subordinate.getRole().getAnnualLeave();
					medicalL = subordinate.getRole().getMedicalLeave();
					annualL = annualL - aConsumed;
					medicalL = medicalL - mConsumed;
					
					subordinate.getRole().setAnnualLeave(annualL);
					subordinate.getRole().setMedicalLeave(medicalL);
				}
			}
		}
		
		if (leavedetail.getCategory() == "Annual Leave" && leavedetail.getStatus()=="Approved" && annualL > 0) {
			lvRepo.save(leavedetail);
			message = "Leave is Approved successfully";
		}
		
		else if (leavedetail.getCategory() == "Medical Leave" && leavedetail.getStatus()=="Approved" && medicalL > 0) {
			lvRepo.save(leavedetail);
			message = "Leave is Approved successfully";
		}
		
		else if (leavedetail.getStatus() == "Rejected") {
			lvRepo.save(leavedetail);
			message = "Leave is Rejected successfully";
		}
		
		model.addAttribute("subordinate", subordinate);
		model.addAttribute("leaveDetail", leavedetail);
		model.addAttribute("message", message);
		
		
		int val = leavedetail.getLeaveId();
		redirectA.addAttribute("leaveId", val);
		return "redirect:/viewIndiLeaveDetails";
	}
	
	
	@RequestMapping(path="/returnSubLeave", method = RequestMethod.POST)
	public String ReturnSubLeave(Leavedetail leavedetail,RedirectAttributes redirectA) {

		
		int val = leavedetail.getemployee().getManagerId();		
		redirectA.addAttribute("managerId", val);
		return "redirect:/viewsubleave";
	}
	
	
//	int val = leavedetail.getemployee().getManagerId();		
//	redirectA.addAttribute("managerId", val);
//	return "redirect:/viewsubleave";
	
	//Deprecated
	@RequestMapping(path="/submitBatchReview", method = RequestMethod.POST)
	public String SubmitBatchReview(Leaverecords leaverecords,RedirectAttributes redirectA) {
		
		Iterator<Leavedetail> iterL = leaverecords.getLeaveList().iterator();
		
		while (iterL.hasNext()) {
			lvRepo.save(iterL.next());
		}
		
		int val = leaverecords.getLeaveList().get(0).getemployee().getManagerId();		
		redirectA.addAttribute("managerId", val);
		return "redirect:/viewsubleave";
	}
	
	
	
	
	//Route test
	@RequestMapping(path="/testpage", method = RequestMethod.GET)
	public String test() {
		return "viewLeaveApplications";
	}
	
	
	//Controller Redirect Test
	@RequestMapping(path="/testred", method = RequestMethod.GET)
	public String testred(RedirectAttributes redirectA) {
		
		int val = 1;
		redirectA.addAttribute("managerId", val);
		return "redirect:/viewsubleave";
	}
}
