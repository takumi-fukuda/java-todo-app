import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class TaskDao {
    Connection conn;

    public TaskDao(Connection conn) {
        this.conn = conn;
    }

    public void showTask() {
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM tasks";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Task task = new Task();
                task.id = rs.getInt("id");
                task.title = rs.getString("title");
                task.completed = rs.getBoolean("completed");
                
                System.out.println(task.id + " " + task.title + " " + task.completed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showIncompleteTask() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM tasks WHERE completed = false";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Task task = new Task();
                task.id = rs.getInt("id");
                task.title = rs.getString("title");
                task.completed = rs.getBoolean("completed");

                System.out.println(task.id + " " + task.title + " " + task.completed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTask(Scanner scanner) {
        try {
            System.out.print("追加するタスク: ");
            Statement stmt = conn.createStatement();
            scanner.nextLine();
            String word = scanner.nextLine();
            String sql = "INSERT INTO tasks(title, completed) VALUES ('" + word + "', false)";
            stmt.executeUpdate(sql);
            System.out.println("追加しました");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void completedTask(Scanner scanner) {
        try {
            System.out.print("完了したタスクid: ");
            Statement stmt = conn.createStatement();
            int num = scanner.nextInt();
            String sql = "UPDATE tasks SET completed = true WHERE id = " + num;
            stmt.executeUpdate(sql);
            System.out.println("完了しました");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(Scanner scanner) {
        try {
            System.out.print("削除するタスクid: ");
            Statement stmt = conn.createStatement();
            int num = scanner.nextInt();
            String sql = "DELETE FROM tasks WHERE id = " + num;
            stmt.executeUpdate(sql);
            System.out.println("削除しました");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchTask(Scanner scanner) {
        try {
            System.out.print("検索するワード: ");
            Statement stmt = conn.createStatement();
            scanner.nextLine();
            String word = scanner.nextLine();
            String sql = "SELECT * FROM tasks WHERE title LIKE '%" + word + "%'";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Task task = new Task();
                task.id = rs.getInt("id");
                task.title = rs.getString("title");
                task.completed = rs.getBoolean("completed");

                System.out.println(task.id + " " + task.title + " " + task.completed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
