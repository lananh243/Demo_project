package business.dao.statistical;

import business.config.ConnectionDB;
import business.model.DepartmentStatistic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticalDaoImp implements StatisticalDao{

    @Override
    public List<DepartmentStatistic> countEmployeeByDepartment() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<DepartmentStatistic> resultList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call count_employee_by_department()}");
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                DepartmentStatistic stat = new DepartmentStatistic();
                stat.setDepId(rs.getString("dep_id"));
                stat.setTotal(rs.getInt("total"));
                resultList.add(stat);
            }

        } catch (SQLException e) {
            System.err.println("Có lỗi xảy ra trong quá trình thống kê: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi làm việc với DB: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return resultList;
    }

    @Override
    public List<DepartmentStatistic> totalAllEmployee() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<DepartmentStatistic> resultList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call total_all_employee()}");
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                DepartmentStatistic stat = new DepartmentStatistic();
                stat.setTotal(rs.getInt("total"));
                resultList.add(stat);
            }

        } catch (SQLException e) {
            System.err.println("Có lỗi xảy ra trong quá trình thống kê: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi làm việc với DB: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return resultList;
    }

    @Override
    public List<DepartmentStatistic> theMostEmployeesInDepartment() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<DepartmentStatistic> resultList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call the_most_employees_in_department()}");
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                DepartmentStatistic stat = new DepartmentStatistic();
                stat.setDepId(rs.getString("dep_id"));
                stat.setTotal(rs.getInt("total"));
                resultList.add(stat);
            }

        } catch (SQLException e) {
            System.err.println("Có lỗi xảy ra trong quá trình thống kê: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi làm việc với DB: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return resultList;
    }

    @Override
    public List<DepartmentStatistic> theMostSalaryInDepartment() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<DepartmentStatistic> resultList = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call the_most_salary_in_department()}");
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                DepartmentStatistic stat = new DepartmentStatistic();
                stat.setDepId(rs.getString("dep_id"));
                stat.setTotal(rs.getInt("total"));
                resultList.add(stat);
            }

        } catch (SQLException e) {
            System.err.println("Có lỗi xảy ra trong quá trình thống kê: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi làm việc với DB: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return resultList;
    }
}
