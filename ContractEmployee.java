import java.time.LocalDate;

class ContractEmployee extends Employee {
    private int noOfHours;
    private double hourlyRate;

    public ContractEmployee(String name, String PANNo, LocalDate joiningDate,
            String designation, int empId,
            int noOfHours, double hourlyRate) {
        super(name, PANNo, joiningDate, designation, empId);
        this.noOfHours = noOfHours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calcCTC() {
        return noOfHours * hourlyRate;
    }
}