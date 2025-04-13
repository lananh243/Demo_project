package business.service.statistical;

import business.model.DepartmentStatistic;

import java.util.List;

public interface StatisticalService {
    List<DepartmentStatistic> countEmployeeByDepartment();

    List<DepartmentStatistic> totalAllEmployee();

    List<DepartmentStatistic> theMostEmployeesInDepartment();

    List<DepartmentStatistic> theMostSalaryInDepartment();
}
