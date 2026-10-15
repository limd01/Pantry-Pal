/**
 * // -------------------------------------------------------------------------
/**
 *  The recipe class
 *  provides a common parent type for all recipes and requires
 *  all recipe types to include the title and list of ingredients
 * 
 *  @author 
 *  @version Oct 15, 2026
 */
public class Recipe {
    private String title;
    private LinkedChain<Ingredient> ingredients;

    /**
     * constructor for recipe
     * @param title of recipe
     * @param ingredients needed for recipe
     */
    public Recipe(String title, LinkedChain<Ingredient> ingredients) {
        this.title = title;
        this.ingredients = ingredients;
    }

    /**
     * getter method for title
     * @return title of recipe
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter method for ingredients
     * @return ingredients needed for recipe
     */
    public LinkedChain<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * toString method
     * @return title and ingredients 
     */
    @Override
    public String toString() {
        String str = title + "\n";
        for (int i = 0; i < ingredients.size(); i++) {
            str += " - " + ingredients.get(i).toString() + "\n";
        }
        return str;
    }
}