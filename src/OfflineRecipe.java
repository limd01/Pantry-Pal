/**
 * // -------------------------------------------------------------------------
/**
 *  The offline recipe class represents a recipe not available online
 * 
 *  @author 
 *  @version Oct 15, 2026
 */
public class OfflineRecipe extends Recipe {
    private String creator;
    
    /**
     * constructor for offlineRecipe
     * @param title of recipe
     * @param creator of recipe
     * @param ingredients needed for the recipe
     */
    public OfflineRecipe(String title, String creator, LinkedChain<Ingredient> ingredients) {
        super(title, ingredients);
        this.creator = creator;
    }
    
    /**
     * getter method for the creator of the recipe
     * @return creator of recipe
     */
    public String getCreator() {
        return creator;
    }

    /**
     * toString method
     * @return title, creator, and ingredients needed as a String
     */
    @Override
    public String toString() {
        String str = getTitle()  + " by " + creator + "\n";
        for (int i = 0; i < getIngredients().size(); i++) {
            str += " - " + getIngredients().get(i).toString() + "\n";
        }
        return str;
    }
}