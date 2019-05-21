package team.thriteen.leave.service;

import team.thirteen.leave.model.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import team.thirteen.leave.util.PersistenceManager;


public class LeaveService {
	
	private EntityManager em;
	
	public LeaveService() {
		
		em = PersistenceManager.INSTANCE.getEntityManager();
	}
	
	public Employee getSubordinates(int managerId) {
		return em.find(Employee.class, managerId);
	}
	
	public ArrayList<Leave> getEmpLeaveRecById(int staffId) {
		
	}

	public ArrayList<Leave> showAllSubordinateLeave(int managerId) {
		em.getTransaction().begin();
		List<Employee> subs = (List<Employee>) getSubordinates(managerId);
		ArrayList<Leave> subLeaveRecord = new ArrayList<Leave>();
		
		for (Employee e : subs) {
			
			TypedQuery<Leave> tquery = em.createQuery("Select l From Staff l WHERE l.");
			
		}
		
		return subLeaveRecord;
	}
}
