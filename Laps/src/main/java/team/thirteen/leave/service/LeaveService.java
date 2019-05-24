package team.thirteen.leave.service;

import team.thirteen.leave.model.*;
import team.thirteen.leave.repository.LeaveRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeaveService {
	
	private LeaveRepository lvRepo;
	
	@Autowired
	public void setLeaveRepository(LeaveRepository leaveRepository) {
		this.lvRepo = leaveRepository;
	}
	
	public LeaveService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Leavedetail> getEmpLeaveRecById(int staffId) {
		List<Leavedetail> allLeave = lvRepo.findAll();
		ArrayList<Leavedetail> leaverecord = new ArrayList<>();
		for (Leavedetail l : allLeave) {
			if (l.getemployee().getStaffId() == staffId) {
				leaverecord.add(l);
			}
		}
		
		return leaverecord;
	}
	
	public ArrayList<Leavedetail> getAllSubLeave (ArrayList<Employee> employees) {
		List<Leavedetail> allLeave = lvRepo.findAll();
		ArrayList<Leavedetail> leaverecords = new ArrayList<>();
		for (Employee e : employees) {
			
			for (Leavedetail l : allLeave) {
				if(l.getemployee().getStaffId() == e.getStaffId()) {
					leaverecords.add(l);
				}
			}
		}
		
		return leaverecords;
	}
	
	public List<Leavedetail> getAllLeave () {
		List<Leavedetail> allLeave = lvRepo.findAll();
		
		return allLeave;
	}

}
 