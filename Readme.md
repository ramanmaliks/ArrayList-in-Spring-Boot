# SPRING BOOT CRUD DEMO USING ARRAY LIST AS DATABASE

# Requirement 
## Create a Array List that on startup of Application get loaded with database.
## Problem to be solved using Array List
# Real Time Queries On List of Employees
1.  CRUD Operations CREATE , READ, UPDATE, DELETE ON EMPLOYEES IN ARRAY LIST
2.  How many Male and female employees are there in the organization?
3.	Print the name of all departments in the organization?
4.	What is the average age of male and female employees
5.	Get the details of Highest paid employee in the Organization
6.	Get the name of all employees who have joined after 2015?
7.	Count the number of employees in each department ?
8.	What is the average salary of each department
9.	Get the details of youngest male employee in the product development department
10.	who has the most working experience in the organizarion
11.	how many male and female employees are there in the sales and marketing team
12.	what is the average salary of male and female employees
13.	List down the names of all employees in each department 
14.	what is the average salay and total salary of the whole organization
15.	Separate the employees who are younger or equal to 25 years from thos employees who are older than 25 years
16.	who is the oldest employee in the organization? what is his age and department he belongs to

# Steps
1.	**Create a new spring boot project** 
2.	**Add Dependencies**   

	i.	Starter Web // As we are making a Rest Api 
	
	ii. Remaining all the default dependencies (which are by default added in the projects pom.xml)
3.	**Application.properties**

	i.	server.port=8080 // for the Tomcat server port address.
4.	**Create Packages**

	i	controller package
	
	ii	model package
	
	iii	repository package
	
	iv	service package

# Create Employee Enity Class
```
public class Employee {
	private int id;   
    private String name;
    private int age;     
    private String gender;     
    private String department;     
    private int yearOfJoining;     
    private double salary;
#setter & getter
#Constructor
```

# Create Employee Repository Class
## create ArrayList for EmployeeList
```
@Repository
public class EmpRepository {
	public List<Employee> employeeList = new ArrayList<Employee>();
```

# In main Application File, implement CommandLineRunner , autowired employee repository and in run method add data to arraylist
```
public class ListArrayCrudDemoApplication implements CommandLineRunner {
	@Autowired
	private EmpRepository empRep;
```
```
@Override
	public void run(String... args) throws Exception {
		
		empRep.employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		empRep.employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		empRep.employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		empRep.employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		empRep.employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		empRep.employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		empRep.employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		empRep.employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		empRep.employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		empRep.employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		empRep.employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		empRep.employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		empRep.employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		empRep.employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		empRep.employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		empRep.employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		empRep.employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
		
	}
```

# Create Controller Class
## in this Http address will be handled

```
@RestController
public class Controller {
	@Autowired
	private EmpSerivce empSer;
```

#Create Employee Service Class
```
@Service
public class EmpSerivce {
	@Autowired
	private EmpRepository empRepo;
```

------------------------------------------------------------

# VIEW ALL EMPLOYEES LIST <http://127.0.0.1:8080/all>
### in Employee Controller Class
``` 
@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Employee> getAllEmp() {
		return empSer.getAllEmp();
	}
```
### in Employee Service Class
```
public List<Employee> getAllEmp() {
		return empRepo.findAll();	}
```
### in Employee Repository
```
	public List<Employee> findAll() {
		return employeeList;
	}	
```

------------------------------------------------------------
# Add New Employee in the Employee Array List
### in Employee Controller Class
```
@RequestMapping(path = "/add", method = RequestMethod.POST)
	public Employee addEmp(@RequestBody Employee employee) {
		return empSer.addNewEmp(employee);
	}
```
### in Employee Service Class
```
public Employee addNewEmp(Employee employee) {
		return empRepo.save(employee);	}
```

### in Employee Repository
```
		public Employee save(Employee employee) {
		key = getMaxId() == 0 ? 0 : getMaxId();
		if (employee.getId() == 0) {
			employee.setId(++key);	}
		employeeList.add(employee);
		return employee;						}
```

------------------------------------------------------------


# Update Employee in the Employees Array List
### in Employee Controller Class
```
@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	public Employee updateEmp(@PathVariable("id") Integer id,
			@RequestBody Employee employee) {
		return empSer.updateEmp(id, employee);
	}
```
### in Employee Service Class
```
public Employee updateEmp(Integer id, Employee employee) {
		return empRepo.save(id, employee);
	}
```
### in Employee Repository
```
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
```
------------------------------------------------------------


# Delete a Employee in the Employees Array List
### in Employee Controller Class
```
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteEmpById(@PathVariable("id") Integer id) {
	empSer.deleteEmpById(id);
```
### in Employee Service Class
```
public void deleteEmpById(Integer id) {
		empRepo.deleteById(id);	}
```
### in Employee Repository
```
public void deleteById(int EmployeeId) {
		Employee empl = employeeList.stream()
				.filter(e -> e.getId() == EmployeeId).findAny().get();
		employeeList.remove(empl);
	}
```
------------------------------------------------------------
# View Employee By ID in the Employees Array List
### in Employee Controller Class
```
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public Employee getEmpById(@PathVariable("id") Integer id) {
		return empSer.getEmpById(id);
	}
```
### in Employee Service Class
```
public Employee getEmpById(int id) {
		return empRepo.findById(id);	}
```
### in Employee Repository
```
	public Employee findById(int id) {
		Employee emp = employeeList.stream().filter(e -> e.getId() == id)
				.findAny().get();
		return emp;	}
```
------------------------------------------------------------
# How many Male and female employees are there in the organization?
### in Employee Controller Class
```
	@RequestMapping(value = "/emp/genders", method = RequestMethod.GET)
	public Map<String, Long> getGenderWise() {
		return empSer.getGenderWise();	}
```
### in Employee Service Class
```
	public Map<String, Long> getGenderWise() {
		return empRepo.findAllGenderWise();	}
```
### in Employee Repository
```
	public Map<String, Long> findAllGenderWise() {
		Map<String, Long> empGender = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getGender, Collectors.counting()));
		return empGender;	}
```
------------------------------------------------------------
# Print the name of all departments in the organization?
### in Employee Controller Class
```
	@RequestMapping(value = "/emp/dept", method = RequestMethod.GET)
	public List getAllDept() {
		return empSer.getAllDept();
	}
```
### in Employee Service Class
```
	public List getAllDept() {
		return empRepo.findAllDepartment();
	}
```
### in Employee Repository
```
	public List findAllDepartment() {
		List department = employeeList.stream().map(Employee::getDepartment)
				.distinct().toList();
		return department;
	}
```
------------------------------------------------------------
# What is the average age of male and female employees
### in Employee Controller Class
```
	@RequestMapping(value = "/emp/avg", method = RequestMethod.GET)
	public Map<String, Double> getAvgEmp() {
		return empSer.getAvgEmp();
	}
```
### in Employee Service Class
```
	public Map<String, Double> getAvgEmp() {
		return empRepo.findAvgGenderWise();
	}
```
### in Employee Repository
```
	public Map<String, Double> findAvgGenderWise() {
		Map<String, Double> empGender = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.averagingInt(Employee::getAge)));
		return empGender;
	}
```
------------------------------------------------------------
# Get the details of Highest paid employee in the Organization
### in Employee Controller Class
```
	@RequestMapping(value = "/emp/sal", method = RequestMethod.GET)
	public Optional<Employee> getMaxSal() {
		return empSer.getMaxSal();
	}
```
### in Employee Service Class
```
	public Optional<Employee> getMaxSal() {		
		return empRepo.findMaxSal();
	}
```
### in Employee Repository
```
	public Optional<Employee> findMaxSal() {
		Optional<Employee> emp = employeeList.stream().collect(Collectors
				.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		return emp;
	}
```
------------------------------------------------------------
# Get the name of all employees who have joined after 2015?
### in Employee Controller Class
```
	@RequestMapping(value = "/emp/yoj/{yoj}", method = RequestMethod.GET)
	public List getYoj(@PathVariable("yoj") int yoj) {
		return empSer.getYoj(yoj);
	}
```
### in Employee Service Class
```
public List getYoj(int yoj) {
		return empRepo.findByYoJ(yoj);
	}
```
### in Employee Repository
```
	//Get the name of all employees who have joined after 2015?
	public List findByYoJ(int yoj) {
		List emp = employeeList.stream().filter(e -> e.getYearOfJoining() > yoj)
				.map(Employee::getName).toList();
		return emp;
	}
```
------------------------------------------------------------
# Count the number of employees in each department ?
### in Employee Controller Class
```
@RequestMapping(value = "/emp/dept-emp", method = RequestMethod.GET)
	public Map<String, Long> getDepEmpWise() {
		return empSer.getDepEmpWise();
	}
```
### in Employee Service Class
```
public Map<String, Long> getDepEmpWise() {
		return empRepo.findEmpDep();
	}
```
### in Employee Repository
```
public Map<String, Long> findEmpDep() {
		Map<String, Long> emp = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.counting()));
		return emp;
	}
```
------------------------------------------------------------
# What is the average salary of each department
### in Employee Controller Class
```
@RequestMapping(value = "/emp/dept-avgsal", method = RequestMethod.GET)
	public Map<String, Double> getDepAvgSal() {
		return empSer.getDepAvgSal();
	}
```
### in Employee Service Class
```
	public Map<String, Double> getDepAvgSal() {
				return empRepo.findAvgDepWiseSalary();
	}
```
### in Employee Repository
```
public Map<String, Double> findAvgDepWiseSalary() {
		Map<String, Double> emp = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.averagingDouble(Employee::getSalary)));
		return emp;
	}
```
------------------------------------------------------------
# who has the most working experience in the organization
### in Employee Controller Class
```
@RequestMapping(value = "/emp/oldest", method = RequestMethod.GET)
	public Optional<Employee> getOldestEmp() {
		return empSer.getOldestEmp();
	}
```
### in Employee Service Class
```
public Optional<Employee> getOldestEmp() {
		return empRepo.FindOldestEmp();
	}
```
### in Employee Repository
```
public Optional<Employee> FindOldestEmp() {
		Optional<Employee> emp = employeeList.stream()
				.sorted(Comparator.comparingInt(Employee::getYearOfJoining))
				.findFirst();
		return emp;
	}
```
------------------------------------------------------------
# Get the details of youngest male employee in the product development department
### in Employee Controller Class
```
@RequestMapping(value = "/emp/youngest", method = RequestMethod.GET)
	public Optional<Employee> getYoungestEmp() {
		return empSer.getYoungestEmp();
```
### in Employee Service Class
```
public Optional<Employee> getYoungestEmp() {
		return empRepo.findYoungestEmpOfMinAgeDep();
	}
```
### in Employee Repository
```
	public Optional<Employee> findYoungestEmpOfMinAgeDep() {
		Optional<Employee> emp = employeeList.stream()
				.filter(e -> e.getGender() == "Male"
						&& e.getDepartment() == "Product Development")
				.min(Comparator.comparingInt(Employee::getAge));
		return emp;
	}
```
------------------------------------------------------------
# how many male and female employees are there in the sales and marketing team
### in Employee Controller Class
```
@RequestMapping(value = "/emp/salesandmarketing", method = RequestMethod.GET)
	public Map<String, Long> getEmpInSalesAndMarketingGenderWise() {
		return empSer.getEmpInSalesAndMarketingGenderWise();
	}
```
### in Employee Service Class
```
public Map<String, Long> getEmpInSalesAndMarketingGenderWise() {
		return empRepo.findEmpGenderInSalesNMarketing();
	}
```
### in Employee Repository
```
	public Map<String, Long> findEmpGenderInSalesNMarketing() {
		Map<String, Long> emp = employeeList.stream()
				.filter(e -> e.getDepartment() == "Sales And Marketing")
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.counting()));
		return emp;
	}
```
------------------------------------------------------------
# what is the average salary of male and female employees
### in Employee Controller Class
```
@RequestMapping(value = "/emp/avgsalary", method = RequestMethod.GET)
	public Map<String, Double> getEmpAvgSalary() {
		return empSer.getEmpAvgSalary();
```
### in Employee Service Class
```
public Map<String, Double> getEmpAvgSalary() {
		return empRepo.findEmpAvgSalaryGenderWise();
	}
```
### in Employee Repository
```
public Map<String, Double> findEmpAvgSalaryGenderWise() {
		Map<String, Double> emp = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.averagingDouble(Employee::getSalary)));
		return emp;
	}
```
------------------------------------------------------------
# List down the names of all employees in each department 
### in Employee Controller Class
```
@RequestMapping(value = "/emp/details", method = RequestMethod.GET)
	public Map<String, List<Employee>> getEmpNamesDept() {
		return empSer.getEmpNamesDept();
	}
```
### in Employee Service Class
```
public Map<String, List<Employee>> getEmpNamesDept() {
		return empRepo.findNamesEmpDeptWise();
	}
```
### in Employee Repository
```
public Map<String, List<Employee>> findNamesEmpDeptWise() {
		Map<String, List<Employee>> emp = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
		return emp;
	}
```
------------------------------------------------------------
# what is the average salary and total salary of the whole organization
### in Employee Controller Class
```
@RequestMapping(value = "/emp/orgavg", method = RequestMethod.GET)
	public DoubleSummaryStatistics getTotalnAverageSal() {
		return empSer.getTotalnAverageSal();
```
### in Employee Service Class
```
public DoubleSummaryStatistics getTotalnAverageSal() {
		return empRepo.findTotalSalaryAndAverageSalary();
	}
```
### in Employee Repository
```
public DoubleSummaryStatistics findTotalSalaryAndAverageSalary() {
		DoubleSummaryStatistics emp = employeeList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));
		return emp;
	}
```
------------------------------------------------------------
# Separate the employees who are younger or equal to 25 years from thos employees who are older than 25 years
### in Employee Controller Class
```
@RequestMapping(value = "/emp/part", method = RequestMethod.GET)
	public Map<Boolean, List<Employee>> getPartitionByEmpAge() {
		return empSer.getPartitionByEmpAge();
	}
```
### in Employee Service Class
```
public Map<Boolean, List<Employee>> getPartitionByEmpAge() {
		return empRepo.findPartitionByAge();
	}
```
### in Employee Repository
```
public Map<Boolean, List<Employee>> findPartitionByAge() {
		Map<Boolean, List<Employee>> emp = employeeList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() > 25));
		return emp;
	}
```
------------------------------------------------------------
# who is the oldest employee in the organization? what is his age and department he belongs to
### in Employee Controller Class
```
@RequestMapping(value = "/emp/oldemp", method = RequestMethod.GET)
	public Optional<Employee> getOldestEmpAgeWise() {
		return empSer.getOldestEmpAgeWise();
	}
```
### in Employee Service Class
```
	public Optional<Employee> getOldestEmpAgeWise() {
		return empRepo.findOldestEmpAgeWise();
	}
```
### in Employee Repository
```
public Optional<Employee> findOldestEmpAgeWise() {
			Optional<Employee> emp = employeeList.stream().max(Comparator.comparingInt(Employee::getAge));
		return emp;
	}
```






