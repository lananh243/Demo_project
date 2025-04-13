package validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ValidatorEmployee {
    private static final String PHONE_VIETTEL_PREFIXES = "086|096|097|098|039|038|037|036|035|034|033|032";
    private static final String PHONE_VINAPHONE_PREFIXES = "091|094|088|083|084|085|081|082";
    private static final String PHONE_MOBIFONE_PREFIXES = "070|079|077|076|078|089|090|093";

    public static String validateEmployeeId(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String id = scanner.nextLine();
                if (id.isEmpty()) {
                    System.err.println("Mã nhân viên không được để trống");
                    continue;
                }

                if (!id.matches("(E)\\d{4}")) {
                    System.err.println("Dữ liệu không đúng định dạng, vui lòng nhập lại");
                    continue;
                }
                return id;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static String validateEmployeeName(Scanner scanner, String message, StringRule stringRule) {
        while (true) {
            System.out.println(message);
            try {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.err.println("Tên nhân viên không được để trống");
                    continue;
                }

                return name;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static String validateEmail(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String email = scanner.nextLine().trim();
                if (email.isEmpty()) {
                    System.err.println("Email không được để trống");
                    continue;
                }

                if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    System.err.println("Dữ liệu nhập vào không đúng định dạng, vui lòng nhập lại");
                    continue;
                }
                return email;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static String validatePhonenumber(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String phone = scanner.nextLine().trim();

                if (phone.isEmpty()) {
                    System.err.println("Số điện thoại không được để trống");
                    continue;
                }

                if (!phone.matches("(" + PHONE_VIETTEL_PREFIXES + "|" + PHONE_VINAPHONE_PREFIXES + "|" + PHONE_MOBIFONE_PREFIXES +")\\d{7}")) {
                    System.err.println("Dữ liệu nhập vào không đúng định dạng, vui lòng nhập lại");
                    continue;
                }
                return phone;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static Integer validateSalaryLevel(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                int salary = Integer.parseInt(scanner.nextLine());
                if (salary < 0) {
                    System.err.println("Bậc lương phải có giá trị > 0");
                    continue;
                }

                return salary;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static Double validateSalary(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                double salary = Double.parseDouble(scanner.nextLine());
                if (salary < 0) {
                    System.err.println("Lương phải có giá trị > 0");
                    continue;
                }

                return salary;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static LocalDate validateDateOfBirth(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            try {
                return LocalDate.parse(scanner.nextLine(), dtf);
            } catch (DateTimeParseException e) {
                System.err.println("Không đúng định dạng ngày tháng, vui lòng nhập lại");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }


}
