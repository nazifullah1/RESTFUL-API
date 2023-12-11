package com.zebraIntern.rest.restfulwebservices.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
//	private static int countEmployee = 0;
//	
//	private static List<Employee> employees = new ArrayList<>();
//	
//	
//	static {
//		
//		employees.add(new Employee(++countEmployee, "Nazifullah", "nazifullah.hanan@zebra.com", "3777 karl dr"));
//		employees.add(new Employee(++countEmployee, "Ravi", "ravi.ravi@zebra.com", "223 Manzanita ave"));
//		employees.add(new Employee(++countEmployee, "Joh", "john.john@zebra.com", "1255 Fulton ave"));
//		
//	}
//	
//	public List<Employee> getAllEmployee(){
//		return employees;
//	}
//	
//	public Employee getOneEmployee(int id) {
//		
//		for (Employee emp : employees) {
//			if(emp.getEmployeeId() == id) {
//				return emp;
//			}
//		}
//		return null;
//		
//	}
//	
//	public Employee createEmployee(Employee employee) {
//		employee.setEmployeeId(++countEmployee);
//		employees.add(employee);
//		return employee;
//		
//	}
//	
//	
//	public Employee updateEmployee(int id, Employee employee) {
//		Employee emp = getOneEmployee(id);
//		
//		if(emp != null) {
//			emp.setEmployeeName(employee.getEmployeeName());
//			emp.setEmployeeEmailAddress(employee.getEmployeeEmailAddress());
//			emp.setEmployeeAddress(employee.getEmployeeAddress());
//			return emp;
//		}
//		return null;
//	}
//	
//	public Employee deleteEmployee(int id) {
//		Employee emp = getOneEmployee(id);
//		if(emp != null) {
//			employees.remove(emp);
//			return emp;
//		}
//		return null;
//	}

}
