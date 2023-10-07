package com.tuto.employeeservice.repository;

import com.tuto.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    //define custom query using JPQL with index params
    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJpql(String firstName, String lastName);

    //define custom query using JPQL with names params
    @Query("select e from Employee e where e.firstName =:firstName and e.lastName =:lastName")
    Employee findByJpqlNameParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "select * from employees e where e.first_name = ?1 and last_name = ?2", nativeQuery = true)
    Employee findbyNativeSQL(String firstName, String lastName);

    @Query(value = "select * from employees e where e.first_name =:firstName and last_name =:lastName", nativeQuery = true)
    Employee findbyNativeSQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
