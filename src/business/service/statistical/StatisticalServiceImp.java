package business.service.statistical;

import business.dao.statistical.StatisticalDao;
import business.dao.statistical.StatisticalDaoImp;
import business.model.DepartmentStatistic;

import java.util.List;

public class StatisticalServiceImp implements StatisticalService {
    private final StatisticalDao statisticalDao;

    public StatisticalServiceImp() {
        statisticalDao = new StatisticalDaoImp();
    }


    @Override
    public List<DepartmentStatistic> countEmployeeByDepartment() {
        return statisticalDao.countEmployeeByDepartment();
    }

    @Override
    public List<DepartmentStatistic> totalAllEmployee() {
        return statisticalDao.totalAllEmployee();
    }

    @Override
    public List<DepartmentStatistic> theMostEmployeesInDepartment() {
        return statisticalDao.theMostEmployeesInDepartment();
    }

    @Override
    public List<DepartmentStatistic> theMostSalaryInDepartment() {
        return statisticalDao.theMostSalaryInDepartment();
    }
}
