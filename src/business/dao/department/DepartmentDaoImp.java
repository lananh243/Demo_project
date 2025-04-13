package business.dao.department;

import business.config.ConnectionDB;
import business.model.Department;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImp implements DepartmentDao {

    @Override
    public boolean addDepartment(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call add_new_department(?,?,?)}");

            callSt.setString(1, department.getDep_name());
            callSt.setString(2, department.getDescription());
            callSt.setBoolean(3, department.isStatus());
            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thêm phòng ban, dữ liệu đã được rollback");
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
    public boolean updateDepartment(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_department(?,?,?,?)}");
            callSt.setInt(1, department.getDep_id());
            callSt.setString(2, department.getDep_name());
            callSt.setString(3, department.getDescription());
            callSt.setBoolean(4, department.isStatus());
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
    public boolean deleteDepartment(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call delete_department(?)}");
            callSt.setInt(1, department.getDep_id());
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
    public List<Department> searchDepartment(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_department_by_name(?)}");
            callSt.setString(1, department.getDep_name());

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Department dep = new Department();
                dep.setDep_id(rs.getInt("dep_id"));
                dep.setDep_name(rs.getString("dep_name"));
                dep.setDescription(rs.getString("description"));
                dep.setStatus(rs.getBoolean("dep_status"));
                list.add(dep);
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
    public List<Department> getAllDepartment() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> listDepartment = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_department()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDep_id(rs.getInt("dep_id"));
                department.setDep_name(rs.getString("dep_name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(rs.getBoolean("dep_status"));
                listDepartment.add(department);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình lấy dữ liệu, dữ liệu đã được rollback");
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listDepartment;
    }

    @Override
    public Department findDepartmentById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Department department = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_department_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                department = new Department();
                department.setDep_id(rs.getInt("dep_id"));
                department.setDep_name(rs.getString("dep_name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(rs.getBoolean("dep_status"));
            }
        }catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình lấy dữ liệu, dữ liệu đã được rollback");
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: \" + e.getMessage()");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return department;
    }

    @Override
    public List<Department> getDepartmentsByPage(int page, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> listDepartment = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_department_by_page(?,?)}");
            callSt.setInt(1, page);
            callSt.setInt(2, pageSize);
            ResultSet rs = callSt.executeQuery();
            while(rs.next()){
                Department department = new Department();
                department.setDep_id(rs.getInt("dep_id"));
                department.setDep_name(rs.getString("dep_name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(rs.getBoolean("dep_status"));
                listDepartment.add(department);
            }
        }   catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình phân trang" +e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listDepartment;
    }
}
