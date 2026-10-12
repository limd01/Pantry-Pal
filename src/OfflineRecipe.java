public class OfflineRecipe extends Recipe {
    private String creator;
    
    public OfflineRecipe(String title, String creator, LinkedChain<Ingredient> ingredients) {
        super(title, ingredients);
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public String toString() {
        String str = getTitle()  + " by " + creator + "\n";
        for (int i = 0; i < getIngredients().size(); i++) {
            str += " - " + getIngredients().get(i).toString() + "\n";
        }
        return str;
    }
}