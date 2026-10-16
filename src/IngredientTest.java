/**
 * Tests for the Ingredient class.
 *
 * @author Sarah Gage sarahg30
 * @version 10.15.2026
 */
public class IngredientTest
    extends student.TestCase
{

    private Ingredient ingredient;

    /**
     * Creates brand new ingredient
     */
    public void setUp()
    {
        ingredient = new Ingredient("Apple", 2, "cup");
    }


    /**
     * Test getName()
     */
    public void testGetName()
    {
        assertEquals(ingredient.getName(), "Apple");
    }


    /**
     * Test getAmount()
     */
    public void testGetAmount()
    {
        assertEquals(ingredient.getAmount(), 2.0, 0.001);
    }


    /**
     * Test getUnits()
     */
    public void testGetUnits()
    {
        assertEquals(ingredient.getUnits(), "cup");
    }


    /**
     * Test toString()
     */
    public void testToString()
    {
        String str = "Apple: 2 cup";
        assertEquals(ingredient.toString(), str);
    }
}
