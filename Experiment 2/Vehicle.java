import java.time.Year;

/* ====== ADDED (REQUIRED) ====== */
enum FuelType {
    PETROL, DIESEL, CNG, ELECTRIC
}
/* ============================== */

public class vehicle {

    public String brandName;
    public String modelName;
    public double price;
    public String color;

    private String mfgCode;
    private String regNo;

    public Year mfgYear;

    /* ====== ADDED (REQUIRED) ====== */
    public FuelType fuelType;
    /* ============================== */

    // getters and setters
    public void setMfgCode(String mCode) {
        mfgCode = mCode;
    }

    public String getMfgCode() {
        return mfgCode;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String rNo) {
        regNo = rNo;
    }

    public vehicle() {
        brandName = "Hyundai";
        modelName = "i10";
        color = "Silver";
        price = 500000.99;
        mfgCode = "HYN12345";
        regNo = "HYN98765";
        mfgYear = Year.of(2020);
    }

    public vehicle(String brandName, String modelName, double price, String mfgCode) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.mfgCode = mfgCode;
    }

    public vehicle(String modelName, double price, String regNo) {
        this.modelName = modelName;
        this.price = price;
        this.regNo = regNo;
    }

    public vehicle(String brandName, String modelName, double price, String color,
                   String mfgCode, String regNo, Year mfgYear) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.color = color;
        this.mfgCode = mfgCode;
        this.regNo = regNo;
        this.mfgYear = mfgYear;
    }

    public vehicle(vehicle v) {
        brandName = v.brandName;
        modelName = v.modelName;
        price = v.price;
        color = v.color;
        mfgCode = v.mfgCode;
        regNo = v.regNo;
        mfgYear = v.mfgYear;
        fuelType = v.fuelType;
    }

    public void start(int initSp) {
        System.out.println("i have started with and initial speed of " + initSp);
    }

    public void stop() {
        System.out.println("that was a nice ride");
    }

    public void drive(int initGear, int initSp, int tgtSp) {

        if (initGear != 1) {
            System.out.println("i always start moving at the first gear");
        }

        if (initSp < 20) {
            System.out.println("that is the ideal speed to start with.");
        } else {
            System.out.println("Be careful!");
        }

        if (tgtSp > 200) {
            System.out.println("look out for cops");
        } else {
            System.out.println("you are within the idea speed range");
        }
    }

    /* ====== MODIFIED (if–else added) ====== */
    public double calcTripMileage(double noOfKms, double fuelUsed) {

        if (fuelType == FuelType.PETROL) {
            return noOfKms / fuelUsed;
        } else if (fuelType == FuelType.DIESEL) {
            return (noOfKms / fuelUsed) * 1.2;
        } else if (fuelType == FuelType.CNG) {
            return (noOfKms / fuelUsed) * 1.4;
        } else if (fuelType == FuelType.ELECTRIC) {
            return noOfKms * 6;
        } else {
            return 0;
        }
    }
}
