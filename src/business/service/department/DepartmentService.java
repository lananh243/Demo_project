package business.service.department;

import business.model.Department;
import business.service.AppService;

import java.util.List;

public interface DepartmentService extends AppService {
    boolean addDepartment(Department department);

    boolean updateDepartment(Department department);

    boolean deleteDepartment(Department department);

    List<Department> searchDepartment(Department department);

    List<Department> getAllDepartment();

    Department findDepartmentById(int id);

    List<Department> getDepartmentsByPage(int page, int pageSize);
}
