package com.zebraIntern.rest.restfulwebservices.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zebraIntern.rest.restfulwebservices.employee.exception.EmployeeNotFoundException;

import jakarta.validation.Valid;

@RestController
public class EmployeeMongoDbResources {

	private EmployeeMongoDbRepository repository;

	public EmployeeMongoDbResources(EmployeeMongoDbRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/employees")
	public List<EmployeeEntity> getAllEmployee() {
		List<EmployeeEntity> employees = repository.findAll();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employee found");

		}
		return repository.findAll();

	}

	@GetMapping("/employees/count")
	public String getAllEmployeeCount() {
		List<EmployeeEntity> emp = repository.findAll();
		if (emp.isEmpty()) {
			throw new EmployeeNotFoundException("The list is empty");
		}

		int count = emp.size();

		return "There are " + count + " employees in the list";

	}

	@GetMapping("/employees/{id}")
	public Optional<EmployeeEntity> getOneEmployeeById(@PathVariable String id) {
		if (repository.findByEmployeeId(id).isEmpty())
			throw new EmployeeNotFoundException("No employee found");

		return repository.findByEmployeeId(id);

	}

	@GetMapping("/employees/lastName")
	public List<EmployeeEntity> findByLastName(@RequestParam String lastName) {
		List<EmployeeEntity> employees = repository.findByLastName(lastName);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employee found with the given last name");
		}
		return employees;
	}

	@GetMapping("/employees/last-name")
	public List<EmployeeEntity> findByLastNameSortedByFirstName(@RequestParam String lastName) {
		List<EmployeeEntity> employees = repository.findByLastNameOrderByEmployeeFirstNameAsc(lastName);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employee found with the given last name");
		}
		return employees;
	}

	@GetMapping("/employees/sorted-by-first-name")
	public List<EmployeeEntity> getAllEmployeesSortedByFirstName() {
		Sort sortByFirstName = Sort.by(Sort.Direction.ASC, "employeeFirstName");
		List<EmployeeEntity> employees = repository.findAll(sortByFirstName);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employees found");
		}
		return employees;
	}

	@PostMapping("/employees")
	public String createEmployee(@Valid @RequestBody EmployeeEntity employee) {

		Optional<EmployeeEntity> existingEmployee = repository.findByEmployeeId(employee.getEmployeeId());
		if (existingEmployee.isPresent()) {
			throw new EmployeeNotFoundException("This ID is already exist");
		}
		repository.save(employee);

		return employee.getEmployeeFirstName() + " " + employee.getLastName() + ": Successfully added to the list";
	}

	@PutMapping("/employees/{id}")
	public String updateEmployeeById(@PathVariable String id, @RequestBody EmployeeEntity updatedEmployee) {
		Optional<EmployeeEntity> empOptional = repository.findByEmployeeId(id);
		if (empOptional.isEmpty()) {
			throw new EmployeeNotFoundException("No employee found to update");
		}

		EmployeeEntity e = empOptional.get();
		e.setEmployeeId(updatedEmployee.getEmployeeId());
		e.setEmployeeFirstName(updatedEmployee.getEmployeeFirstName());
		e.setLastName(updatedEmployee.getLastName());
		e.setEmployeeEmail(updatedEmployee.getEmployeeEmail());
		e.setEmployeeAddress(updatedEmployee.getEmployeeAddress());

		repository.deleteByEmployeeId(id);
		repository.save(e);
		return "Employee updated successfully";
	}

	@DeleteMapping("/employees/{id}")
	public String deleteEmployeeById(@PathVariable String id) {
		Optional<EmployeeEntity> empOptional = repository.findByEmployeeId(id);
		if (empOptional.isEmpty()) {
			throw new EmployeeNotFoundException("No employee found");
		}

		repository.deleteByEmployeeId(id);
		return "Employee deleted successfully from the list!";
	}

}
