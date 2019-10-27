package com.company;

import java.io.*;
import java.util.ArrayList;

public class Recipe implements Serializable{

    /**
     * Title of the Recipe
     */
    private String title;

    /**
     * Instructions on how to make the recipe
     */
    private String instructions;

    /**
     * List of Ingredient objects that make up the Recipe
     * NOTE: Made transient so that the ArrayList does not get serialized and mess up our reading from the binary file.
     */
    private transient ArrayList<Ingredient> ingredientList;


    /**
     * Constructor to initialize Recipe object
     */
    public Recipe(String title, String instructions){
        this.title = title;
        this.instructions = instructions;
        ingredientList = new ArrayList<>();
    }

    /**
     * Getter for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for instructions
     * @return instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Setter for instructions
     * @param instructions
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * Add Ingredient object to our Recipe
     * @param ing
     */
    public void addIngredient(Ingredient ing) {
        ingredientList.add(ing);
    }

    /**
     * Helper method for checking two recipes for equality
     * @param index
     * @return Ingredient object located at specific index
     */
    private Ingredient getIngredient(int index){
        return ingredientList.get(index);
    }



    /**
     * Writes calling Recipe object to a binary file for storage.
     * @param filename
     *
    public void toRecipeBox(String filename){
        ObjectOutputStream outputStream = null;
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeObject(this);
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

    */

    public static ArrayList<String> listOfRecipes(String fileName){

        ObjectInputStream inputStream = null;
        ArrayList<String> names = new ArrayList<>();

        try{
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
            //loop through file to get Recipe Objects, then store their names in the array
            while(true){
                Recipe currRecipe = (Recipe)inputStream.readObject();
                names.add(currRecipe.getTitle());
            }
        }
        catch(FileNotFoundException e){
            System.err.println("File Not Found");
            System.exit(0);
        }
        catch(ClassNotFoundException e){
            System.err.println("Invalid Class");
            System.exit(0);
        }
        catch(EOFException e){
            System.out.println("All recipes have been counted!");
            try{
                inputStream.close();
            }
            catch(IOException f){
                System.out.println("Trouble closing the file after reading.");
                System.exit(0);
            }
        }
        catch(IOException e){
            e.printStackTrace();
            System.err.println("Problem opening file");
            System.exit(0);
        }

        return names;
    }

    /*
    private static int getCount(String fileName) {

        ObjectInputStream inputStream = null;
        int count = 0;

        //Get the number of Recipes in the binary file
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
            while (true) {
                inputStream.readObject();
                count++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
            System.exit(0);
        }
        catch(ClassNotFoundException e){
            System.err.println("Invalid Class");
            System.exit(0);
        }
        catch(EOFException e){
            System.out.println("All Recipes Counted");
            try{
                inputStream.close();
            }
            catch(IOException f){
                System.out.println("Trouble closing the file after reading.");
                System.exit(0);
            }

        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Trouble processing file while reading.");
            System.exit(0);
        }
            return count;
    }

    */

    /**
     * Check for equality of two Recipe objects
     * @param other
     * @return True if the two Recipe objects contain the same ingredients. Return false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        else if(this.getClass() != other.getClass()){
            return false;
        }

        Recipe o = (Recipe)other;

        for(int i = 0; i < ingredientList.size(); i++){
            if(!this.getIngredient(i).equals(o.getIngredient(i))){
                return false;
            }
        }

        return true;
    }

    /**
     * Display all Recipe data as a String
     * @return String representation of the Recipe object
     */
    public String toString(){
        StringBuilder recipe = new StringBuilder();
        for(int i = 0; i < ingredientList.size(); i++){
            recipe.append(this.getIngredient(i).toString() + "\n");
        }
        return recipe.toString();
    }

}
