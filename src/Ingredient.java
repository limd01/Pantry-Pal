import java.text.DecimalFormat;

/**
 * // -------------------------------------------------------------------------
 * /** The ingredient class: represents an ingredient required to make a recipe
 * 
 * @author Anna Kinney
 * @version 2026.10.09
 */
public class Ingredient
{
    private String name;
    private double amount;
    private String units;
    private DecimalFormat df;

    /**
     * the constructor
     * 
     * @param name
     *            of ingredient
     * @param amount
     *            of ingredient
     * @param units
     *            of the amount of ingredient
     */
    public Ingredient(String name, double amount, String units)
    {
        this.name = name;
        this.amount = amount;
        this.units = units;
        this.df = new DecimalFormat("#.##");
    }


    /**
     * getter method for ingredient name
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }


    /**
     * getter method for amount of ingredient
     * 
     * @return amount
     */
    public double getAmount()
    {
        return amount;
    }


    /**
     * getter method for units
     * 
     * @return units
     */
    public String getUnits()
    {
        return units;
    }


    /**
     * toString method for ingredient
     * 
     * @return the ingredient with all it's constructed paramters as a String
     */
    @Override
    public String toString()
    {
        return name + ": " + df.format(amount) + " " + units;
    }
}
