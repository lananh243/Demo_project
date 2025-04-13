package presentation;

import business.model.Department;
import business.service.department.DepartmentService;
import business.service.department.DepartmentServiceImp;
import validate.StringRule;
import validate.Validator;
import validate.ValidatorDepartment;

import java.util.List;
import java.util.Scanner;

public class DepartmentUI {
    private final DepartmentService departmentService;

    public DepartmentUI() {
        departmentService = new DepartmentServiceImp();
    }

    public static void displayDepartmentMenu() {
        DepartmentUI departmentUI = new DepartmentUI();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*************************MENU QUẢN LÝ PHÒNG BAN**************************");
            System.out.println("1. Danh sách phòng ban có phân trang");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Cập nhật phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Tìm kiếm phòng ban theo tên");
            System.out.println("6. Thoát");
            int choice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn: ");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    departmentUI.getDepartmentsByPage(scanner);
                    break;
                case 2:
                    departmentUI.addNewDepartment(scanner);
                    break;
                case 3:
                    departmentUI.updateDepartment(scanner);
                    break;
                case 4:
                    departmentUI.deleteDepartment(scanner);
                    break;
                case 5:
                    departmentUI.searchDepartment(scanner);
                    break;
                case 6:
                    return;
            }
        } while (true);
    }

    public void getDepartmentsByPage(Scanner scanner) {
        int page = Validator.validateInputInterger(scanner, "Nhập vào số trang cần xem: ");
        scanner.nextLine();
        int size = Validator.validateInputInterger(scanner, "Nhập vào số lượng phòng ban mỗi trang: ");
        scanner.nextLine();

        List<Department> departments = departmentService.getDepartmentsByPage(page, size);

        if (departments.isEmpty()) {
            System.out.println("Không có phòng ban nào ở trang này.");
        } else {
            System.out.println("Danh sách phòng ban - Trang " + page);
            System.out.println("------------------------------------------");

           for (int i = 0; i < departments.size(); i++) {
               Department department = departments.get(i);
               System.out.println((i + 1)+". "+department.toString());
           }
        }


    }


    public void addNewDepartment(Scanner scanner) {
        int size = Validator.validateInputInterger(scanner, "Nhập vào số lượng phòng ban cần thêm mới: ");
        scanner.nextLine();
        for (int i = 0; i < size; i++) {
            System.out.println("=== Nhập thông tin phòng ban thứ " + (i + 1) + " ===");

            Department department = new Department();
            department.inputData(scanner);
            departmentService.addDepartment(department);
        }

        boolean allAdded = true;

        for (Department dep : departmentService.getAllDepartment()) {
            if (!departmentService.addDepartment(dep)) {
                allAdded = false;
            }
        }

        if (allAdded) {
            System.out.println("Thêm mới phòng ban thành công!");
        } else {
            System.err.println("Có lỗi xảy ra khi thêm phòng ban.");
        }
    }


    public void updateDepartment(Scanner scanner) {
        int dep_id = Validator.validateInputInterger(scanner, "Nhập vào mã phòng ban cần cập nhập: ");
        scanner.nextLine();
        if (departmentService.findDepartmentById(dep_id) != null) {
            Department department = departmentService.findDepartmentById(dep_id);
            do {
                System.out.println("Chọn thông tin bạn muốn cập nhật:");
                System.out.println("1. Cập nhật tên phòng ban");
                System.out.println("2. Cập nhật mô tả phòng ban");
                System.out.println("3. Cập nhật trạng thái phòng ban");
                System.out.println("4. Thoát");
                int updateChoice = Validator.validateInputInterger(scanner, "Nhập lựa chọn: ");
                scanner.nextLine();
                switch (updateChoice) {
                    case 1:
                        String newDepName = ValidatorDepartment.validateDepartmentName(scanner, "Nhập tên phòng ban mới: ", new StringRule(10, 100));
                        department.setDep_name(newDepName);
                        break;
                    case 2:
                        String newDescription = ValidatorDepartment.validateDescription(scanner, "Nhập mô tả phòng ban mới: ");
                        department.setDescription(newDescription);
                        break;
                    case 3:
                        boolean newStatus = Validator.validateInputBoolean(scanner, "Nhập trạng thái phòng ban mới: ");
                        department.setStatus(newStatus);
                        break;
                    case 4:
                        return;
                    default:
                        System.err.println("Lựa chọn không hợp lệ.");
                }

                boolean result = departmentService.updateDepartment(department);

                if (result) {
                    System.out.println("Cập nhật phòng ban thành công.");
                } else {
                    System.err.println("Có lỗi trong quá trình cập nhật phòng ban.");
                }
            } while (true);


        } else {
            System.err.println("Không tồn tại mã phòng ban" +dep_id);
        }
    }

    public void deleteDepartment(Scanner scanner) {
        int dep_id = Validator.validateInputInterger(scanner, "Nhập vào mã phòng ban cần xóa: ");
        scanner.nextLine();
        if (departmentService.findDepartmentById(dep_id) != null) {
            Department department = new Department();
            department.setDep_id(dep_id);
            boolean result = departmentService.deleteDepartment(department);

            if (result) {
                System.out.println("Xóa phòng ban thành công");
            } else {
                System.err.println("Có lỗi trong quá trình xóa");
            }
        } else {
            System.err.println("Không tồn tại mã phòng ban");
        }
    }

    public void searchDepartment(Scanner scanner) {
        String dep_name = Validator.validateInputString(scanner, "Nhập vào tên phòng ban cần tìm kiếm: ");
        Department department = new Department();
        department.setDep_name(dep_name);
        List<Department> result = departmentService.searchDepartment(department);

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy phòng ban nào với tên chứa: " + dep_name);
        } else {
            System.out.println("Kết quả tìm kiếm: ");
            result.forEach(System.out::println);
        }
    }
}
