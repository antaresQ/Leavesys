package team.thirteen.leave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewLeaveController {

	
	@RequestMapping(path="/view")
	public String ViewApplications() {
		
		return "viewLeaveApplications";
	}
}
