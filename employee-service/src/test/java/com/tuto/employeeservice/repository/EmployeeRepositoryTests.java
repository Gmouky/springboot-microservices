package com.tuto.employeeservice.repository;

import com.tuto.employeeservice.entity.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = createEmployee();
    }

    //Junit test for save employee operation
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        //when - action or the behavior that e are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenEmployees_whenFindAll_thenReturnListOfAllEmployees() {
        Employee employee1 = createEmployee();
        //email should be unique
        employee1.setEmail("john@wick.com");

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        List<Employee> employeeList = employeeRepository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    public void givenEmployee_whenFindById_thenReturnEmployee() {
        employeeRepository.save(employee);
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotZero();
    }

    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployee() {
        employeeRepository.save(employee);
        Employee savedEmployee = employeeRepository.findByEmail("wick@mail.com").get();

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getEmail()).isEqualTo("wick@mail.com");
    }

    @Test
    public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        employeeRepository.save(employee);

        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

        savedEmployee.setEmail("test@test.de");
        savedEmployee.setFirstName("john");
        savedEmployee.setLastName("cena");

        Employee updateEmployee = employeeRepository.save(savedEmployee);

        assertThat(updateEmployee.getId()).isEqualTo(savedEmployee.getId());
        assertThat(updateEmployee.getLastName()).isEqualTo("cena");
        assertThat(updateEmployee.getFirstName()).isEqualTo("john");
        assertThat(updateEmployee.getEmail()).isEqualTo("test@test.de");
    }

    @Test
    public void givenEmployee_whenDelete_thenRemoveEmployee() {
        employeeRepository.save(employee);

        employeeRepository.delete(employee);

        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        assertThat(employeeOptional).isEmpty();
    }

    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployee() {
        employeeRepository.save(employee);

        String firstName = "ramesh";
        String lastName = "wick";

        Employee savedEmployee = employeeRepository.findByJpql(firstName, lastName);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo(firstName);
        assertThat(savedEmployee.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void givenFirstNameAndLastName_whenFindByJpqlNamedParams_thenReturnEmployee() {
        employeeRepository.save(employee);

        String firstName = "ramesh";
        String lastName = "wick";

        Employee savedEmployee = employeeRepository.findByJpqlNameParams(firstName, lastName);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo(firstName);
        assertThat(savedEmployee.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployee() {
        employeeRepository.save(employee);

        Employee savedEmployee = employeeRepository.findbyNativeSQL(employee.getFirstName(), employee.getLastName());

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(savedEmployee.getLastName()).isEqualTo(employee.getLastName());
    }

    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedPArams_thenReturnEmployee() {
        employeeRepository.save(employee);

        Employee savedEmployee = employeeRepository.findbyNativeSQLNamedParams(employee.getFirstName(), employee.getLastName());

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(savedEmployee.getLastName()).isEqualTo(employee.getLastName());
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("ramesh");
        employee.setLastName("wick");
        employee.setEmail("wick@mail.com");
        employee.setDepartmentCode("IT001");
        employee.setOrganizationCode("ABC001");

        return employee;
    }
}
