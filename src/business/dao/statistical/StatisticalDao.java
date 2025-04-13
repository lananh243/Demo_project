package business.dao.statistical;

import business.model.DepartmentStatistic;

import java.util.List;

public interface StatisticalDao {
    List<DepartmentStatistic> countEmployeeByDepartment();

    List<DepartmentStatistic> totalAllEmployee();

    List<DepartmentStatistic> theMostEmployeesInDepartment();

    List<DepartmentStatistic> theMostSalaryInDepartment();
}
