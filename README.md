# CS 2114 Project 2 - Pantry Pal

Pantry Pal is a resource to people to manage their recipes, 
view their ingredients, create grocery lists, and choose 
their meals.

## How to Compile and Run

Ensure Java JDK 17 or later is installed.

Open a terminal in the project's root directory and run:

```bash
javac -d out src/Ingredient.java src/LinkedChain.java src/Main.java src/OfflineRecipe.java src/OnlineRecipe.java src/Recipe.java
java -cp out Main
```

Run the project from root directory so the program can load the recipes correctly.

## Example recipes to copy into RecipeBook
Boiled Cookies | vanilla | 1.0 | tsp | chopped nuts | 0.5 | cup | peanut butter | 0.5 | cup | quick oats | 3.0 | cup | cocoa | 0.5 | cup | butter | 0.5 | cup | milk | 0.5 | cup | sugar | 2.0 | cup | 0 | 2 | Grandma
Cranberry Salad | orange juice | 1.0 | cup | crushed pineapple | 1.0 | cup | walnuts | 1.0 | cup | crushed cranberries | 1.0 | cup | sugar | 0.75 | cup | cherry jello | 1.5 | boxes | 0 | 2 | Grandma
Bananna Bread | bananna | 3.0 | ripe | all purpose flour | 2.0 | cup | baking soda | 1.0 | tsp | egg | 2.0 | whole | white sugar | 1.0 | cup | shortening | 0.5 | cup | 0 | 2 | Grandma
Oatmeal Cookies | flour | 2.0 | cup | baking powder | 1.0 | tsp | salt | 1.0 | tsp | cinnamon | 1.0 | tsp | baking soda | 0.5 | tsp | quick oats | 3.0 | cup | raisins | 1.0 | cup | margarine | 1.25 | cup | sugar | 1.75 | cup | egg | 2.0 | whole | milk | 0.25 | cup | 0 | 2 | Grandma
Yuletide Gummies | butter | 0.5 | lb | conf. sugar | 0.5 | cup | sifted flour | 2.0 | cup | salt | 0.25 | tsp | vanilla | 1.0 | tsp | chopped walnuts | 1.0 | cup | 0 | 2 | Grandma

## Reflection
Overall, we made only a few changes to the basic structure of our classes and methods. The Recipe, Ingredient, OnlineRecipe, and OfflineRecipe classes were implemented according to our original design and the specifications we created for our program. In our main method, we added a system called recipe roulette, where you can make a random recipe. Recipe Roulette takes in user input to either display a random recipe or a random recipe that contains a specified ingredient. This was one of our stretch goals that we were able to successfully implement into our program. Our input validation had one change, we added the option for the user to exit any selection at any time. This prevents the user from getting stuck in a position where they need to enter a recipe title but cannot find a valid name for example. We also added new validation in the recipe roulette selection.

Also, in Main.java, we added four methods that were not in our spec. We added a method that guides the user through inputting values for a new recipe the user is adding to the recipe book and handles bad inputs then returns either the new recipe or null if the user decided to exit. This method was created to keep everything more organized and separate checking existing recipes from recipes you are about to create. This change originated out of individual reflection when implementing the main class. Next, we created a method that saves the current version of the recipe book to the recipe book file. This method was created to organize this routine outside of the main switch case. This change originated out of individual reflection when implementing the main class. Then, we created methods that handle both the recipe roulette selection and the creation of a grocery list. Both take in user inputs, handle bad inputs and display recipes based on the user's selections. This change originated out of group discussion when we decided to add these stretch goals. Finally, we removed the output ingredients list method as this is a duplication of information found in the recipe's toString method and therefore not neccessary. This change originated out of individual reflection when implementing the main class.

Our group’s Linked Chain class and Node class implementation experienced no modifications. These classes worked as expected.

When it comes to our unknowns, our biggest one was how to implement and read linked chains that contained linked chains. We determined the best approach was writing to a csv file such that one row corresponds to one recipe. We then added information to the file in the same pattern each time. A row starts with the recipe name and then the ingredients in the order name, amount, unit. The each ingredient is added to the row and when reading, we know all the ingredients have been listed when we reach a 0 entry. Then, either a 1 or a 2 entry is listed such that a 1 entry means the recipe is an online recipe and a 2 entry means the recipe is an offline recipe. Next, either the link or creator is listed. Finally, each entry is broken by a " | " delimeter. This means this unknown is closed. However, it did create a new unknown. For our project, we write out the csv file each time by rewriting every single recipe. This can become very time consuming when there are a lot of recipes in the recipe book. Our current method is therefore not very time efficient and we do not yet know the most efficient method.

Currently, the programs meets the expectations set in the MVP and does not show any errors differently from expected. However, we would like to improve the bad file handling in the Main class. The method currently creates a new recipe book file when one does not yet exist and prompts the user to add their first recipe and currectly reads in empty files. However, this could be improved to handle when the entries are not in the order or type expected. 

## Next Steps

Currently, we have finised the MVP and the stretch goals of allowing the user to compile a grocery list of ingredients from multiple recipes and allowing the user to get a random recipe that contains a specific ingredient from the recipe roulette selectoin. Beyond the MVP, we want to include more validation within reading in an out the file as previously discussed. Next, when adding recipes our program currently does not ignore case input when checking if the recipe book already has the recipe the user wants to add, so that bad input needs to be fixed. Finally, in the main class, our bad input handling for when a user inputs a space only checks for a single space and not multiple so we need to trim the input to confirm it is empty. These improvement we found as we implemented the project and we believe would greatly improve the project.

Our current stretch goals include: the ability for the user to compile a list of ingredients across multiple recipes, a roulette function that returns a random recipe based on an ingredient the user inputs, and the additon of a search feature in which the user can enter part of the title to filter a list of recipe title to allow them to find a specific recipe. We complete the first two stretch goals in their entirety. We will attempt to add the third stretch goal into the final design.

For our third stretch goal, we will need to create a new method which only handles searching. This can be called in other methods in main to allow the user to search at multiple times. For example, the user many want to search when they are want to view a recipe or when they are adding a recipe's ingredients to their grocery list. The method should return the index of the recipe that was searched for. Validation within the method has some similarities to other methods in that the input should not be empty and will return a message if the input is empty asking the user to enter an input. Additionally, an exit option should be included in case the user decides they no longer want to search. The method should return -1 when the user exits.

Further validation of the recipe book file will be implemented by Anna. Further validation of the new recipe input will be implemented by Aditi. Further validation of empty inputs which contain multiple spaces will be implemeted by Don. Finally, the third stretch goal will be implemented by Sarah. However, we are all in constant communication if we feel the need to change how the work is divided up. 

## Attributions

| Section | Team member(s) | Contribution (how) |
| ------- | -------------- | ------------------ |
| Recipe Class | Sarah | Implemented based on spec |
| RecipeTest Class | Sarah | Implemented based on spec |
| OnlineRecipe Class | Aditi | Implemented based on spec |
| OnlineRecipeTest Class | Aditi | Implemented based on spec |
| OfflineRecipe Class | Aditi | Implemented based on spec |
| OfflineRecipeTest Class | Aditi | Implemented based on spec |
| Ingredient Class | Anna | Implemented based on spec |
| IngredientTest Class | Anna | Implemented based on spec |
| Main Class | Sarah | Implemented based on spec and revised, Used GenAI to help with reading and writing to a csv file |
| Bad input survives | Sarah | Added to main class and tested |
| Reflection | Aditi | Drafted and added to README |
| Next steps | Anna | Drafted and added to README |
| Created repositiory | Don | Created and shared |
