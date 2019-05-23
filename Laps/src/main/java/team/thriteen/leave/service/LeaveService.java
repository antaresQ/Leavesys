package team.thriteen.leave.service;

import team.thirteen.leave.model.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

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
		String queryName = "Leave.findByStaffId";
		Query qry = em.createNamedQuery(queryName);
		qry.setParameter("staffId", staffId);
		ArrayList<Leave> leaveRecord = (ArrayList<Leave>) qry.getResultList();
		return leaveRecord;
	}

	public ArrayList<Leave> showAllSubordinateLeave(int managerId) {
		em.getTransaction().begin();
		List<Employee> subs = (List<Employee>) getSubordinates(managerId);
		ArrayList<Leave> subLeaveRecord = new ArrayList<Leave>();
		
		for (Employee e : subs) {
				
		}
		
		return subLeaveRecord;
	}
}
