package com.raman.listarraycrud;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpSerivce {
	@Autowired
	private EmpRepository empRepo;
	public List<Employee> getAllEmp() {

		return empRepo.findAll();
	}
	public Employee addNewEmp(Employee employee) {

		return empRepo.save(employee);

	}
	
	public Employee updateEmp(Integer id, Employee employee) {
		return empRepo.save(id, employee);
	}
	
	public void deleteEmpById(Integer id) {
		empRepo.deleteById(id);
	}
	// Employee By Id
	public Employee getEmpById(int id) {
		return empRepo.findById(id);
	}
	// GENDER WISE LIST
	public Map<String, Long> getGenderWise() {
		return empRepo.findAllGenderWise();
	}
	// VIEW DEPARTMENT NAMES
	public List getAllDept() {
		return empRepo.findAllDepartment();
	}
	// AVERAGE AGE OF MALE AND FEMALE EMPLOYEES
	public Map<String, Double> getAvgEmp() {
		return empRepo.findAvgGenderWise();
	}
	// HIGHEST PAID EMPLOYEE
	public Optional<Employee> getMaxSal() {
		
		return empRepo.findMaxSal();
	}
	// Get the name of all employees who have joined after 2015?
	public List getYoj(int yoj) {
		return empRepo.findByYoJ(yoj);
	}
	// Count No of Employees Department Wise
	public Map<String, Long> getDepEmpWise() {
		return empRepo.findEmpDep();
	}
	//  average salary of each department
	public Map<String, Double> getDepAvgSal() {
				return empRepo.findAvgDepWiseSalary();
	}
	// youngest male employee in the product development department 
	public Optional<Employee> getYoungestEmp() {
		return empRepo.findYoungestEmpOfMinAgeDep();
	}
	//who has the most working experience in the organizarion
	public Optional<Employee> getOldestEmp() {
		return empRepo.FindOldestEmp();
	}
	
	//male and female employees are there in the sales and marketing team
	public Map<String, Long> getEmpInSalesAndMarketingGenderWise() {
		return empRepo.findEmpGenderInSalesNMarketing();
	}
	// the average salary of male and female employees
	public Map<String, Double> getEmpAvgSalary() {
		return empRepo.findEmpAvgSalaryGenderWise();
	}
	
	// List down the names of all employees in each department
	public Map<String, List<Employee>> getEmpNamesDept() {
		return empRepo.findNamesEmpDeptWise();
	}
	
	//average salary and total salary of the whole organization
	public DoubleSummaryStatistics getTotalnAverageSal() {
		return empRepo.findTotalSalaryAndAverageSalary();
	}
	
	//Separate the employees who are younger or equal to 25 years from thos employees who are older than 25 years
	public Map<Boolean, List<Employee>> getPartitionByEmpAge() {
		return empRepo.findPartitionByAge();
	}
	
	//who is the oldest employee in the organization? what is his age and department he belongs to
	public Optional<Employee> getOldestEmpAgeWise() {
		return empRepo.findOldestEmpAgeWise();
	}
	
	

}
