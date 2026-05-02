import java.time.LocalDate;

class Manager extends FullTimeEmployee {
    private double travelAllowance;
    private double educationAllowance;

    public Manager(String name, String PANNo, LocalDate joiningDate,
                   String designation, int empId,
                   double baseSalary, double perfBonus,
                   double travelAllowance, double educationAllowance) {

        super(name, PANNo, joiningDate, designation, empId,
              baseSalary, perfBonus, "Manager");

        this.travelAllowance = travelAllowance;
        this.educationAllowance = educationAllowance;
    }

    @Override
    public double calcCTC() {
        return baseSalary + perfBonus + travelAllowance + educationAllowance;
    }
}

