import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TaskDao {
    Connection conn;

    public TaskDao(Connection conn) {
        this.conn = conn;
    }

    public void showTask() {
        String sql = "SELECT * FROM tasks";
        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setCompleted(rs.getBoolean("completed"));

                System.out.println(task.getId() + " " + task.getTitle() + " " + task.getCompleted());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showIncompleteTask() {
        String sql = "SELECT * FROM tasks WHERE completed = false";
        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setCompleted(rs.getBoolean("completed"));

                System.out.println(task.getId() + " " + task.getTitle() + " " + task.getCompleted());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTask(Scanner scanner) {
        System.out.print("追加するタスク: ");
        scanner.nextLine();
        String sql = "INSERT INTO tasks(title, completed) VALUES (?, false)";
        try ( 
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String word = scanner.nextLine();
            pstmt.setString(1, word);
            pstmt.executeUpdate(); 
            System.out.println("追加しました");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void completedTask(Scanner scanner) {
        System.out.print("完了したタスクid: ");
        String sql = "UPDATE tasks SET completed = true WHERE id = ?";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int num = scanner.nextInt();
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
            System.out.println("完了しました");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(Scanner scanner) {
        System.out.print("削除するタスクid: ");
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int num = scanner.nextInt();
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
            System.out.println("削除しました");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchTask(Scanner scanner) {
        System.out.print("検索するワード: ");
        String sql = "SELECT * FROM tasks WHERE title LIKE ?";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            scanner.nextLine();
            String word = scanner.nextLine();
            pstmt.setString(1, "%" + word + "%");

            try (
                ResultSet rs = pstmt.executeQuery()) {
                    while(rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setTitle(rs.getString("title"));
                    task.setCompleted(rs.getBoolean("completed"));

                    System.out.println(task.getId() + " " + task.getTitle() + " " + task.getCompleted());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
