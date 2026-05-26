import java.sql.*;
import java.util.Scanner;

public class StudentApp {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "shaima";
    private static final String PASSWORD = "";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("[A]dd");
            System.out.println("[V]iew");
            System.out.println("[U]pdate Password");
            System.out.println("[D]elete");
            System.out.println("[Q]uit");
            System.out.print("\nEnter choice: ");

            choice = scanner.nextLine().toUpperCase().charAt(0);

            switch (choice) {
                case 'A':
                    
                    addStudent(scanner);
                    break;
                case 'V':
                    viewStudents();
                    break;
                case 'U':
                    updatePassword(scanner);
                    break;
                case 'D':
                    deleteStudent(scanner);
                    break;
                case 'Q':
                    System.out.println("Program ended.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 'Q');

        scanner.close();
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void addStudent(Scanner scanner) {
        String sql = "INSERT INTO student " +
                "(email, password, firstname, lastname) " +
                "VALUES (?, ?, ?, ?)";

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastname = scanner.nextLine();

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, firstname);
            ps.setString(4, lastname);

            ps.executeUpdate();
            System.out.println("Student added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private static void viewStudents() {
        String sql = "SELECT * FROM student ORDER BY studentid";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== STUDENTS ===");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("studentid") + " | " +
                        rs.getString("email") + " | " +
                        rs.getString("firstname") + " " +
                        rs.getString("lastname") + " | " +
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
    }

    private static void updatePassword(Scanner scanner) {
        String sql = "UPDATE student " +
                "SET password = ?, dateupdated = CURRENT_TIMESTAMP " +
                "WHERE email = ?";

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }

    private static void deleteStudent(Scanner scanner) {
        String sql = "DELETE FROM student WHERE email = ?";

        System.out.print("Enter student email to delete: ");
        String email = scanner.nextLine();

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}