package business.dao.employee;

import business.config.ConnectionDB;
import business.model.Department;
import business.model.Employee;
import business.model.Gender;
import business.model.StatusEmp;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImp implements EmployeeDao {

    @Override
    public boolean addEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call add_employee(?,?,?,?,?,?,?,?,?,?)}");

            callSt.setString(1, employee.getEmp_name());
            callSt.setString(2, employee.getEmail());
            callSt.setString(3, employee.getPhoneNumber());
            callSt.setString(4, (employee.getGender().name()));
            callSt.setInt(5, employee.getLevelSalary());
            callSt.setDouble(6, employee.getSalary());
            callSt.setDate(7, Date.valueOf(employee.getDateOfBirth()));
            callSt.setString(8, employee.getAddress());
            callSt.setString(9, (employee.getEmp_status().name()));
            callSt.setInt(10, employee.getDepartment().getDep_id());

            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thêm nhân viên, dữ liệu đã được rollback");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_employee(?,?,?,?,?,?,?,?,?,?,?)}");

            callSt.setString(1, employee.getEmp_id());
            callSt.setString(2, employee.getEmp_name());
            callSt.setString(3, employee.getEmail());
            callSt.setString(4, employee.getPhoneNumber());
            callSt.setString(5, String.valueOf(employee.getGender()));
            callSt.setInt(6, employee.getLevelSalary());
            callSt.setDouble(7, employee.getSalary());
            callSt.setString(8, String.valueOf(employee.getDateOfBirth()));
            callSt.setString(9, employee.getAddress());
            callSt.setString(10, String.valueOf(employee.getEmp_status()));
            callSt.setInt(11, employee.getDepartment().getDep_id());
            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình cập nhật, dữ liệu đã được rollback");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Có lỗi khi thực hiện rollback: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call delete_employee(?)}");
            callSt.setString(1, employee.getEmp_id());
            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình xóa, dữ liệu đã được rollback");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_all_employee()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmp_id(rs.getString("emp_id"));
                emp.setEmp_name(rs.getString("emp_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhoneNumber(rs.getString("phoneNumber"));
                emp.setGender(Gender.valueOf(rs.getString("gender")));
                emp.setLevelSalary(rs.getInt("level_salary"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDateOfBirth(LocalDate.parse(rs.getString("dateOfBirth")));
                emp.setAddress(rs.getString("address"));
                emp.setEmp_status(StatusEmp.valueOf(rs.getString("emp_status")));
                emp.setDepartment(rs.getString("dep_id"));
                list.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Có lỗi khi thực hiện rollback: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Employee employee = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_employee_by_id(?)}");
            callSt.setString(1, employeeId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEmp_id(rs.getString("emp_id"));
                employee.setEmp_name(rs.getString("emp_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setGender(Gender.valueOf(rs.getString("gender")));
                employee.setLevelSalary(rs.getInt("level_salary"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDateOfBirth(LocalDate.parse(rs.getString("dateOfBirth")));
                employee.setAddress(rs.getString("address"));
                employee.setEmp_status(StatusEmp.valueOf(rs.getString("emp_status")));
                employee.setDepartment(rs.getString("dep_id"));
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình lấy dữ liệu: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employee;
    }

    @Override
    public List<Employee> searchEmployeeByName(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_employee_by_name(?)}");
            callSt.setString(1, employee.getEmp_name());

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmp_id(rs.getString("emp_id"));
                emp.setEmp_name(rs.getString("emp_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhoneNumber(rs.getString("phoneNumber"));
                emp.setGender(Gender.valueOf(rs.getString("gender")));
                emp.setLevelSalary(rs.getInt("level_salary"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDateOfBirth(LocalDate.parse(rs.getString("dateOfBirth")));
                emp.setAddress(rs.getString("address"));
                emp.setEmp_status(StatusEmp.valueOf(rs.getString("emp_status")));
                emp.setDepartment(rs.getString("dep_id"));
                list.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Có lỗi khi thực hiện rollback: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Employee> searchEmployeeByAgeRange(int min_age, int max_age) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> listEmployee = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_employee_by_age_range(?,?)}");
            callSt.setInt(1, min_age);
            callSt.setInt(2, max_age);

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmp_id(rs.getString("emp_id"));
                emp.setEmp_name(rs.getString("emp_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhoneNumber(rs.getString("phoneNumber"));
                emp.setGender(Gender.valueOf(rs.getString("gender")));
                emp.setLevelSalary(rs.getInt("level_salary"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDateOfBirth(LocalDate.parse(rs.getString("dateOfBirth")));
                emp.setAddress(rs.getString("address"));
                emp.setEmp_status(StatusEmp.valueOf(rs.getString("emp_status")));
                emp.setDepartment(rs.getString("dep_id"));
                listEmployee.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Có lỗi khi thực hiện rollback: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listEmployee;
    }

    @Override
    public List<Employee> sortEmployeeBySalary(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> listEmployee = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_employee_by_salary_desc()}");

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmp_id(rs.getString("emp_id"));
                emp.setEmp_name(rs.getString("emp_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhoneNumber(rs.getString("phoneNumber"));
                emp.setGender(Gender.valueOf(rs.getString("gender")));
                emp.setLevelSalary(rs.getInt("level_salary"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDateOfBirth(LocalDate.parse(rs.getString("dateOfBirth")));
                emp.setAddress(rs.getString("address"));
                emp.setEmp_status(StatusEmp.valueOf(rs.getString("emp_status")));
                emp.getDepartment(rs.getString("dep_id"));
                listEmployee.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listEmployee;
    }

    @Override
    public List<Employee> sortEmployeeByName(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> listEmployee = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_employee_by_name_asc()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmp_id(rs.getString("emp_id"));
                emp.setEmp_name(rs.getString("emp_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhoneNumber(rs.getString("phoneNumber"));
                emp.setGender(Gender.valueOf(rs.getString("gender")));
                emp.setLevelSalary(rs.getInt("level_salary"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDateOfBirth(LocalDate.parse(rs.getString("dateOfBirth")));
                emp.setAddress(rs.getString("address"));
                emp.setEmp_status(StatusEmp.valueOf(rs.getString("emp_status")));
                emp.getDepartment(rs.getString("dep_id"));
                listEmployee.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listEmployee;
    }

    @Override
    public List<Employee> getEmployeeByPage(int page, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> listEmployee = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_employee_by_page(?,?)}");
            callSt.setInt(1, page);
            callSt.setInt(2, pageSize);

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmp_id(rs.getString("emp_id"));
                emp.setEmp_name(rs.getString("emp_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhoneNumber(rs.getString("phoneNumber"));
                emp.setGender(Gender.valueOf(rs.getString("gender")));
                emp.setLevelSalary(rs.getInt("level_salary"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDateOfBirth(LocalDate.parse(rs.getString("dateOfBirth")));
                emp.setAddress(rs.getString("address"));
                emp.setEmp_status(StatusEmp.valueOf(rs.getString("emp_status")));
                emp.getDepartment(rs.getString("dep_id"));
                listEmployee.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình phân trang: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listEmployee;
    }
}
