package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * NAME FOR PROGRAM: The Handmade Home Handbook
 */
public class Main {

    public static void main(String[] args) {

        final String FILE = "Recipe.dat";
        ArrayList<Recipe> recipeList = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Welcome to The Handmade Home Handbook! \nWhat would you like to do?");
        System.out.println("1. Add a Recipe\n2. Display Current Recipes");

        int choice = keyboard.nextInt();
        keyboard.nextLine(); //absorb the leftover \n from the nextInt()

        switch(choice){
            case 1:

                boolean addMore = true;
                boolean moreRecipes = true;

                while(moreRecipes){
                    System.out.print("Enter the title of your recipe: ");
                    String title = keyboard.nextLine();

                    System.out.print("\nEnter the instructions for making this recipe: ");
                    String instructions = keyboard.nextLine();

                    Recipe newRecipe = new Recipe(title, instructions);

                    System.out.println("Now let's add some ingredients!");
                    while(addMore){
                        System.out.print("Enter the name of the ingredient: ");
                        String name = keyboard.nextLine();

                        System.out.print("\nEnter the measurement for the ingredient: ");
                        String measurement = keyboard.nextLine();

                        System.out.print("\nEnter the way this ingredient should be prepared: ");
                        String prep = keyboard.nextLine();

                        newRecipe.addIngredient(new Ingredient(name,measurement,prep));

                        System.out.println("Would you like to add another ingredient? [Y/N]");
                        char choice2 = keyboard.nextLine().charAt(0);

                        switch(choice2){
                            case 'Y':
                            case 'y':
                                addMore = true;
                                break;
                            case 'N':
                            case 'n':
                                addMore = false;
                                break;
                            default:
                                System.out.println("Invalid Entry. No more ingredients will be added.");
                                addMore = false;
                                break;
                        }
                        recipeList.add(newRecipe);
                    }
                    addMore = true;
                    System.out.println("Would you like to add another recipe? [Y/N]");
                    char choice3 = keyboard.nextLine().charAt(0);

                    switch(choice3){
                        case 'Y':
                        case 'y':
                            moreRecipes = true;
                            break;
                        case 'N':
                        case 'n':
                            moreRecipes = false;
                            break;
                        default:
                            System.out.println("Invalid Entry. No more recipes will be added.");
                            moreRecipes = false;
                            break;
                    }
                }

                toRecipeBox(FILE, recipeList);
                System.out.println("You're recipes have been added to the binary file!");
                break;

            case 2:
                System.out.println("Reading the names of the recipes you have in the binary file currently!");
                ArrayList<String> list = Recipe.listOfRecipes(FILE);
                for(String currName: list){
                    System.out.println("Current Recipe: " + currName);
                }
                break;
        }

        System.out.println("Thank you for using The Handmade Home Handbook!");
    }

    /**
     * Writes calling Recipe object to a binary file for storage.
     * @param filename recipeList
     */
    public static void toRecipeBox(String filename, ArrayList<Recipe> recipeList){
        AppendObjectOutputStream outputStream = null;
        try{
            outputStream = new AppendObjectOutputStream(new FileOutputStream(filename));
            for(int i = 0; i < recipeList.size(); i++){
                outputStream.writeObject(recipeList.get(i));
            }
            outputStream.close();
        }
        catch(FileNotFoundException e){
            System.err.println("File Not Found");
            System.exit(0);
        }
        catch(IOException e){
            System.err.println("Problem opening file");
            System.exit(0);
        }
    }
}
