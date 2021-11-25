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

	public Map<String, Long> getGenderWise() {
		return empRepo.findAllGenderWise();
	}
	public List getAllDept() {
		return empRepo.findAllDepartment();
	}
	public Map<String, Double> getAvgEmp() {
		return empRepo.findAvgGenderWise();
	}
	public Optional<Employee> getMaxSal() {
		
		return empRepo.findMaxSal();
	}
	public List getYoj(int yoj) {
		return empRepo.findByYoJ(yoj);
	}
	public Map<String, Long> getDepEmpWise() {
		return empRepo.findEmpDep();
	}
	public Map<String, Double> getDepAvgSal() {
				return empRepo.findAvgDepWiseSalary();
	}
	public Optional<Employee> getYoungestEmp() {
		return empRepo.findYoungestEmpOfMinAgeDep();
	}
	public Optional<Employee> getOldestEmp() {
		return empRepo.FindOldestEmp();
	}
	public Map<String, Long> getEmpInSalesAndMarketingGenderWise() {
		return empRepo.findEmpGenderInSalesNMarketing();
	}
	public Map<String, Double> getEmpAvgSalary() {
		return empRepo.findEmpAvgSalaryGenderWise();
	}
	public Map<String, List<Employee>> getEmpNamesDept() {
		return empRepo.findNamesEmpDeptWise();
	}
	public DoubleSummaryStatistics getTotalnAverageSal() {
		return empRepo.findTotalSalaryAndAverageSalary();
	}
	public Map<Boolean, List<Employee>> getPartitionByEmpAge() {
		return empRepo.findPartitionByAge();
	}
	public Optional<Employee> getOldestEmpAgeWise() {
		return empRepo.findOldestEmpAgeWise();
	}
	
	

}
