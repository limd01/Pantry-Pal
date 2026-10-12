public class OnlineRecipe extends Recipe {
    private String link;
    
    public OnlineRecipe(String title, String link, LinkedChain<Ingredient> ingredients) {
        super(title, ingredients);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String toString() {
        String str = getTitle()  + " found at " + link + "\n";
        for (int i = 0; i < getIngredients().size(); i++) {
            str += " - " + getIngredients().get(i).toString() + "\n";
        }
        return str;
    }
}