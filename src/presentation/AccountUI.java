package presentation;

import business.model.Account;
import business.service.account.AccountService;
import business.service.account.AccountServiceImp;
import validate.Validator;

import java.util.Scanner;

public class AccountUI {
    public static void displayAccountMenu() {
        AccountService accountService = new AccountServiceImp();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**********************ACCOUNT MENU**************************");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Thoát");
            int choice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn là: ");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    boolean loginSuccess = logIn(scanner, accountService);
                    if (loginSuccess) {
                        displayLoginMenu(scanner);
                    }
                    break;
                case 2:
                    return;
            }
        }while (true);
    }

    public static boolean logIn(Scanner scanner, AccountService accountService) {
        Account account = new Account();
        account.inputData(scanner);
        return accountService.logIn(account.getUsername(),  account.getPassword());
    }

    public static void displayLoginMenu(Scanner scanner) {
        do {
            System.out.println("*********************MENU SAU ĐĂNG NHẬP***********************");
            System.out.println("1. Đăng xuất");
            System.out.println("2. Trở về menu chính");
            int choice = Validator.validateInputInterger(scanner, "Lựa chọn của bạn: ");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Bạn có chắc chắn muốn đăng xuất không ? (Y/N): ");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("Đăng xuất thành công!");
                        return;
                    } else {
                        System.out.println("Huỷ bỏ đăng xuất");
                    }
                    break;
                case 2:
                    return;
                default:
                    System.err.println("Vui lòng chọn 1 hoặc 2");
            }
        } while (true);
    }
}
