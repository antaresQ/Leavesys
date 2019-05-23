package team.thirteen.leave.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.thirteen.leave.model.Employee;
import team.thirteen.leave.model.LeaveDetail;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}