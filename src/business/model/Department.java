package business.model;

import business.dao.department.DepartmentDao;
import business.dao.department.DepartmentDaoImp;
import validate.StringRule;
import validate.Validator;
import validate.ValidatorDepartment;

import java.util.List;
import java.util.Scanner;

public class Department {
    private static int idAutoincreasement = 0;
    private int dep_id;
    private String dep_name;
    private String description;
    private boolean status;

    public Department() {
        this.dep_id = ++idAutoincreasement;
    }

    public Department(int dep_id, String dep_name, String description, boolean status) {
        this.dep_id = ++idAutoincreasement;
        this.dep_name = dep_name;
        this.description = description;
        this.status = status;
    }

    public int  getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        this.dep_name = ValidatorDepartment.validateDepartmentName(scanner, "Nhập vào tên phòng ban: ", new StringRule(10, 100));
        this.description = ValidatorDepartment.validateDescription(scanner, "Nhập vào mô tả phòng ban: ");
        this.status = Validator.validateInputBoolean(scanner, "Nhập vào trạng thái phòng ban: ");
    }
    @Override
    public String toString() {
        return "Mã phòng ban: " + dep_id +
                " - Tên phòng ban: " + dep_name +
                " - Mô tả phòng ban: " + description +
                " - Trạng thái phòng ban: " + (this.status ? "Hoạt động" : "Không hoạt động") ;
    }
}
