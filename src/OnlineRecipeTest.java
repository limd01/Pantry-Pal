import student.TestCase;

/**
 * Tests for the OnlineRecipe class.
 *
 * @author Sarah Gage sarahg30
 * @version 10.15.2026
 */
public class OnlineRecipeTest
    extends TestCase
{

    private OnlineRecipe recipe;
    private LinkedChain<Ingredient> ingredients;

    /**
     * Creates online recipe with title and three ingredients
     */
    public void setUp()
    {
        Ingredient ingredient1 = new Ingredient("Apple", 2, "cup");
        Ingredient ingredient2 = new Ingredient("Banana", 3.0, "whole");
        Ingredient ingredient3 = new Ingredient("Carrot", 0.5, "cup");
        ingredients = new LinkedChain<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        recipe = new OnlineRecipe(
            "Basic Recipe",
            "www.goodRecipes.com",
            ingredients);
    }


    /**
     * Test getTitle()
     */
    public void testGetTitle()
    {
        assertEquals(recipe.getTitle(), "Basic Recipe");
    }


    /**
     * Test getIngredients()
     */
    public void testGetIngredients()
    {
        assertEquals(recipe.getIngredients(), ingredients);
    }


    /**
     * Test getLink()
     */
    public void testGetLink()
    {
        assertEquals(recipe.getLink(), "www.goodRecipes.com");
    }


    /**
     * Test toString()
     */
    public void testToString()
    {
        String str =
            "Basic Recipe found at www.goodRecipes.com\n - Carrot: 0.5 cup\n - Banana: 3 whole\n - Apple: 2 cup\n";
        assertEquals(recipe.toString(), str);
    }
}
