import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;

public class JDBCJavaFXApp extends Application {

    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASS = "password";

    TextArea output = new TextArea();

    Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    void insertRestaurant() {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Restaurant VALUES (?, ?, ?)"
            );
            ps.setInt(1, 11);
            ps.setString(2, "Cafe FX");
            ps.setString(3, "Mumbai");
            ps.executeUpdate();
            output.setText("Restaurant Inserted");
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }

    void insertMenu() {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO MenuItem VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, 21);
            ps.setString(2, "Sandwich");
            ps.setInt(3, 80);
            ps.setInt(4, 11);
            ps.executeUpdate();
            output.setText("MenuItem Inserted");
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }

    void selectMenu() {
        try (Connection con = getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM MenuItem");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getInt(1)).append("\t")
                  .append(rs.getString(2)).append("\t")
                  .append(rs.getInt(3)).append("\t")
                  .append(rs.getInt(4)).append("\n");
            }
            output.setText(sb.toString());
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }

    void updateMenu() {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE MenuItem SET Price=200 WHERE Price<=100"
            );
            int rows = ps.executeUpdate();
            output.setText("Updated Rows: " + rows);
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }

    void deleteMenu() {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM MenuItem WHERE Name LIKE 'P%'"
            );
            int rows = ps.executeUpdate();
            output.setText("Deleted Rows: " + rows);
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {

        Button insertR = new Button("Insert Restaurant");
        Button insertM = new Button("Insert MenuItem");
        Button select = new Button("Select Menu");
        Button update = new Button("Update Menu");
        Button delete = new Button("Delete Menu");

        insertR.setOnAction(e -> insertRestaurant());
        insertM.setOnAction(e -> insertMenu());
        select.setOnAction(e -> selectMenu());
        update.setOnAction(e -> updateMenu());
        delete.setOnAction(e -> deleteMenu());

        VBox root = new VBox(10, insertR, insertM, select, update, delete, output);
        root.setPadding(new javafx.geometry.Insets(15));

        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle("JDBC JavaFX CRUD");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
