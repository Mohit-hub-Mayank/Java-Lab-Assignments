import java.io.*;
import java.util.*;

public class StudentsCRUD {

    static String fileName = "Students.csv";

    public static void main(String[] args) {

        try {
            createFile();
            addInitialRows();
            addMoreRows();
            updateMarks();
            calculatePercentage();
            deleteRow(2); // delete 2nd student
            displayFile();

            // Demonstrate Exception
            demonstrateException();

        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    // 1. Create File with Header
    public static void createFile() throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write("studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage\n");
        fw.close();
        System.out.println("File created with header.");
    }

    // 2. Add Initial Rows
    public static void addInitialRows() throws IOException {
        FileWriter fw = new FileWriter(fileName, true);

        fw.write("1,John,CSE,80,85,90,0,0,0\n");
        fw.write("2,Alice,IT,75,70,80,0,0,0\n");

        fw.close();
        System.out.println("Initial rows added.");
    }

    // 3. Add 3 More Rows
    public static void addMoreRows() throws IOException {
        FileWriter fw = new FileWriter(fileName, true);

        fw.write("3,Bob,ECE,60,65,70,0,0,0\n");
        fw.write("4,Emma,MECH,88,92,85,0,0,0\n");
        fw.write("5,Raj,CSE,78,82,80,0,0,0\n");

        fw.close();
        System.out.println("3 more rows added.");
    }

    // 4. Update Marks (marks4 & marks5)
    public static void updateMarks() throws IOException {
        List<String[]> data = readFile();

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);

            row[6] = String.valueOf(70 + i); // marks4
            row[7] = String.valueOf(75 + i); // marks5
        }

        writeFile(data);
        System.out.println("Marks updated.");
    }

    // 5. Calculate Percentage
    public static void calculatePercentage() throws IOException {
        List<String[]> data = readFile();

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);

            int sum = 0;
            for (int j = 3; j <= 7; j++) {
                sum += Integer.parseInt(row[j]);
            }

            double percentage = sum / 5.0;
            row[8] = String.format("%.2f", percentage);
        }

        writeFile(data);
        System.out.println("Percentage calculated and updated.");
    }

    // 6. Delete Row
    public static void deleteRow(int rowNumber) throws IOException {
        List<String[]> data = readFile();

        if (rowNumber < data.size()) {
            data.remove(rowNumber);
            writeFile(data);
            System.out.println("Row " + rowNumber + " deleted.");
        } else {
            System.out.println("Row not found.");
        }
    }

    // Read CSV
    public static List<String[]> readFile() throws IOException {
        List<String[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = br.readLine()) != null) {
            data.add(line.split(","));
        }

        br.close();
        return data;
    }

    // Write CSV
    public static void writeFile(List<String[]> data) throws IOException {
        FileWriter fw = new FileWriter(fileName);

        for (String[] row : data) {
            fw.write(String.join(",", row) + "\n");
        }

        fw.close();
    }

    // Display File
    public static void displayFile() throws IOException {
        System.out.println("\nFinal File Content:");

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }

    // 7. Demonstrate Exception Handling
    public static void demonstrateException() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("WrongFile.csv"));
            br.readLine();
            br.close();
        } catch (IOException e) {
            System.out.println("\nException Demonstrated Successfully:");
            System.out.println(e);
        }
    }
}
