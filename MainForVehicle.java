public class MainForVehicle {

    public static void main(String[] args) {

        // ---------- CREATE VEHICLE OBJECTS ----------
        vehicle defV = new vehicle();
        vehicle v2 = new vehicle("Honda", "City", 400000.99, "HON983022");
        vehicle v3 = new vehicle(v2);   // copy constructor

        // Modify third vehicle
        v3.setMfgCode("HON98765");
        v3.color = "Orange";

        // ---------- ASSIGN FUEL TYPES ----------
        defV.fuelType = FuelType.PETROL;
        v2.fuelType  = FuelType.DIESEL;
        v3.fuelType  = FuelType.CNG;

        // ---------- STORE IN ARRAY ----------
        vehicle[] myFleet = { defV, v2, v3 };

        // ---------- TABULAR OUTPUT ----------
        System.out.println("\n================ VEHICLE INFORMATION TABLE =================");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-12s %-12s %-12s %-12s %-12s%n",
                "Brand", "Model", "Price", "Fuel", "Mileage");
        System.out.println("---------------------------------------------------------------------");

        for (vehicle v : myFleet) {

            // Example trip values
            double mileage = v.calcTripMileage(100, 5);

            System.out.printf("%-12s %-12s %-12.2f %-12s %-12.2f%n",
                    v.brandName,
                    v.modelName,
                    v.price,
                    v.fuelType,
                    mileage);
        }

        System.out.println("---------------------------------------------------------------------");
    }
}
