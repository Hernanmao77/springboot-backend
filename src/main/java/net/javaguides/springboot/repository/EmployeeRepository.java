package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @Modifying
    @Query("SELECT * from employees emp where (:firstName is null or emp.firstName LIKE CONCAT('%', :firstName, '%')) and (:lastName is null or emp.lastName LIKE CONCAT('%', :lastName, '%'))  and (:state is null or emp.state = :state) and (:emailId is null or emp.emailId LIKE CONCAT('%', :emailId, '%'))")
    List<Employee> findByFilter(@Param("firstName") String firstName, @Param("lastName") String lastName,@Param("state") String state, @Param("emailId") String emailId);

}
