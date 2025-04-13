package business.service.employee;

import business.dao.employee.EmployeeDao;
import business.dao.employee.EmployeeDaoImp;
import business.model.Employee;

import java.util.List;

public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeServiceImp() {
        employeeDao = new EmployeeDaoImp();
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return employeeDao.deleteEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> searchEmployeeByName(Employee employee) {
        return employeeDao.searchEmployeeByName(employee);
    }

    @Override
    public List<Employee> searchEmployeeByAgeRange(int min_age, int max_age) {
        return employeeDao.searchEmployeeByAgeRange(min_age, max_age);
    }

    @Override
    public List<Employee> sortEmployeeBySalary(Employee employee) {
        return employeeDao.sortEmployeeBySalary(employee);
    }

    @Override
    public List<Employee> sortEmployeeByName(Employee employee) {
        return employeeDao.sortEmployeeByName(employee);
    }

    @Override
    public List<Employee> getEmployeeByPage(int page, int pageSize) {
        return employeeDao.getEmployeeByPage(page, pageSize);
    }
}
