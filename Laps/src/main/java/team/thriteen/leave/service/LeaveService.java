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
	
	public ArrayList<Leavedetail> getEmpLeaveRecById(int staffId) {
		String queryName = "Leave.findByStaffId";
		Query qry = em.createNamedQuery(queryName);
		qry.setParameter("staffId", staffId);
		ArrayList<Leavedetail> leaveRecord = (ArrayList<Leavedetail>) qry.getResultList();
		return leaveRecord;
	}

	public ArrayList<Leavedetail> showAllSubordinateLeave(int managerId) {
		em.getTransaction().begin();
		List<Employee> subs = (List<Employee>) getSubordinates(managerId);
		ArrayList<Leavedetail> subLeaveRecord = new ArrayList<Leavedetail>();
		
		for (Employee e : subs) {
				
		}
		
		return subLeaveRecord;
	}
}
