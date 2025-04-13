package validate;

import business.dao.department.DepartmentDao;
import business.dao.department.DepartmentDaoImp;
import business.model.Department;

import java.util.List;
import java.util.Scanner;

public class ValidatorDepartment {
    public static final DepartmentDao departmentDao = new DepartmentDaoImp();
    public static final List<Department> departments = departmentDao.getAllDepartment();
    public static String validateDepartmentName(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        do {
            String inputString = scanner.nextLine().trim();
            if (inputString.isEmpty()) {
                System.err.println("Tên phòng ban không được để trống.");
                continue;
            }

            if (!stringRule.isValidString(inputString)) {
                System.err.println("Dữ liệu không hợp lệ, vui lòng nhập lại");
                continue;
            }

            boolean isDupName = departments.stream().anyMatch(dep -> dep.getDep_name().equalsIgnoreCase(inputString));
            if (isDupName) {
                System.err.println("Tên phòng ban đã tồn tại, vui lòng nhập tên khác.");
                continue;
            }
            return inputString;
        }while (true);
    }

    public static String validateDescription(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String inputDescription = scanner.nextLine().trim();
                if (inputDescription.isEmpty()) {
                    System.err.println("Dữ liệu không được để trống");
                    continue;
                }

                if (inputDescription.length() > 255) {
                    System.err.println("Dữ liệu không đúng định dạng, vui lòng nhập lại");
                    continue;
                }
                return inputDescription;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
    }
}
