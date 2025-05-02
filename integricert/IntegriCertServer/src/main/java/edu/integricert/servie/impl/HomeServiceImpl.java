package edu.integricert.servie.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.integricert.model.Certificate;
import edu.integricert.model.College;
import edu.integricert.model.Company;
import edu.integricert.model.Employee;
import edu.integricert.model.Student;
import edu.integricert.model.User;
import edu.integricert.model.constants.UserRole;
import edu.integricert.repository.UserRepository;
import edu.integricert.service.CertificateService;
import edu.integricert.service.CollegeService;
import edu.integricert.service.CompanyService;
import edu.integricert.service.EmployeeService;
import edu.integricert.service.HomeService;
import edu.integricert.service.JwtService;
import edu.integricert.service.StudentService;
import edu.integricert.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final ResourceLoader resourceLoader;
	private final UserService userService;
	private final CollegeService collegeService;
	private final CertificateService certificateService;
	private final StudentService studentService;
	private final CompanyService companyService;
	private final EmployeeService employeeService;

	@Override
	public List<Student> findAllStudents() {
		return studentService.findAll();
	}

	@Override
	public Student findByIdStudent(Integer id) {
		return studentService.findById(id);
	}

	@Override
	public List<Certificate> findAllCertificates() {
		return certificateService.findAll();
	}

	@Override
	public Certificate findByIdCertificate(Integer id) {
		return certificateService.findById(id);
	}

	@Override
	public List<College> findAllColleges() {
		return collegeService.findAll();
	}

	@Override
	public College findByIdCollege(Integer id) {
		return collegeService.findById(id);
	}

	@Override
	public List<Company> findAllCompanies() {
		return companyService.findAll();
	}

	@Override
	public Company findByIdCompany(Integer id) {
		return companyService.findById(id);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeService.findAll();
	}

	@Override
	public Employee findByIdEmployee(Integer id) {
		return employeeService.findById(id);
	}

	@Override
	@Transactional
	public void deleteByCollegeId(Integer collegeId) {
		List<Student> studentsWithCertificate = studentService.findAllStudentsWithCertificate(collegeId);
		if (CollectionUtils.isNotEmpty(studentsWithCertificate)) {
			for (Student student : studentsWithCertificate) {
				Integer certificateId = student.getCertificate().getId();
				certificateService.deleteById(certificateId);
			}
		}
		studentService.deleteStudentsByCollegeId(collegeId);
		
		collegeService.deleteById(collegeId);
	}
	
	@Override
	@Transactional
	public void deleteByCompanyId(Integer companyId) {
		List<Employee> employeesWithCertificate = employeeService.findAllEmployeesWithCertificate(companyId);
		if (CollectionUtils.isNotEmpty(employeesWithCertificate)) {
			for (Employee employee : employeesWithCertificate) {
				Integer certificateId = employee.getCertificate().getId();
				certificateService.deleteById(certificateId);
			}
		}
		employeeService.deleteEmployeesByCompanyId(companyId);
		
		companyService.deleteById(companyId);
	}
	
	@Override
	@SuppressWarnings("resource")
	public void loadIntegriCertData(String userEmail) {
		try {
			Resource resource = resourceLoader.getResource("classpath:data/integricert_data.xlsx");
			for (Sheet sheet : new XSSFWorkbook(resource.getInputStream())) {
				loadUsers(sheet, userEmail);

				loadColleges(sheet, userEmail);
				loadStudents(sheet, userEmail);

				loadCompanies(sheet, userEmail);
				loadEmployees(sheet, userEmail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadUsers(Sheet sheet, String userEmail) {
		String sheetName = sheet.getSheetName();
		int numberOfRows = sheet.getPhysicalNumberOfRows();

		if ("users".equals(sheetName)) {
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < numberOfRows; i++) {
				try {
					Row row = sheet.getRow(i);
					if (i == 0) {
						for (Cell cell : row) {
							map.put(cell.getStringCellValue(), cell.getColumnIndex());
						}
					} else {
						String name = getCell(map, row, "Full Name");
						String dateOfBirth = getCell(map, row, "Date of Birth");
						String email = getCell(map, row, "Email");
						String mobile = getCell(map, row, "Phone Number");
						String address = getCell(map, row, "Address");
						String username = getCell(map, row, "Username");
						String password = getCell(map, row, "Password");
						String role = getCell(map, row, "Role");

						User user = User.builder().name(name).dateOfBirth(dateOfBirth).email(email).mobile(mobile).address(address).userName(username).password(passwordEncoder.encode(password))
								.role(UserRole.valueOf(role)).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
						userService.save(user);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void loadColleges(Sheet sheet, String userEmail) {
		String sheetName = sheet.getSheetName();
		int numberOfRows = sheet.getPhysicalNumberOfRows();

		if ("colleges".equals(sheetName)) {
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < numberOfRows; i++) {
				try {
					Row row = sheet.getRow(i);
					if (i == 0) {
						for (Cell cell : row) {
							map.put(cell.getStringCellValue(), cell.getColumnIndex());
						}
					} else {
						String name = getCell(map, row, "College_Name");
						String location = getCell(map, row, "Location");
						String registrationId = getCell(map, row, "Registration_ID");
						String establishedYear = getCell(map, row, "Established_Year");
						String mobile = getCell(map, row, "Mobile");
						String email = getCell(map, row, "Email");

						College college = College.builder().name(name).location(location).registrationId(registrationId).establishedYear(Integer.valueOf(establishedYear)).mobile(mobile).email(email)
								.createdAt(LocalDateTime.now()).createdBy(userEmail).build();
						collegeService.save(college);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void loadStudents(Sheet sheet, String userEmail) {
		String sheetName = sheet.getSheetName();
		int numberOfRows = sheet.getPhysicalNumberOfRows();

		if ("students".equals(sheetName)) {
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < numberOfRows; i++) {
				try {
					Row row = sheet.getRow(i);
					if (i == 0) {
						for (Cell cell : row) {
							map.put(cell.getStringCellValue(), cell.getColumnIndex());
						}
					} else {
						String name = getCell(map, row, "Name");
						String gender = getCell(map, row, "Gender");
						String collegeName = getCell(map, row, "College_Name");
						String course = getCell(map, row, "Course");
						String email = getCell(map, row, "Email");
						String mobile = getCell(map, row, "Phone_Number");
						String address = getCell(map, row, "Address");
						String academicYear = getCell(map, row, "Academic_Year");
						String courseResult = getCell(map, row, "Course_Result");
						String degree = getCell(map, row, "Degree");
						String dob = getCell(map, row, "DOB");

						College college = collegeService.findByName(collegeName);

						Student medicine = Student.builder().name(name).gender(gender).college(college).course(course).email(email).mobile(mobile).address(address).academicYear(academicYear)
								.courseResult(courseResult).degree(degree).dob(dob).createdAt(LocalDateTime.now()).createdBy(userEmail).build();
						studentService.save(medicine);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void loadCompanies(Sheet sheet, String userEmail) {
		String sheetName = sheet.getSheetName();
		int numberOfRows = sheet.getPhysicalNumberOfRows();

		if ("companies".equals(sheetName)) {
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < numberOfRows; i++) {
				try {
					Row row = sheet.getRow(i);
					if (i == 0) {
						for (Cell cell : row) {
							map.put(cell.getStringCellValue(), cell.getColumnIndex());
						}
					} else {
						String name = getCell(map, row, "Company_Name");
						String location = getCell(map, row, "Location");
						String registrationId = getCell(map, row, "Registration_ID");
						String establishedYear = getCell(map, row, "Established_Year");
						String mobile = getCell(map, row, "Mobile");
						String email = getCell(map, row, "Email");

						Company company = Company.builder().name(name).location(location).registrationId(registrationId).establishedYear(Integer.valueOf(establishedYear)).mobile(mobile).email(email)
								.createdAt(LocalDateTime.now()).createdBy(userEmail).build();
						companyService.save(company);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void loadEmployees(Sheet sheet, String userEmail) {
		String sheetName = sheet.getSheetName();
		int numberOfRows = sheet.getPhysicalNumberOfRows();

		if ("employees".equals(sheetName)) {
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < numberOfRows; i++) {
				try {
					Row row = sheet.getRow(i);
					if (i == 0) {
						for (Cell cell : row) {
							map.put(cell.getStringCellValue(), cell.getColumnIndex());
						}
					} else {
						String name = getCell(map, row, "Name");
						String gender = getCell(map, row, "Gender");
						String companyName = getCell(map, row, "Company_Name");
						String joiningDate = getCell(map, row, "Joining_Date");
						String email = getCell(map, row, "Email");
						String mobile = getCell(map, row, "Phone_Number");
						String address = getCell(map, row, "Address");
						String designation = getCell(map, row, "Designation");
						String department = getCell(map, row, "Department");
						String dob = getCell(map, row, "DOB");

						Company company = companyService.findByName(companyName);

						Employee employee = Employee.builder().name(name).gender(gender).company(company).joiningDate(joiningDate).email(email).mobile(mobile).address(address).designation(designation)
								.department(department).dob(dob).createdAt(LocalDateTime.now()).createdBy(userEmail).build();
						employeeService.save(employee);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private LocalDate getLocalDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.parse(dateStr, formatter);
		return localDate;
	}

	private String getCell(Map<String, Integer> map, Row row, String columnName) {
		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(row.getCell(map.get(columnName)));
	}

}
