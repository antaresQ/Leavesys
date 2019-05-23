package team.thirteen.leave.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.thirteen.leave.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
