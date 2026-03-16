import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/taskapp",
                "root",
                "root1234root"
            );

            TaskDao taskDao = new TaskDao(conn);

            Scanner scanner = new Scanner(System.in);

            while(true) {
                System.out.println("\n1: タスク一覧");
                System.out.println("2: 未完了タスク一覧");
                System.out.println("3: タスク追加");
                System.out.println("4: タスク完了");
                System.out.println("5: タスク削除");
                System.out.println("6: タスク検索");
                System.out.println("0: 終了");

                int choice = scanner.nextInt();

                if(choice == 1) {
                    taskDao.showTask();
                } else if(choice == 2) {
                    taskDao.showIncompleteTask();
                } else if(choice == 3) {
                    taskDao.addTask(scanner);
                } else if(choice == 4) {
                    taskDao.completedTask(scanner);
                } else if(choice == 5) {
                    taskDao.deleteTask(scanner);
                } else if(choice == 6) {
                    taskDao.searchTask(scanner);
                } else if(choice == 0) {
                    System.out.println("終了します");
                    scanner.close();
                    break;
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}