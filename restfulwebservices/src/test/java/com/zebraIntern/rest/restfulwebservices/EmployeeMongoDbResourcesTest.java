package com.zebraIntern.rest.restfulwebservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.zebraIntern.rest.restfulwebservices.employee.EmployeeEntity;
import com.zebraIntern.rest.restfulwebservices.employee.EmployeeMongoDbRepository;
import com.zebraIntern.rest.restfulwebservices.employee.EmployeeMongoDbResources;

@ExtendWith(MockitoExtension.class)
public class EmployeeMongoDbResourcesTest {

	@Mock
	private EmployeeMongoDbRepository repository;

	@InjectMocks
	private EmployeeMongoDbResources employeeMongoDbResources;

	@Test
	public void getAllEmployeeTest() {

		List<EmployeeEntity> employees = Arrays.asList(
				new EmployeeEntity("1001", "Nazifullah", "Hanan", "nazifullah.hanan@example.com", "3601 karl dr"),
				new EmployeeEntity("1002", "Ahmad", "Acbary", "ahmad.acbary@example.com", "122 karn dr"));

		when(repository.findAll()).thenReturn(employees);
		List<EmployeeEntity> response = employeeMongoDbResources.getAllEmployee();
		assertEquals(employees, response);
	}

	@Test
	public void getAllEmployeeCountTest() {
		List<EmployeeEntity> employees = Arrays.asList(
				new EmployeeEntity("1001", "sabir", "samim", "sabir.samim@example.com", "323 Karl Dr"),
				new EmployeeEntity("1002", "Ahmad", "Ahmady", "ahmad.ahmady@example.com", "123 Main St"));

		when(repository.findAll()).thenReturn(employees);

		String response = employeeMongoDbResources.getAllEmployeeCount();

		assertEquals("There are " + employees.size() + " employees in the list", response);
	}

	@Test
	public void getOneEmployeeByIdTest() {

		EmployeeEntity employee = new EmployeeEntity("1001", "Nazifullah", "Hanan", "nazifullah.hanan@example.com",
				"3601 karl dr");

		when(repository.findByEmployeeId("1001")).thenReturn(Optional.of(employee));

		Optional<EmployeeEntity> response = employeeMongoDbResources.getOneEmployeeById("1001");

		assertEquals(employee, response.get());
	}

	@Test
	public void findByLastNameTest() {

		List<EmployeeEntity> employees = new ArrayList<>();
		employees.add(new EmployeeEntity("1001", "Safi", "Ahmadi", "safi.ahmadi@example.com", "123 Main St"));

		when(repository.findByLastName("Ahmadi")).thenReturn(employees);

		List<EmployeeEntity> response = employeeMongoDbResources.findByLastName("Ahmadi");

		assertEquals(employees, response);

	}

	@Test
	public void findByLastNameSortedByFirstNameTest() {

		List<EmployeeEntity> employees = Arrays.asList(
				new EmployeeEntity("1001", "John", "Doe", "john.doe@example.com", "123 Main St"),
				new EmployeeEntity("1002", "Jane", "Doe", "jane.doe@example.com", "456 Elm St"));

		when(repository.findByLastNameOrderByEmployeeFirstNameAsc("Doe")).thenReturn(employees);

		List<EmployeeEntity> response = employeeMongoDbResources.findByLastNameSortedByFirstName("Doe");

		assertEquals(employees, response);

	}

	@Test
	public void getAllEmployeeSortedByFirstNameTest() {
		List<EmployeeEntity> employees = Arrays.asList(
				new EmployeeEntity("1001", "Sabir", "Samim", "sabir.samim@example.com", "123 Main St"),
				new EmployeeEntity("1002", "Ahmad", "Samim", "ahmad.samim@example.com", "456 Elm St"));

		when(repository.findAll(Sort.by(Sort.Direction.ASC, "employeeFirstName"))).thenReturn(employees);

		List<EmployeeEntity> response = employeeMongoDbResources.getAllEmployeesSortedByFirstName();
		assertEquals(employees, response);
	}

	@Test
	public void createEmployeeTest() {
		EmployeeEntity employee = new EmployeeEntity("1001", "John", "Smith", "john.smith@example.com", "3681 Karl Dr");

		when(repository.findByEmployeeId(employee.getEmployeeId())).thenReturn(Optional.empty());

		String response = employeeMongoDbResources.createEmployee(employee);

		assertEquals("John Smith: Successfully added to the list", response);
	}

	@Test
	public void updateEmployeeByIdTest() {
		EmployeeEntity existingEmployee = new EmployeeEntity("1001", "John", "Doe", "john.doe@example.com",
				"3601 Karl Dr");
		EmployeeEntity updatedEmployee = new EmployeeEntity("1001", "John", "Smith", "john.smith@example.com",
				"123 Main St");

		when(repository.findByEmployeeId("1001")).thenReturn(Optional.of(existingEmployee));

		String response = employeeMongoDbResources.updateEmployeeById("1001", updatedEmployee);

		assertEquals("Employee updated successfully", response);
		assertEquals("Smith", existingEmployee.getLastName());
		assertEquals("123 Main St", existingEmployee.getEmployeeAddress());
	}

	@Test
	public void deleteEmployeeByIdTest() {
		EmployeeEntity employee = new EmployeeEntity("1001", "John", "Doe", "john.doe@example.com", "3601 Karl Dr");
		when(repository.findByEmployeeId("1001")).thenReturn(Optional.of(employee));

		String deletedEmployee = employeeMongoDbResources.deleteEmployeeById("1001");
		assertEquals("Employee deleted successfully from the list!", deletedEmployee);

	}

}
