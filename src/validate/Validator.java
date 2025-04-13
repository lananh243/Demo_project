package validate;

import java.util.Scanner;

public class Validator {
    public static String validateInputString(Scanner scanner, String message) {
        System.out.println(message);
        do {
            try {
                return scanner.nextLine();
            } catch (NumberFormatException ex) {
                System.err.println("Dữ liệu nhập vào không đúng định dạng, vui lòng nhập lại");
            }
        } while (true);
    }

    public static double validateInputDouble(Scanner scanner, String message) {
        System.out.println(message);
        do {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.err.println("Dữ liệu nhập vào không đúng định dạng, vui lòng nhập lại");
            }
        } while (true);
    }

    public static int validateInputInterger(Scanner scanner, String message) {
        System.out.println(message);
        do {
            try {
                return scanner.nextInt();
            } catch (NumberFormatException ex) {
                System.err.println("Dữ liệu nhập vào không phải kiểu số nguyên, vui lòng nhập lại");
            }
        } while (true);
    }

    public static boolean validateInputBoolean(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String inputString = scanner.nextLine();
                if (inputString.equalsIgnoreCase("true") || inputString.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(inputString);
                }

                throw new IllegalArgumentException("Dữ liệu không hợp lệ, vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static <T extends Enum<T>> T validateEnumInput(Scanner scanner, String message, Class<T> enumClass) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            for (T value : enumClass.getEnumConstants()) {
                if (value.name().equalsIgnoreCase(input)) {
                    return value;
                }
            }
            System.err.println("Giá trị không đúng, vui lòng nhập lại");
        }
    }
}
