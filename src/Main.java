import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static Connection conn;
    public static void main(String[] args) {

        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/taskapp",
                "root",
                "root1234root"
            );

            Scanner scanner = new Scanner(System.in);

            while(true) {
                System.out.println("\n1: タスク一覧");
                System.out.println("2: タスク追加");
                System.out.println("3: タスク完了");
                System.out.println("4: タスク削除");
                System.out.println("0: 終了");

                int choice = scanner.nextInt();

                if(choice == 1) {
                    showTask();
                } else if(choice == 2) {
                    addTask(scanner);
                } else if(choice == 3) {
                    completedTask(scanner);
                } else if(choice == 4) {
                    deleteTask(scanner);
                } else if(choice == 0) {
                    scanner.close();
                    break;
                }
            }
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }   
    static void showTask() {
        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");

            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                Boolean completed = rs.getBoolean("completed");

                System.out.println(id + " " + title + " " + completed);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void addTask(Scanner scanner) {
        try {
            System.out.print("タスク名: ");

            scanner.nextLine();
            String title = scanner.nextLine();

            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO tasks (title, completed) VALUES ('" + title + "', false)";

            stmt.executeUpdate(sql);

            System.out.println("追加しました");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void completedTask(Scanner scanner) {
        try {
            System.out.print("完了するID: ");
            int id = scanner.nextInt();

            Statement stmt = conn.createStatement();

            String sql = "UPDATE tasks SET completed = true WHERE id = " + id;

            stmt.executeUpdate(sql);

            System.out.println("完了しました");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteTask(Scanner scanner) {
        try {
            System.out.print("削除するID ");
            int id = scanner.nextInt();

            Statement stmt = conn.createStatement();

            String sql = "DELETE FROM tasks WHERE id = " + id;

            stmt.executeUpdate(sql);

            System.out.println("削除しました");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}