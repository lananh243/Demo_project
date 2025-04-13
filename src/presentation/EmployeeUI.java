package presentation;

import business.model.Employee;
import business.model.Gender;
import business.model.StatusEmp;
import business.service.employee.EmployeeService;
import business.service.employee.EmployeeServiceImp;
import validate.StringRule;
import validate.Validator;
import validate.ValidatorEmployee;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeUI {
    private final EmployeeService employeeService;

    public EmployeeUI() {
        employeeService = new EmployeeServiceImp();
    }

    public static void displayEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);
        EmployeeUI employeeUI = new EmployeeUI();
        do {
            System.out.println("*************************MENU QUẢN LÝ NHÂN VIÊN**************************");
            System.out.println("1. Danh sách nhân viên có phân trang");
            System.out.println("2. Thêm nhân viên");
            System.out.println("3. Cập nhật nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Tìm kiếm nhân viên");
            System.out.println("6. Sắp xếp nhân viên");
            System.out.println("7. Thoát");
            int choice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn: ");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    employeeUI.getEmployeeByPage(scanner);
                    break;
                case 2:
                    employeeUI.addEmployee(scanner);
                    break;
                case 3:
                    employeeUI.updateEmployee(scanner);
                    break;
                case 4:
                    employeeUI.deleteEmployee(scanner);
                    break;
                case 5:
                    employeeUI.displaySearchMenu(scanner);
                    break;
                case 6:
                    employeeUI.displaySortMenu(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }

    public void getEmployeeByPage(Scanner scanner) {
        int page = Validator.validateInputInterger(scanner, "Nhập vào số trang cần xem: ");
        int size = Validator.validateInputInterger(scanner, "Nhập vào số lượng nhân viên mỗi trang: ");

        List<Employee> employees = employeeService.getEmployeeByPage(page, size);

        if (employees.isEmpty()) {
            System.out.println("Không có nhân viên nào ở trang này.");
        } else {
            System.out.println("Danh sách nhân viên - Trang " + page);
            System.out.println("------------------------------------------");

            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                System.out.println((i + 1)+". "+employee.toString());
            }
        }
    }

    public void addEmployee(Scanner scanner){
        int size = Validator.validateInputInterger(scanner, "Nhập vào số lượng nhân viên cần thêm mới: ");

        boolean allAdded = true;
        for (int i = 0; i < size; i++) {
            System.out.println("=== Nhập thông tin nhân viên thứ " + (i + 1) + " ===");
            Employee employee = new Employee();
            employee.inputData(scanner);

            if (!employeeService.addEmployee(employee)) {
                allAdded = false;
            }
        }
        if (allAdded) {
            System.out.println("Thêm mới nhân viên thành công!");
        } else {
            System.err.println("Có lỗi xảy ra khi thêm nhân viên.");
        }
    }

    public void updateEmployee(Scanner scanner) {
        String emp_id = Validator.validateInputString(scanner, "Nhập vào mã nhân viên cần cập nhật: ");
        if (employeeService.getEmployeeById(emp_id) != null) {
            Employee employee = employeeService.getEmployeeById(emp_id);
            do {
                System.out.println("Chọn thông tin bạn muốn cập nhật: ");
                System.out.println("1. Cập nhật tên nhân viên");
                System.out.println("2. Cập nhật email");
                System.out.println("3. Cập nhật số điện thoại");
                System.out.println("4. Cập nhật giới tính");
                System.out.println("5. Cập nhật bậc lương");
                System.out.println("6. Cập nhật ngày sinh");
                System.out.println("7. Cập nhật địa chỉ");
                System.out.println("8. Cập nhật trạng thái nhân viên");
                System.out.println("9. Cập nhật phòng ban cho nhân viên");
                System.out.println("10. Thoát");
                int updateChoice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn là: ");
                scanner.nextLine();
                switch (updateChoice) {
                    case 1:
                        String newEmpName = ValidatorEmployee.validateEmployeeName(scanner, "Nhập tên nhân viên mới: ", new StringRule(15, 150));
                        employee.setEmp_name(newEmpName);
                        break;
                    case 2:
                        String newEmail = ValidatorEmployee.validateEmail(scanner, "Nhập vào email mới: ");
                        employee.setEmail(newEmail);
                        break;
                    case 3:
                        String newPhoneNumber = ValidatorEmployee.validatePhonenumber(scanner, "Nhập vào số điện thoại mới: ");
                        employee.setPhoneNumber(newPhoneNumber);
                        break;
                    case 4:
                        Gender newGender = Validator.validateEnumInput(scanner, "Nhập vào giới tính mới: ", Gender.class);
                        employee.setGender(newGender);
                        break;
                    case 5:
                         int newLevelSalary = ValidatorEmployee.validateSalaryLevel(scanner, "Nhập vào bậc lương mới: ");
                         employee.setSalary(newLevelSalary);
                         break;
                     case 6:
                         double newSalary = ValidatorEmployee.validateSalary(scanner, "Nhập vào lương mới: ");
                         employee.setSalary(newSalary);
                         break;
                     case 7:
                         LocalDate newDateOfBirth = ValidatorEmployee.validateDateOfBirth(scanner, "Nhập vào ngày sinh mới: ");
                         employee.setDateOfBirth(newDateOfBirth);
                         break;
                     case 8:
                         String newAddress = Validator.validateInputString(scanner, "Nhập vào địa chỉ mới: ");
                         employee.setAddress(newAddress);
                         break;
                     case 9:
                         StatusEmp newStatus = Validator.validateEnumInput(scanner, "Nhập vào trạng thái mới: ", StatusEmp.class);
                         employee.setEmp_status(newStatus);
                         break;
                     case 10:
                         return;
                    default:
                        System.err.println("Lựa chọn không hợp lệ, vui lòng chọn lại");
                }
                boolean result = employeeService.updateEmployee(employee);

                if (result) {
                    System.out.println("Cập nhật nhân viên thành công.");
                } else {
                    System.err.println("Có lỗi trong quá trình cập nhật nhân viên.");
                }
            } while (true);
        } else {
            System.err.println("Không tồn tại mã nhân viên" +emp_id);
        }
    }

    public void deleteEmployee(Scanner scanner) {
        String emp_id = ValidatorEmployee.validateEmployeeId(scanner, "Nhập vào mã nhân viên cần xóa: ");
        if (employeeService.getEmployeeById(emp_id) != null) {
            Employee employee = new Employee();
            employee.setEmp_id(emp_id);
            boolean result = employeeService.deleteEmployee(employee);
            if (result) {
                System.out.println("Xóa nhân viên thành công");
            } else {
                System.err.println("Không thể xóa phòng ban vì vẫn còn nhân viên hoặc có lỗi xảy ra.");
            }
        } else {
            System.err.println("Không tồn tại mã nhân viên"+emp_id);
        }
    }

    public void displaySearchMenu(Scanner scanner) {
        do {
            System.out.println("********************Tìm kiếm********************");
            System.out.println("1. Tìm kiếm nhân viên theo tên");
            System.out.println("2. Tìm kiếm nhân viên theo khoảng tuổi");
            System.out.println("3. Thoát");
            int choice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn: ");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    searchEmployeeByName(scanner);
                    break;
                case 2:
                    searchEmployeeByAgeRange(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Lựa chọn ko hợp lệ, vui lòng chọn lại");
            }
        }while (true);
    }

    public void searchEmployeeByName(Scanner scanner) {
        String emp_name = ValidatorEmployee.validateEmployeeName(scanner, "Nhập vào tên nhân viên cần tìm: ", new StringRule(15, 150));
        Employee employee = new Employee();
        employee.setEmp_name(emp_name);
        List<Employee> result = employeeService.searchEmployeeByName(employee);

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy nhân viên nào có tên: "+emp_name);
        } else {
            System.out.println("Kết quả tìm kiếm: ");
            result.forEach(System.out::println);
        }
    }

    public void searchEmployeeByAgeRange(Scanner scanner) {
        int min_age = Validator.validateInputInterger(scanner, "Nhập vào tuổi bắt đầu: ");
        int max_age = Validator.validateInputInterger(scanner, "Nhập vào tuổi kết thúc: ");

        List<Employee> result = employeeService.searchEmployeeByAgeRange(min_age, max_age);

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy nhân viên nào nằm trong đô tuổi từ" +min_age+ " -  "+ max_age);
        } else {
            System.out.println("Kết quả tìm kiếm: ");
            result.forEach(System.out::println);
        }
    }

    public void displaySortMenu(Scanner scanner) {
        do {
            System.out.println("************************Sắp xếp**********************");
            System.out.println("1. Lương giảm dần");
            System.out.println("2. Tên nhân viên tăng dần");
            System.out.println("3. Thoát");
            int choice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn: ");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    sortEmployeeBySalary();
                    break;
                case 2:
                    sortEmployeeByName();
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Lựa chọn ko hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }

    public void sortEmployeeBySalary(){
        List<Employee> result = employeeService.sortEmployeeBySalary(null);

        if (result.isEmpty()) {
            System.out.println("Không có nhân viên nào để hiển thị.");
        } else {
            System.out.println("Danh sách nhân viên theo lương giảm dần: ");
            result.forEach(System.out::println);
        }
    }

    public void sortEmployeeByName() {
        List<Employee> result = employeeService.sortEmployeeByName(null);
        if (result.isEmpty()) {
            System.out.println("Không có nhân viên nào để hiển thị.");
        } else {
            System.out.println("Danh sách nhân viên theo tên tăng dần: ");
            result.forEach(System.out::println);
        }
    }
}
