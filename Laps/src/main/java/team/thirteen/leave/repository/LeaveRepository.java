package team.thirteen.leave.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.thirteen.leave.model.Leavedetail;

@Repository
public interface LeaveRepository extends JpaRepository<Leavedetail, Integer>{

}
