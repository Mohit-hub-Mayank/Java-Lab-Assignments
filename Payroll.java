import java.time.LocalDate;

public class Payroll {
    public static void main(String[] args) {

        FullTimeEmployee emp1 = new FullTimeEmployee(
                "Amit", "ABCDE1234F",
                LocalDate.of(2022, 5, 10),
                "Software Engineer", 101,
                500000, 50000, "SWE"
        );

        ContractEmployee emp2 = new ContractEmployee(
                "Ravi", "LMNOP4321K",
                LocalDate.of(2023, 1, 20),
                "Contract Dev", 102,
                100, 400
        );

        Manager mgr = new Manager(
                "Suresh", "ZXCVB9876Q",
                LocalDate.of(2020, 7, 1),
                "Project Manager", 103,
                800000, 100000,
                50000, 30000
        );

        // Output
        System.out.println("\n--- Full Time Employee ---");
        emp1.displayDetails();
        System.out.println("CTC: " + emp1.calcCTC());

        System.out.println("\n--- Contract Employee ---");
        emp2.displayDetails();
        System.out.println("CTC: " + emp2.calcCTC());

        System.out.println("\n--- Manager ---");
        mgr.displayDetails();
        System.out.println("CTC: " + mgr.calcCTC());
    }
}
