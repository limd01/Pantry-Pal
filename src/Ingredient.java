import java.text.DecimalFormat;

public class Ingredient {
    private String name;
    private double amount;
    private String units;
    private DecimalFormat df;

    public Ingredient(String name, double amount, String units) {
        this.name = name;
        this.amount = amount;
        this.units = units;
        this.df = new DecimalFormat("#.##");
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnits() {
        return units;
    }

    public String toString() {
        return name + ": " + df.format(amount) + " " + units;
    }
}