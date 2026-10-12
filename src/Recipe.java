public class Recipe {
    private String title;
    private LinkedChain<Ingredient> ingredients;

    public Recipe(String title, LinkedChain<Ingredient> ingredients) {
        this.title = title;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public LinkedChain<Ingredient> getIngredients() {
        return ingredients;
    }

    public String toString() {
        String str = title + "\n";
        for (int i = 0; i < ingredients.size(); i++) {
            str += " - " + ingredients.get(i).toString() + "\n";
        }
        return str;
    }
}