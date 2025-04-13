package business.dao.employee;

import business.dao.AppDao;
import business.model.Employee;

import java.util.List;

public interface EmployeeDao extends AppDao<Employee> {
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(String employeeId);
    List<Employee> searchEmployeeByName(Employee employee);
    List<Employee> searchEmployeeByAgeRange(int min_age, int max_age);
    List<Employee> sortEmployeeBySalary(Employee employee);
    List<Employee> sortEmployeeByName(Employee employee);
    List<Employee> getEmployeeByPage(int page, int pageSize);
}
