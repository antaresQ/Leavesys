package team.thirteen.leave.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
			
		List<Employee> allEmployees = empRepo.findAll();
		ArrayList<Employee> subordinates = new ArrayList<Employee>();
		List<Leavedetail> allLeave = lvRepo.findAll();
		ArrayList<Leavedetail> subLeave = new ArrayList<>();
		
		int annualL = 0;
		int medicalL = 0;
		int aConsumed = 0;
		int mConsumed = 0;

		for (Employee e: allEmployees) {
			
			if (e.getManagerId() == managerId) {

				for (Leavedetail l: allLeave) {
					
						if (l.getemployee() == e) {
							subLeave.add(l);

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
	public String LeaveDetails(int leaveId, Model model, String message) {
		
		String msg = message;
		
		Leavedetail leave = lvRepo.findById(leaveId).get();
		Employee subordinate = empRepo.findById(leave.getEmployee().getStaffId()).get();
		
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
		
		
		model.addAttribute("subordinate", subordinate);
		model.addAttribute("leaveDetail", leave);
		model.addAttribute("message", msg);
		
		return "individualLeaveDetails";
	}
	
	@RequestMapping(path="/submitreview", method = RequestMethod.POST)
	public String SubmitReview(Leavedetail leaveDetail,Model model,RedirectAttributes redirectA) {
		
		Employee subordinate = empRepo.findById(leaveDetail.getEmployee().getStaffId()).get();
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
					
				}
			}
		}
		
		
		if (leaveDetail.getStatus() == null) {
			leaveDetail.setStatus("Applied/Updated");
			message = "Invalid Submission. Please Select an Option.";
		}
		
		else if (leaveDetail.getCategory().equals("Annual Leave")  && leaveDetail.getStatus().equals("Approved") && annualL > 0) {
			annualL--;
			lvRepo.save(leaveDetail);
			message = "Leave is Approved successfully";
		}
		
		else if (leaveDetail.getCategory().equals("Medical Leave") && leaveDetail.getStatus().equals("Approved") && medicalL > 0) {
			medicalL--;
			lvRepo.save(leaveDetail);
			message = "Leave is Approved successfully";
		}
		
		else if (leaveDetail.getStatus().equals("Rejected") && leaveDetail.getComment().isEmpty()) {
			leaveDetail.setStatus("Applied/Updated");
			message = "Comment required for Rejection";
		}
		
		else if (leaveDetail.getStatus().equals("Rejected")) {
			lvRepo.save(leaveDetail);
			message = "Leave is Rejected. Comment:" + leaveDetail.getComment();
		}
		
		subordinate.getRole().setAnnualLeave(annualL);
		subordinate.getRole().setMedicalLeave(medicalL);
		
		String url = "/viewsubleave?managerId=" + subordinate.getManagerId();
		
		model.addAttribute("subordinate", subordinate);
		model.addAttribute("leaveDetail", leaveDetail);
		model.addAttribute("message", message);
		model.addAttribute("url",url);
		
		
		int val = leaveDetail.getLeaveId();
		model.addAttribute("leaveId", val);
		return "individualLeaveDetails";
	}
	
	//return to subleave page
	@RequestMapping(path="/returnSubLeave", method = RequestMethod.POST)
	public String ReturnSubLeave(Leavedetail leavedetail,RedirectAttributes redirectA) {

		int val = leavedetail.getemployee().getManagerId();		
		redirectA.addAttribute("managerId", val);
		return "redirect:/viewsubleave";
	}

	
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
	
	@RequestMapping(path="/testform", method = RequestMethod.GET)
	public String TestForm(Model model) {
		
		Leavedetail leaveObject = new Leavedetail();
		model.addAttribute(leaveObject);
		
		return "testform";
	}
	
}

//test
