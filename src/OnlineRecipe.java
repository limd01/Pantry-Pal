/**
 * // -------------------------------------------------------------------------
/**
 *  The online recipe class represents a recipe available online
 *  link for the recipe is read by main class
 *  @author Aditi Samaraju
 *  @version 2026.10.09
 */
public class OnlineRecipe extends Recipe {
    private String link;
    
    /**
     * constructor for OnlineRecipe
     * @param title of recipe
     * @param link to recipe
     * @param ingredients needed for recipe
     */
    public OnlineRecipe(String title, String link, LinkedChain<Ingredient> ingredients) {
        super(title, ingredients);
        this.link = link;
    }

    /**
     * getter method for link
     * @return link to recipe
     */
    public String getLink() {
        return link;
    }

    /**
     * toString method
     * @return recipe title, link, and ingredient list
     */
    @Override
    public String toString() {
        String str = getTitle()  + " found at " + link + "\n";
        for (int i = 0; i < getIngredients().size(); i++) {
            str += " - " + getIngredients().get(i).toString() + "\n";
        }
        return str;
    }
}