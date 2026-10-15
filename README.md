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