import student.TestCase;
import java.nio.file.*;

public class MainTest
    extends student.TestCase
{

    private Path file;
    private byte[] oldFile;

    public void setUp()
        throws Exception
    {

        file = Paths.get("RecipeBook.csv");

        // save the original file
        if (Files.exists(file))
        {
            oldFile = Files.readAllBytes(file);
        }
        else
        {
            oldFile = null;
        }

        // create test recipe
        String recipe = "Pancakes | Egg | 2.0 | pieces | 0 | 2 | Jamie\n";
        Files.write(file, recipe.getBytes("UTF-8"));
    }


// Test viewing recipes
    public void testViewRecipes()
    {
        setSystemIn("A\nG\n");
        Main.main(new String[0]);

        assertTrue(systemOut().getHistory().contains("Pancakes"));
    }


// Test invalid menu choice
    public void testInvalidMenu()
    {
        setSystemIn("X\nG\n");
        Main.main(new String[0]);

        assertTrue(
            systemOut().getHistory()
                .contains("selection that was not available"));
    }


// Test empty menu choice
    public void testEmptyMenu()
    {
        setSystemIn("\nG\n");
        Main.main(new String[0]);

        assertTrue(
            systemOut().getHistory()
                .contains("It does not look like you selected"));
    }


// Test recipe that does not exist
    public void testMissingRecipe()
    {
        setSystemIn("B\n1\nUnknown\nexit\nG\n");
        Main.main(new String[0]);

        assertTrue(
            systemOut().getHistory()
                .contains("not currently in the recipe book"));
    }


// Test invalid ingredient amount
    public void testInvalidAmount()
    {
        setSystemIn("C\n2\nJamie\nSoup\ny\nWater\n" + "abc\n1.5\ncups\nn\nG\n");
        Main.main(new String[0]);

        assertTrue(
            systemOut().getHistory()
                .contains("could not be converted to a number"));
    }


    /**
     * Tests removing an existing recipe.
     */
    public void testRemoveRecipe()
    {
        setSystemIn("D\n1\nPancakes\nn\n");
        Main.main(new String[0]);

        assertTrue(
            systemOut().getHistory().contains("Successfully removed recipe."));
    }


    /**
     * Tests creating a grocery list.
     */
    public void testGroceryList()
    {
        setSystemIn("E\ny\n1\nPancakes\nn\nG\n");
        Main.main(new String[0]);

        assertTrue(systemOut().getHistory().contains("List of Groceries:"));
        assertTrue(systemOut().getHistory().contains("Egg"));
    }


    /**
     * Tests selecting a random recipe.
     */
    public void testRecipeRoulette()
    {
        setSystemIn("F\nA\nG\n");
        Main.main(new String[0]);

        assertTrue(systemOut().getHistory().contains("Pancakes"));
    }


// Restore original file
    public void tearDown()
        throws Exception
    {
        if (oldFile != null)
        {
            Files.write(file, oldFile);
        }
        else
        {
            Files.deleteIfExists(file);
        }
    }
}
