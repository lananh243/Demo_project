package business.model;

import validate.StringRule;
import validate.Validator;
import validate.ValidatorEmployee;

import java.time.LocalDate;
import java.util.Scanner;

public class Employee {
    private String emp_id;
    private String emp_name;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private int levelSalary;
    private double salary;
    private LocalDate dateOfBirth;
    private String address;
    private StatusEmp emp_status;
    private Department department;


    public Employee() {
    }

    public Employee(String emp_id, String emp_name, String email, String phoneNumber, Gender gender, int levelSalary, double salary, LocalDate dateOfBirth, String address, StatusEmp emp_status, Department department) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.levelSalary = levelSalary;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.emp_status = emp_status;
        this.department = department;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLevelSalary() {
        return levelSalary;
    }

    public void setLevelSalary(int levelSalary) {
        this.levelSalary = levelSalary;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StatusEmp getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(StatusEmp emp_status) {
        this.emp_status = emp_status;
    }

    public Department getDepartment() {
        return department;
    }

    public void inputData(Scanner scanner) {
        this.emp_id = ValidatorEmployee.validateEmployeeId(scanner, "Nhập vào mã nhân viên: ");
        this.emp_name = ValidatorEmployee.validateEmployeeName(scanner, "Nhạp vào tên nhân viên: ", new StringRule(15, 150));
        this.email = ValidatorEmployee.validateEmail(scanner, "Nhập vào email: ");
        this.phoneNumber = ValidatorEmployee.validatePhonenumber(scanner, "Nhập vào số điện thoại: ");
        this.gender = Validator.validateEnumInput(scanner, "Nhập vào giới tính: ", Gender.class);
        this.levelSalary = ValidatorEmployee.validateSalaryLevel(scanner, "Nhập vào bậc lương: ");
        this.salary = ValidatorEmployee.validateSalary(scanner, "Nhập vào lương: ");
        this.dateOfBirth = ValidatorEmployee.validateDateOfBirth(scanner, "Nhập vào ngày sinh: ");
        this.address = Validator.validateInputString(scanner, "Nhập vào địa chỉ: ");
        this.emp_status = Validator.validateEnumInput(scanner, "Nhập vào trạng thái nhân viên: ", StatusEmp.class);
    }
    @Override
    public String toString() {
        return "Mã nhân viên: " + emp_id +
                " - Tên nhân viên: " + emp_name +
                " - Email: " + email +
                " - Số điện thoại: " + phoneNumber +
                " - Giới tính: " + gender +
                " - Bậc lương: " + levelSalary +
                " - Lương: " + salary +
                " - Ngày sinh: " + dateOfBirth +
                " - Địa chỉ: " + address +
                " - Trạng thái nhân viên : " + emp_status +
                " - Phòng ban nhân viên: " + department ;
    }

    public void getDepartment(String depId) {
    }
}
