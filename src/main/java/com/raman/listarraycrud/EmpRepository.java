package com.raman.listarraycrud;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
@Repository
public class EmpRepository {

	public List<Employee> employeeList = new ArrayList<Employee>();

	int key = 0;
	// VIEW ALL EMPLOYEE
	public List<Employee> findAll() {
		return employeeList;
	}
	// MAX ID
	private int getMaxId() {

		Optional<Employee> highestidwrapper = employeeList.stream().collect(
				Collectors.maxBy(Comparator.comparingInt(Employee::getId)));
		Employee highestid = highestidwrapper.get();
		return highestid.getId();
	}
	// SAVE NEW EMPLOYEE
	public Employee save(Employee employee) {
		key = getMaxId() == 0 ? 0 : getMaxId();
		if (employee.getId() == 0) {
			employee.setId(++key);
		}
		employeeList.add(employee);
		return employee;
	}
	// UPDATE EMPLOYEE
	public Employee save(Integer id, Employee employee) {
		Employee empl = employeeList.stream().filter(e -> e.getId() == id)
				.findAny().get();
		if (empl.getId() != 0 || empl != null) {
			empl.setName(employee.getName());
			empl.setAge(employee.getAge());
			empl.setGender(employee.getGender());
			empl.setDepartment(employee.getDepartment());
			empl.setYearOfJoining(employee.getYearOfJoining());
			empl.setSalary(employee.getSalary());
		}
		return empl;
	}
	// Delete Employee by ID
	public void deleteById(int EmployeeId) {
		Employee empl = employeeList.stream()
				.filter(e -> e.getId() == EmployeeId).findAny().get();
		employeeList.remove(empl);
	}
	// View Employee by ID
	public Employee findById(int id) {
		Employee emp = employeeList.stream().filter(e -> e.getId() == id)
				.findAny().get();
		return emp;
	}
	// VIEW LIST GENDER WISE
	public Map<String, Long> findAllGenderWise() {
		Map<String, Long> empGender = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getGender, Collectors.counting()));
		return empGender;
	}
	// VIEW ALL DEPARTMENT NAMES
	public List findAllDepartment() {
		List department = employeeList.stream().map(Employee::getDepartment)
				.distinct().toList();
		return department;
	}
	// AVERAGE AGE OF MALE AND FEMALE EMPLOYEES
	public Map<String, Double> findAvgGenderWise() {
		Map<String, Double> empGender = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.averagingInt(Employee::getAge)));
		return empGender;
	}
	// HIGHEST PAID EMPLOYEE
	public Optional<Employee> findMaxSal() {
		Optional<Employee> emp = employeeList.stream().collect(Collectors
				.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		return emp;
	}
	//Get the name of all employees who have joined after 2015?
	public List findByYoJ(int yoj) {
		List emp = employeeList.stream().filter(e -> e.getYearOfJoining() > yoj)
				.map(Employee::getName).toList();
		return emp;
	}
	public Map<String, Long> findEmpDep() {
		Map<String, Long> emp = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.counting()));
		return emp;
	}

	public Map<String, Double> findAvgDepWiseSalary() {
		Map<String, Double> emp = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.averagingDouble(Employee::getSalary)));
		return emp;
	}
	public Optional<Employee> findYoungestEmpOfMinAgeDep() {
		Optional<Employee> emp = employeeList.stream()
				.filter(e -> e.getGender() == "Male"
						&& e.getDepartment() == "Product Development")
				.min(Comparator.comparingInt(Employee::getAge));
		return emp;
	}

	public Optional<Employee> FindOldestEmp() {
		Optional<Employee> emp = employeeList.stream()
				.sorted(Comparator.comparingInt(Employee::getYearOfJoining))
				.findFirst();
		return emp;
	}

	public Map<String, Long> findEmpGenderInSalesNMarketing() {
		Map<String, Long> emp = employeeList.stream()
				.filter(e -> e.getDepartment() == "Sales And Marketing")
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.counting()));
		return emp;
	}

	public Map<String, Double> findEmpAvgSalaryGenderWise() {
		Map<String, Double> emp = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.averagingDouble(Employee::getSalary)));
		return emp;
	}

	public Map<String, List<Employee>> findNamesEmpDeptWise() {
		Map<String, List<Employee>> emp = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
		return emp;
	}
	public DoubleSummaryStatistics findTotalSalaryAndAverageSalary() {
		DoubleSummaryStatistics emp = employeeList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));
		return emp;
	}

	public Map<Boolean, List<Employee>> findPartitionByAge() {
		Map<Boolean, List<Employee>> emp = employeeList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() > 25));
		return emp;
	}

	public Optional<Employee> findOldestEmpAgeWise() {
			Optional<Employee> emp = employeeList.stream().max(Comparator.comparingInt(Employee::getAge));
		return emp;
	}
	
}
