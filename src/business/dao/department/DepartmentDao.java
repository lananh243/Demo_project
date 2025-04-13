package business.dao.department;

import business.dao.AppDao;
import business.model.Department;

import java.util.List;

public interface DepartmentDao extends AppDao<Department> {
    boolean addDepartment(Department department);

    boolean updateDepartment(Department department);

    boolean deleteDepartment(Department department);

    List<Department> searchDepartment(Department department);

    List<Department> getAllDepartment();

    Department findDepartmentById(int id);

    List<Department> getDepartmentsByPage(int page, int pageSize);
}
