package team.thirteen.leave.service;

import team.thirteen.leave.model.*;
import team.thirteen.leave.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeService {
	
	private EmployeeRepository empRepo;
	
	@Autowired
	private void setEmployeeRepository(EmployeeRepository empRepository) {
		this.empRepo = empRepository;
	}

	public EmployeeService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Employee> getSubordinates(int managerId) {
		List<Employee> allEmployees = empRepo.findAll();
		ArrayList<Employee> subordinates = new ArrayList<Employee>();
		for (Employee e: allEmployees) {
			if (e.getManagerId() == managerId) {
				subordinates.add(e);
				System.out.println(e);
			}
		}
		
		return subordinates;
	}

}
