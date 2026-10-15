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

## Reflection
Overall, we made only a few changes to the basic structure of our classes and methods. The Recipe, Ingredient, OnlineRecipe, and OfflineRecipe classes were implemented according to our original design and the specifications we created for our program. In our main method, we added a system called recipe roulette, where you can make a random recipe. This was one of our stretch goals that we were able to successfully implement into our program. Our input validation also stayed the same; according to our spec, the only difference being that we added more validation checks into our roulette system. Also, in Main.java, we added a method that is not in our spec that reads a new recipe we added and checks validation for our scanner input, along with guiding the user through making a new recipe. This method was created to keep everything more organized and separate checking existing recipes from recipes you are about to create.

Our group’s Linked Chain class and Node class implementation uses a generic type so it can store different kinds of objects. It supports adding and removing entries, retrieving entries by index, checking whether an entry exists, counting entries, and converting the chain into an array. We also use numberOfEntries to keep track of the chain’s size. We removed addToEnd, which is usually a part of Linked Chain class implementations, because the order does not matter. When it comes to our unknowns, our biggest one was how to implement and read linked chains that contained linked chains, but we were able to figure it out.

Currently, we have not encountered any major errors while running our program. The features we implemented appear to be working as expected, and we are not aware of any major unfinished features at this time. Further testing could help us identify edge cases involving invalid user input, etc along with testing whether Recipe Roulette is checking each case properly.

## Next Steps

Currently, we have almost completed our MVP, the only thing left is a few validation fixes. Firstly, we need to prevent ingredient amounts from being negative within our ingredient constructor. It should throw an IllegalArgumentException if it is negative. Next, when adding recipes our program currently does not ignore case input when checking if the recipe book already has the recipe the user wants to add, so that bad input needs to be fixed. Finally, in the main class, our bad input handling for when a user inputs a space only checks for a single space and not multiple so we need to trim the input to confirm it is empty. So, these are just the little bugs and improvements that we need to fix before moving onto our stretch goals.

Our current stretch goals include: the ability for the user to compile a list of ingredients across multiple recipes, and a roulette function that returns a random recipe based on an ingredient the user inputs. We are committing to both stretch goals because at our current stage of the project and prototype it seems achievable. 

For our first stretch goal we will need to add a compileIngredients method that takes a list of recipes chosen by the user and this method could be tested by adding two recipes with the same ingredients and the expected result should be twice as much of each ingredient. Another case would be entirely different ingredients and the list returned should contain all the ingredients. This method would fall under the main class which Sarah has been working on. Another method would be an outputGroceryList that would print the compiled grocery list by calling the toString method. This would be tested by printing a grocery list with ingredients and one without to handle bad input. 
The Ingredient class which would be delegated to Anna, also needs a method for this stretch goal. It would need an addAmount method that would add the amount of the certain ingredient that is necessary. So this would be tested by adding for example 2.0 to an ingredient with 1.0 and the expected output should be 3.0. The bad input would be tested by adding a negative amount which should throw an IllegalArgumentException.

For our second stretch goal, recipe roulette, we would need to add a few methods to the main class again. Firstly, there would need to be a recipeRoulette method that takes the input of an ingredient entered by the user and iterates through every recipe in the book and returns a random recipe that contains the inputted ingredient. This would be tested by searching for an ingredient that multiple recipes have and the output should return only one of the recipes. This should also test when only one recipe had the inputted ingredient. The bad cases that need to be tested would be when either none of the recipes contain the ingredient or when the recipe book is empty. Also, bad ingredient input where the user inputs in all capitals. The second method would be readIngredientInput which would read the user input and handle bad cases. So this would be tested through the user inputs of an ingredient and that ingredient should be returned. The bad input would be an empty string or only spaces. These methods could be worked on by Don and Aditi, as Anna and Sarah are working on the first stretch goal. However, we are all in constant communication if we feel the need to change how the work is divided up. 


## Attribution

| Section | Team member(s) | Contribution (how) |
| ------- | -------------- | ------------------ |
| Working application slice | All | Implemented classes and created java docs |
| The LinkedChain is reused | Sarah and Don | Implemented recipe book linked chain with online and offline recipes and ingredients list that only took in ingredients |
| Used through its public API | Sarah and Don | Implemented LinkedChains |
| JUnit tests that run | Don | Wrote and tested |
| Bad input survives | Sarah | Wrote and tested |
| Reflection | Aditi | Drafted and added to README |
| Next steps | Anna or Aditi | Drafted and added to README |
