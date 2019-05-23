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
	
	public ArrayList<LeaveDetail> getEmpLeaveRecById(int staffId) {
		String queryName = "Leave.findByStaffId";
		Query qry = em.createNamedQuery(queryName);
		qry.setParameter("staffId", staffId);
		ArrayList<LeaveDetail> leaveRecord = (ArrayList<LeaveDetail>) qry.getResultList();
		return leaveRecord;
	}

	public ArrayList<LeaveDetail> showAllSubordinateLeave(int managerId) {
		em.getTransaction().begin();
		List<Employee> subs = (List<Employee>) getSubordinates(managerId);
		ArrayList<LeaveDetail> subLeaveRecord = new ArrayList<LeaveDetail>();
		
		for (Employee e : subs) {
				
		}
		
		return subLeaveRecord;
	}
}
