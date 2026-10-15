import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Main is used to maintain the user's interactions with the
 * game, handle bad input, and keep track of time and
 * distance left to class.
 *
 * @author Sarah Gage
 * @version 2026.10.09
 */
public class Main {
    private LinkedChain<Recipe> recipeBook;
    private String fileName;

    // ----------------------------------------------------------
    /**
     * Maintains the user's interactions with the application, handles
     * bad input, and keep track recipe book.
     */
    public Main() {
        // Create scanner to read in user inputs
        Scanner scanner = new Scanner(System.in);
        
        boolean inUse = true;

        fileName = "RecipeBook.csv";

        recipeBook = readRecipeFile(fileName);

        System.out.println("Welcome to Pantry Pal!");
        System.out.println("I am here to help you with dinner. We can review recipes, create grocery lists, and more!\n");

        Recipe temp;

        while (inUse) {
            if (recipeBook.size() == 0) {
                boolean madeSelection = false;
                while (!madeSelection) {
                    System.out.print("There are currently no recipes in your recipe book. Would you like to add your first recipe (y) or exit (n)? ");
                    String input = scanner.nextLine();
                    if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
                        temp = readNewRecipe(scanner);
                        if (temp != null) {
                            recipeBook.add(temp);
                            madeSelection = true;
                        }
                    } 
                    else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
                        madeSelection = true;
                        inUse = false;
                    }
                }
            }
            else {
                printMenu();
                switch (readMenuInput(scanner)) {
                    case 'A':
                        outputRecipeList();
                        break;
                    case 'B':
                        temp = readNameInput(scanner);
                        if (temp != null) {
                            System.out.println(temp.toString());
                        }
                        break;
                    case 'C':
                        temp = readNewRecipe(scanner);
                        if (temp != null) {
                            recipeBook.add(temp);
                        }
                        break;
                    case 'D':
                        temp = readNameInput(scanner);
                        if (temp != null) {
                            if (recipeBook.remove(temp)) {
                                System.out.println("Successfully removed recipe.");
                            } else {
                                System.out.println("Failed to remove recipe.");
                            }   
                        }
                        break;
                    case 'E':
                        makeGroceryList(scanner);
                        break;
                    case 'F':
                        recipeRoulette(scanner);
                        break;
                    case 'G':
                        System.out.println("Enjoy your meal! Come back again soon!");
                        saveRecipeBook(fileName, recipeBook);
                        inUse = false;
                        break;
                    case 'Z':
                        break;
                    default:
                        System.out.print("Please make a selection from the menu");
                }
            }
        }
    }

    /**
     * main method that maintains recipe book and user interaction
     * @param args
     */
    public static void main(String args[]) {
        new Main();
    }

    private LinkedChain<Recipe> readRecipeFile(String fileName) {
        LinkedChain<Recipe> retrievedRecipeBook = new LinkedChain<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                LinkedChain<Ingredient> recipeIngredients = new LinkedChain<>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())) {
                    rowScanner.useDelimiter(" \\| ");
                    String recipeTitle = rowScanner.next();
                    String next = rowScanner.next();
                    while (!next.equals("0")) {
                        recipeIngredients.add(new Ingredient(next, Double.parseDouble(rowScanner.next()), rowScanner.next()));
                        next = rowScanner.next();
                    }
                    String type = rowScanner.next(); 
                    if (type.equals("1")) {
                        retrievedRecipeBook.add(new OnlineRecipe(recipeTitle, rowScanner.next(), recipeIngredients));
                    } else {
                        retrievedRecipeBook.add(new OfflineRecipe(recipeTitle, rowScanner.next(), recipeIngredients));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not find RecipeBook save file. Creating new file.");
            saveRecipeBook(fileName, retrievedRecipeBook);

        }
        return retrievedRecipeBook;
    }

    private static void saveRecipeBook(String fileName, LinkedChain<Recipe> recipeBook) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < recipeBook.size(); i++) {
                String str = recipeBook.get(i).getTitle();
                LinkedChain<Ingredient> ingredients = recipeBook.get(i).getIngredients();
                for (int j = 0; j < ingredients.size(); j++) {
                    Ingredient ingredient = ingredients.get(j);
                    str += " | " + ingredient.getName() + " | " + ingredient.getAmount() + " | " + ingredient.getUnits();
                }
                str += " | 0 | ";
                if (recipeBook.get(i) instanceof OnlineRecipe) {
                    str += "1 | " + ((OnlineRecipe) recipeBook.get(i)).getLink();
                } else {
                    str += "2 | " + ((OfflineRecipe) recipeBook.get(i)).getCreator();
                }
                out.println(str);
            }
        } catch (IOException e) {
            System.out.println("Could not write to RecipeBook file.");
        }
    }

    private static void printMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("A) See existing recipes in your recipe book");
        System.out.println("B) View details of recipe in your recipe book");
        System.out.println("C) Add a recipe to your recipe book");
        System.out.println("D) Remove a recipe from your recipe list");
        System.out.println("E) Start grocery list");
        System.out.println("F) Recipe Roulette: Make a random recipe!");
        System.out.println("G) Exit");
    }

    private static char readMenuInput(Scanner scanner) {
        Boolean madeSelection = false;
        char selection = 'Z';

        while (!madeSelection) {
            System.out.print("Please select an option from the menu: ");
            String input = scanner.nextLine();

            if (input.equals("")) {
                System.out.println(
                    "It does not look like you selected an option. Make sure to enter a letter selection from the menu.");
            }
            else {
                char[] inputArray = input.toCharArray();
                if (inputArray.length > 1) {
                    System.out.println(
                        "It looks like you entered more than one character. Please enter a single letter selection from the menu.");
                }
                else {
                    char inputChar = Character.toUpperCase(inputArray[0]);
                    if ((int)inputChar - 65 < 0 || (int)inputChar - 65 >= 7) {
                        System.out.println(
                            "It looks like you entered a selection that was not available in the menu. Please enter a single letter selection that is available in the menu.");
                    }
                    else {
                        selection = inputChar;
                        madeSelection = true;
                    }
                }
            }
        }
        System.out.println();
        return selection;
    }

    private Recipe readNameInput(Scanner scanner) {
        while (true) {
            System.out.print("Would you like to search with the specific title of the recipe (1), view titles of existing recipes (2), or \"exit\"? ");
            String input = scanner.nextLine();

            if (input.equals("")) {
                System.out.println(
                    "It does not look like you answered. Make sure to enter either '1' or '2'.");
            }
            else {
                if (input.equals("1")) {
                    System.out.print("Enter the exact recipe title or \"exit\": ");
                    System.out.flush();
                    input = scanner.nextLine();

                    if (input.equals("")) {
                        System.out.println(
                            "It does not look like you entered a name. Make sure to enter the exact name from list.");
                    }
                    else if (input.toLowerCase().equals("exit")) {
                        return null;
                    }
                    else {
                        input = input.toLowerCase();
                        for (int i = 0; i < recipeBook.size(); i++) {
                            if (recipeBook.get(i).getTitle().toLowerCase().equals(input)) {
                                return recipeBook.get(i);
                            }
                        }
                        System.out.println("It looks like you entered a recipe title that is not currently in the recipe book. Please enter a recipe in the book.");
                    }
                } 
                else if (input.equals("2")) {
                    outputRecipeList();
                } 
                else if (input.equals("exit")) {
                    return null;
                }
                else {
                    System.out.println(
                        "It looks like you entered a something other than expected. Please enter a either '1' or '2'.");
                }
            }
        }
    }

    private void outputRecipeList() {
        System.out.println("Recipes in Recipe List:");
        for (int i = 0; i < recipeBook.size(); i++) {
            System.out.println(" - " + recipeBook.get(i).getTitle());
        }
    }

    private Recipe readNewRecipe(Scanner scanner) {
        Boolean madeSelection = false;
        String title = "";
        String linkOrCreator = "";
        LinkedChain<Ingredient> ingredients = new LinkedChain<>();
        boolean onlineRecipe = false;

        while (!madeSelection) {
            System.out.print("Would you like to create an online recipe (1), offline recipe (2), or \"exit\"? ");
            String input = scanner.nextLine();

            if (input.equals("")) {
                System.out.println(
                    "It does not look like you answered. Make sure to enter either '1' or '2'.");
            }
            else {
                if (input.equals("1")) {
                    onlineRecipe = true;
                    madeSelection = true;
                } 
                else if (input.equals("2")) {
                    onlineRecipe = false;
                    madeSelection = true;
                } 
                else if (input.toLowerCase().equals("exit")) {
                    return null;
                }
                else {
                    System.out.println(
                        "It looks like you entered a something other than expected. Please enter a either '1' or '2'.");
                }
            }
        }

        madeSelection = false;
        while (!madeSelection) {
            if (onlineRecipe) {
                System.out.print("Please enter a link for the recipe or \"exit\": ");
            }
            else {
                System.out.print("Please enter a creator for the recipe or \"exit\": ");
            }
            String input = scanner.nextLine();

            if (input.equals("")) {
                System.out.println(
                    "The input cannot be empty. Please enter the requested input.");
            }
            else if (input.toLowerCase().equals("exit")) {
                return null;
            }
            else {
                linkOrCreator = input;
                madeSelection = true;
            }
        }

        madeSelection = false;
        while (!madeSelection) {
            System.out.print("Please enter a title for the recipe or \"exit\": ");
            String input = scanner.nextLine();

            if (input.equals("")) {
                System.out.println(
                    "The title cannot be empty. Please enter a title for the recipe.");
            }
            else if (input.toLowerCase().equals("exit")) {
                return null;
            }
            else {
                boolean titleUsed = false;
                for (int i = 0; i < recipeBook.size() && !titleUsed; i++) {
                    if (recipeBook.get(i).getTitle().equals(input)) {
                        System.out.println("This title is already used in another recipe. Please enter a different title.");
                        titleUsed = true;
                    }
                }
                if (!titleUsed) {
                    title = input;
                    madeSelection = true;
                }
            }
        }

        madeSelection = false;
        while (!madeSelection) {
            System.out.print("Would you like to add an ingredient to the recipe (y/n) or \"exit\"? ");
            String input = scanner.nextLine();

            if (input.equals("")) {
                System.out.println(
                    "It does not look like you answered. Make sure to enter either 'y' or 'n'.");
            }
            else {
                if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
                    
                    boolean madeSelection1 = false;
                    String name = "";
                    double amount = 0.0;
                    String unit = "";

                    while (!madeSelection1) {
                        System.out.print("Please enter the name of the ingredient: ");
                        input = scanner.nextLine();

                        if (input.equals("")) {
                            System.out.println(
                                "The name cannot be empty. Please enter the name of the ingredient.");
                        }
                        else {
                            name = input;
                            madeSelection1 = true;
                        }
                    }

                    madeSelection1 = false;
                    while (!madeSelection1) {
                        System.out.print("Please enter the amount of the ingredient required: ");
                        input = scanner.nextLine();

                        if (input.equals("")) {
                            System.out.println(
                                "The amount cannot be empty. Please enter the amount of the ingredient required.");
                        }
                        else {
                            try {
                                amount = Double.parseDouble(input);
                                madeSelection1 = true;
                            } catch (NumberFormatException e) {
                                System.out.println("The input could not be converted to a number. Please enter a number with the format \"#.##\".");
                                madeSelection1 = false;
                            }
                        }
                    }

                    madeSelection1 = false;
                    while (!madeSelection1) {
                        System.out.print("Please enter the unit of the amount of the ingredient: ");
                        input = scanner.nextLine();

                        if (input.equals("")) {
                            System.out.println(
                                "The unit cannot be empty. Please enter the unit of the amount of the ingredient.");
                        }
                        else {
                            unit = input;
                            madeSelection1 = true;
                        }
                    }

                    ingredients.add(new Ingredient(name, amount, unit));

                }
                else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
                    if (ingredients.size() == 0) {
                        System.out.println("You must enter at least one ingredient. Please enter an ingredient");
                    } else {
                        madeSelection = true;
                    }
                }
                else if (input.toLowerCase().equals("exit")) {
                    return null;
                }
                else {
                    System.out.println(
                        "It looks like you entered a something other than expected. Please enter a either 'y' or 'n'.");
                }
            }
        }
        
        if (onlineRecipe) {
            return new OnlineRecipe(title, linkOrCreator, ingredients);
        } else {
            return new OfflineRecipe(title, linkOrCreator, ingredients);
        }
    }

    private void makeGroceryList(Scanner scanner) {
        LinkedChain<Recipe> recipeList = new LinkedChain<>();
        boolean addRecipe = true;
        while (addRecipe) {
            Boolean madeSelection = false;

            while (!madeSelection) {
                System.out.print("Would you like to add a new recipe to grocery list (y/n) or \"exit\"? ");
                String input = scanner.nextLine();

                if (input.equals("")) {
                    System.out.println(
                        "It does not look like you answered. Make sure to enter either 'y' or 'n'.");
                }
                else {
                    if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
                        Recipe temp = readNameInput(scanner);
                        if (temp != null) {
                            recipeList.add(temp);
                        }
                        madeSelection = true;
                    } 
                    else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
                        madeSelection = true;
                        addRecipe = false;
                    } 
                    else if (input.toLowerCase().equals("exit")){
                        madeSelection = true;
                        addRecipe = false;
                        recipeList = null;
                    }
                    else {
                        System.out.println(
                            "It looks like you entered a something other than expected. Please enter a either 'y' or 'n'.");
                    }
                }
            }
        }

        if (recipeList != null) {
            List<String> ingredientNames = new ArrayList<>();
            List<String> output = new ArrayList<>();
            for (int i = 0; i < recipeList.size(); i++) {
                Recipe recipe = recipeList.get(i);
                System.out.println("Checking recipe: " + recipe.getTitle());
                for (int j = 0; j < recipe.getIngredients().size(); j++) {
                    Ingredient ingredient = recipe.getIngredients().get(j);
                    System.out.println("Checking ingredient: " + ingredient.getName());
                    if (ingredientNames.contains(ingredient.getName())) {
                        for (int k = 0; k < ingredientNames.size(); k++) {
                            if (ingredientNames.get(k).toLowerCase().equals(ingredient.getName().toLowerCase())) {
                                System.out.print("Found same: " + ingredient.getName());
                                output.set(k, output.get(k) + ", " + ingredient.getAmount() + " " + ingredient.getUnits());
                            }
                        } 
                    } else {
                        System.out.println("New so adding to grocery list.");
                        ingredientNames.add(ingredient.getName());
                        output.add(" - " + ingredient.toString());
                    }
                }
            }

            System.out.println("\nList of Groceries: ");
            for (int i = 0; i < output.size(); i++) {
                System.out.println(output.get(i));
            }
        }
    }

    private void recipeRoulette(Scanner scanner) {
        Random random = new Random();

        System.out.println("What would you like to do?");
        System.out.println("A) Get a random recipe");
        System.out.println("B) Get a random recipe with a specified ingredient");
        System.out.println("C) Exit");

        boolean madeSelection = false;
        char selection = 'Z';

        while (!madeSelection) {
            System.out.print("Please select an option from the menu: ");
            String input = scanner.nextLine();

            if (input.equals("")) {
                System.out.println(
                    "It does not look like you selected an option. Make sure to enter a letter selection from the menu.");
            }
            else {
                char[] inputArray = input.toCharArray();
                if (inputArray.length > 1) {
                    System.out.println(
                        "It looks like you entered more than one character. Please enter a single letter selection from the menu.");
                }
                else {
                    char inputChar = Character.toUpperCase(inputArray[0]);
                    if (inputChar != 'A' && inputChar != 'B' && inputChar != 'C') {
                        System.out.println(
                            "It looks like you entered a selection that was not available in the menu. Please enter a single letter selection that is available in the menu.");
                    }
                    else {
                        selection = inputChar;
                        madeSelection = true;
                    }
                }
            }
        }

        if (selection == 'A') {
            System.out.println("\n" + recipeBook.get(random.nextInt(recipeBook.size())).toString());
        } 
        else if (selection == 'B') {
            System.out.print("What ingredient would you like to limit the randomized selection by or \"exit\": ");
            String input = scanner.nextLine();
            if (!(input.toLowerCase().equals("exit"))) {
                List<Integer> indexes = new ArrayList<>();
                for (int i = 0; i < recipeBook.size(); i++) {
                    boolean found = false;
                    Recipe recipe = recipeBook.get(i);
                    for (int j = 0; j < recipe.getIngredients().size() && !found; j++) {
                        if (recipe.getIngredients().get(j).getName().toLowerCase().equals(input.toLowerCase())) {
                            indexes.add(i);
                            found = true;
                        }
                    }
                }

                if (indexes.size() > 0) {
                    System.out.println(recipeBook.get(indexes.get(random.nextInt(indexes.size()))));
                } else {
                    System.out.println("There are no recipes in the recipe book that use the ingredient: " + input + ".");
                }
            }
        }
    }
}