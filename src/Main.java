import presentation.AccountUI;
import presentation.DepartmentUI;
import presentation.EmployeeUI;
import presentation.StatisticalUI;
import validate.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("************************MENU MAIN************************");
            System.out.println("1. Tài khoản");
            System.out.println("2. Quản lý phòng ban");
            System.out.println("3. Quản lý nhân viên");
            System.out.println("4. Thống kê");
            System.out.println("0. Thoát");
            int choice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn là: ");
            switch (choice) {
                case 1:
                    AccountUI.displayAccountMenu();
                    break;
                case 2:
                    DepartmentUI.displayDepartmentMenu();
                    break;
                case 3:
                    EmployeeUI.displayEmployeeMenu();
                    break;
                case 4:
                    StatisticalUI.displayStatisticMenu();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn 0 - 4");
            }
        }while (true);
    }
}
