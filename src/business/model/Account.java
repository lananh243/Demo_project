package business.model;

import validate.Validator;

import java.util.Scanner;

public class Account {
    private static int idAutoincreasement = 0;
    private int acc_id;
    private String username;
    private String password;
    private boolean status;

    public Account() {
        this.acc_id = ++idAutoincreasement;
    }

    public Account(boolean status, String username, String password) {
        this.acc_id = ++idAutoincreasement;
        this.status = status;
        this.username = username;
        this.password = password;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        this.username = Validator.validateInputString(scanner, "Nhập vào tên người dùng: ");
        this.password = Validator.validateInputString(scanner, "Nhập vào mật khẩu: ");
        this.status = Validator.validateInputBoolean(scanner, "Nhập vào trạng thái: ");
    }

    @Override
    public String toString() {
        return "Tên người dùng: " + username +
                " - Mật khẩu: " + password +
                " - Trạng thái: " + status;
    }
}
