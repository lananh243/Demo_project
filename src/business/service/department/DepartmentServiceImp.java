package business.service.department;

import business.dao.department.DepartmentDao;
import business.dao.department.DepartmentDaoImp;
import business.model.Department;

import java.util.List;

public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentDao departmentDao;

    public DepartmentServiceImp() {
        departmentDao = new DepartmentDaoImp();
    }

    @Override
    public boolean addDepartment(Department department) {
        return departmentDao.addDepartment(department);
    }

    @Override
    public boolean updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(Department department) {
        return departmentDao.deleteDepartment(department);
    }

    @Override
    public List<Department> searchDepartment(Department department) {
        return departmentDao.searchDepartment(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentDao.getAllDepartment();
    }

    @Override
    public Department findDepartmentById(int id) {
        return departmentDao.findDepartmentById(id);
    }

    @Override
    public List<Department> getDepartmentsByPage(int page, int pageSize) {
        return departmentDao.getDepartmentsByPage(page, pageSize);
    }
}
