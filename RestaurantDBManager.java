import java.sql.*;

public class RestaurantDBManager {

    // Database credentials - Update these to match your MySQL setup
    static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to database successfully...");

            Statement stmt = conn.createStatement();

            // 1. Create Tables
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Restaurant (" +
                    "Id INT PRIMARY KEY, Name VARCHAR(100), Address VARCHAR(255))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MenuItem (" +
                    "Id INT PRIMARY KEY, Name VARCHAR(100), Price DOUBLE, ResId INT, " +
                    "FOREIGN KEY (ResId) REFERENCES Restaurant(Id))");

            // 2. Insert 10 records into Restaurant
            String resInsert = "INSERT IGNORE INTO Restaurant VALUES (?, ?, ?)";
            PreparedStatement psRes = conn.prepareStatement(resInsert);
            for (int i = 1; i <= 10; i++) {
                psRes.setInt(1, i);
                psRes.setString(2, i == 1 ? "Cafe Java" : "Restaurant " + i);
                psRes.setString(3, "Street " + i);
                psRes.executeUpdate();
            }

            // 3. Insert 10 records into MenuItem
            String menuInsert = "INSERT IGNORE INTO MenuItem VALUES (?, ?, ?, ?)";
            PreparedStatement psMenu = conn.prepareStatement(menuInsert);
            String[] itemNames = {"Pizza", "Pasta", "Burger", "Soda", "Salad", "Pancakes", "Coffee", "Tea", "Fries", "Soup"};
            double[] prices = {150, 80, 120, 40, 90, 110, 50, 30, 60, 95};
            
            for (int i = 1; i <= 10; i++) {
                psMenu.setInt(1, i);
                psMenu.setString(2, itemNames[i-1]);
                psMenu.setDouble(3, prices[i-1]);
                psMenu.setInt(4, (i % 10) + 1); // Distribute among ResId 1-10
                psMenu.executeUpdate();
            }

            // 4. Select records where price <= 100
            System.out.println("\n--- Menu Items with Price <= 100 ---");
            printMenuTable(stmt.executeQuery("SELECT * FROM MenuItem WHERE Price <= 100"));

            // 5. Select records from "Cafe Java"
            System.out.println("\n--- Menu Items at 'Cafe Java' ---");
            String cafeQuery = "SELECT m.* FROM MenuItem m JOIN Restaurant r ON m.ResId = r.Id WHERE r.Name = 'Cafe Java'";
            printMenuTable(stmt.executeQuery(cafeQuery));

            // 6. Update price <= 100 to 200
            stmt.executeUpdate("UPDATE MenuItem SET Price = 200 WHERE Price <= 100");
            System.out.println("\nUpdate Successful. Current Menu:");
            printMenuTable(stmt.executeQuery("SELECT * FROM MenuItem"));

            // 7. Delete names starting with 'P'
            stmt.executeUpdate("DELETE FROM MenuItem WHERE Name LIKE 'P%'");
            System.out.println("\nDeleted items starting with 'P'. Final Table:");
            printMenuTable(stmt.executeQuery("SELECT * FROM MenuItem"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to print ResultSet in tabular format
    private static void printMenuTable(ResultSet rs) throws SQLException {
        System.out.format("%-5s | %-15s | %-10s | %-5s\n", "ID", "Name", "Price", "ResId");
        System.out.println("----------------------------------------------");
        while (rs.next()) {
            System.out.format("%-5d | %-15s | %-10.2f | %-5d\n",
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getDouble("Price"),
                    rs.getInt("ResId"));
        }
    }
}