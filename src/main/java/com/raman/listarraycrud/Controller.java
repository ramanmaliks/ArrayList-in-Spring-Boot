package com.raman.listarraycrud;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private EmpSerivce empSer;
	//View Employee List
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Employee> getAllEmp() {
		return empSer.getAllEmp();
	}
	// Add Employee
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public Employee addEmp(@RequestBody Employee employee) {
		return empSer.addNewEmp(employee);
	}
	// Update Employee
	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	public Employee updateEmp(@PathVariable("id") Integer id,
			@RequestBody Employee employee) {
		return empSer.updateEmp(id, employee);
	}
	// Delete Employee
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteEmpById(@PathVariable("id") Integer id) {
		empSer.deleteEmpById(id);
	}
	// View Employee By Id
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public Employee getEmpById(@PathVariable("id") Integer id) {
		return empSer.getEmpById(id);
	}
	//Employee List Gender Wise
	@RequestMapping(value = "/emp/genders", method = RequestMethod.GET)
	public Map<String, Long> getGenderWise() {
		return empSer.getGenderWise();
	}
	// VIEW NAMES OF DEPARTMENT
	@RequestMapping(value = "/emp/dept", method = RequestMethod.GET)
	public List getAllDept() {
		return empSer.getAllDept();
	}
	// AVERAGE AGE OF MALE AND FEMALE
	@RequestMapping(value = "/emp/avg", method = RequestMethod.GET)
	public Map<String, Double> getAvgEmp() {
		return empSer.getAvgEmp();
	}
	// HIGHEST PAID EMPLOYEE
	@RequestMapping(value = "/emp/sal", method = RequestMethod.GET)
	public Optional<Employee> getMaxSal() {
		return empSer.getMaxSal();
	}
	// Get the name of all employees who have joined after 2015?
	@RequestMapping(value = "/emp/yoj/{yoj}", method = RequestMethod.GET)
	public List getYoj(@PathVariable("yoj") int yoj) {
		return empSer.getYoj(yoj);
	}
	// Count the number of employees in each department
	@RequestMapping(value = "/emp/dept-emp", method = RequestMethod.GET)
	public Map<String, Long> getDepEmpWise() {
		return empSer.getDepEmpWise();
	}
	// What is the average salary of each department
	@RequestMapping(value = "/emp/dept-avgsal", method = RequestMethod.GET)
	public Map<String, Double> getDepAvgSal() {
		return empSer.getDepAvgSal();
	}
	// youngest male employee in the product development department
	@RequestMapping(value = "/emp/youngest", method = RequestMethod.GET)
	public Optional<Employee> getYoungestEmp() {
		return empSer.getYoungestEmp();
	}
	// Employee who has most working experience in the organization
	@RequestMapping(value = "/emp/oldest", method = RequestMethod.GET)
	public Optional<Employee> getOldestEmp() {
		return empSer.getOldestEmp();
	}
	// How many male and female employees are there in the sales and marketing team
	@RequestMapping(value = "/emp/salesandmarketing", method = RequestMethod.GET)
	public Map<String, Long> getEmpInSalesAndMarketingGenderWise() {
		return empSer.getEmpInSalesAndMarketingGenderWise();
	}
	// the average salary of male and female employees
	@RequestMapping(value = "/emp/avgsalary", method = RequestMethod.GET)
	public Map<String, Double> getEmpAvgSalary() {
		return empSer.getEmpAvgSalary();
	}
	// List down the names of all employees in each department
	@RequestMapping(value = "/emp/details", method = RequestMethod.GET)
	public Map<String, List<Employee>> getEmpNamesDept() {
		return empSer.getEmpNamesDept();
	}
	// average salary and total salary of the whole organization
	@RequestMapping(value = "/emp/orgavg", method = RequestMethod.GET)
	public DoubleSummaryStatistics getTotalnAverageSal() {
		return empSer.getTotalnAverageSal();
	}
	// Separate the employees who are younger or equal to 25 years from those
	// employees who are older than 25 years
	@RequestMapping(value = "/emp/part", method = RequestMethod.GET)
	public Map<Boolean, List<Employee>> getPartitionByEmpAge() {
		return empSer.getPartitionByEmpAge();
	}

	// the oldest employee in the organization? What is his age and which
	// department he belongs to
	@RequestMapping(value = "/emp/oldemp", method = RequestMethod.GET)
	public Optional<Employee> getOldestEmpAgeWise() {
		return empSer.getOldestEmpAgeWise();
	}
}
