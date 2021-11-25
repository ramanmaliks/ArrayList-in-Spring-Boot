# SPRING BOOT CRUD DEMO USING ARRAY LIST AS DATABASE

# Requirement 
## Create a Array List that on startup of Application get loaded with database.
## Problem to be solved using Array List

1.  Real Time Queries On List of Employees
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
1.	Create a new spring boot project 
2.	Add Dependencies   

	i.	Starter Web // As we are making a Rest Api 
	
	ii. Remaining all the default dependencies (which are by default added in the projects pom.xml)
3.	Application.properties

	i.	server.port=8080 // for the Tomcat server port address.
4.	Create Packages

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

# **VIEW ALL EMPLOYEES LIST** <http://127.0.0.1:8080/all>
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
	# Finding Maximum ID
	private int getMaxId() {
		Optional<Employee> highestidwrapper = employeeList.stream().collect(
				Collectors.maxBy(Comparator.comparingInt(Employee::getId)));
		Employee highestid = highestidwrapper.get();
		return highestid.getId();
	}
	# Save Method
	public Employee save(Employee employee) {
		key = getMaxId() == 0 ? 0 : getMaxId();
		if (employee.getId() == 0) {
			employee.setId(++key);
		}
		employeeList.add(employee);
		return employee;
	}	
```

------------------------------------------------------------






