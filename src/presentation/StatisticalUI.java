package presentation;

import business.model.DepartmentStatistic;
import business.service.statistical.StatisticalService;
import business.service.statistical.StatisticalServiceImp;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class StatisticalUI {
    private final StatisticalService statisticalService;


    public StatisticalUI() {
        statisticalService = new StatisticalServiceImp();
    }

    public static void displayStatisticMenu() {
        Scanner scanner = new Scanner(System.in);
        StatisticalUI statisticalUI = new StatisticalUI();
        do {
            System.out.println("***********************MENU THỐNG KÊ******************************");
            System.out.println("1. Số lượng nhân viên theo từng phòng ban");
            System.out.println("2. Tổng số nhân viên của toàn bộ hệ thống");
            System.out.println("3. Phòng ban có nhiều nhân viên nhất");
            System.out.println("4. Phòng ban có lương cao nhất");
            System.out.println("5. Thoát");
            int choice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn: ");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    statisticalUI.count_employee_by_department();
                    break;
                case 2:
                    statisticalUI.totalAllEmployees();
                    break;
                case 3:
                    statisticalUI.theMostEmployeesInDepartment();
                    break;
                case 4:
                    statisticalUI.theMostSalaryInDepartment();
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }

    public void count_employee_by_department() {
        List<DepartmentStatistic> result = statisticalService.countEmployeeByDepartment();
        if (result != null && !result.isEmpty()) {
            System.out.println("=== SỐ LƯỢNG NHÂN VIÊN THEO PHÒNG BAN ===");
            result.forEach(ds -> System.out.printf("Phòng ban: %s | Số nhân viên: %d\n", ds.getDepId(), ds.getTotal()));
        } else {
            System.err.println("Không có dữ liệu để thống kê");
        }
    }

    public void totalAllEmployees() {
        List<DepartmentStatistic> result = statisticalService.totalAllEmployee();
        if (result != null && !result.isEmpty()) {
            System.out.println("=== TỔNG SỐ NHÂN VIÊN CỦA TOÀN HỆ THỐNG ===");
            result.forEach(ds -> System.out.printf("Tổng số nhân viên: %d\n", ds.getTotal()));
        } else {
            System.err.println("Không có dữ liệu để thống kê");
        }
    }

    public void theMostEmployeesInDepartment() {
        List<DepartmentStatistic> result = statisticalService.theMostEmployeesInDepartment();
        if (result != null && !result.isEmpty()) {
            System.out.println("=== PHÒNG BAN CÓ NHIỀU NHÂN VIÊN NHẤT ===");
            result.forEach(ds -> System.out.printf("Phòng ban: %s | Số nhân viên: %d\n", ds.getDepId(), ds.getTotal()));
        } else {
            System.err.println("Không có dữ liệu để thống kê");
        }
    }

    public void theMostSalaryInDepartment() {
        List<DepartmentStatistic> result = statisticalService.theMostSalaryInDepartment();
        if (result != null && !result.isEmpty()) {
            System.out.println("=== PHÒNG BAN CÓ LƯƠNG CAO NHẤT ===");
            result.forEach(ds -> System.out.printf("Phòng ban: %s | Tổng lương: %d\n", ds.getDepId(), ds.getTotal()));
        } else {
            System.err.println("Không có dữ liệu để thống kê");
        }
    }
}
