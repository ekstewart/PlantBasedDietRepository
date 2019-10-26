package com.company;


/**
 * TO DO:
 * Conversions between units: Cups, Tbsp, tsp, Oz, g
 *
 */
public class Ingredient {


    /**
     * Name of the Ingredient to be used in a recipe
     */
    private String name;

    /**
     * How much of the Ingredient is needed. Ex) 1/4 Cup
     */
    private String measurement;

    /**
     * How the Ingredient will be prepared. Ex) Diced, Chopped, Sauteed etc.
     */
    private String preparation;


    /**
     * Full Constructor to initialize Ingredient Object
     */
    public Ingredient(String name, String measurement, String preparation) {
        this.name = name;
        this.measurement = measurement;
        this.preparation = preparation;
    }


    /**
     * Helper method that is meant to combine the measurements of an ingredient from other recipes into one big measurement (possible grocery list)
     * private Ingredient combineIngredient(Ingredient other);
     */


    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for measurement
     * @return measurement
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     * Getter for preparation
     * @return preparation
     */
    public String getPreparation() {
        return preparation;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for measurement
     * @param measurement
     */
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    /**
     * Setter for preparation
     * @param preparation
     */
    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }


    /**
     * Checks for equality of two Ingredient objects
     * @param other
     * @return True if name, measurement, and preparation are the equal. Returns false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        else if(this.getClass() != other.getClass()){
            return false;
        }

        Ingredient ing = (Ingredient)other;

        return this.name.equals(ing.name) && this.measurement.equals(ing.measurement) && this.preparation.equals(ing.preparation);

    }

    /**
     *
     * @return String representation of the Ingredient object
     */
    public String toString(){
        return "Name: " + name + "\nMeasurement: " + measurement + "\nPreparation: " + preparation;
    }


}
