import java.time.LocalDate;

abstract class Employee {
    protected String name;
    protected String PANNo;
    protected LocalDate joiningDate;
    protected String designation;
    protected int empId;

    public Employee(String name, String PANNo, LocalDate joiningDate, String designation, int empId) {
        this.name = name;
        this.PANNo = PANNo;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.empId = empId;
    }

    // Abstract Method
    public abstract double calcCTC();

    public void displayDetails() {
        System.out.println("ID: " + empId);
        System.out.println("Name :" + name);
        System.out.println("Designation :" + designation);
        System.out.println("Joining Date: " + joiningDate);
    }
}
