package model.food;

import org.json.JSONObject;

import java.util.Objects;

// Represents a singular food with the food's name and calorie
public class Food {
    private double calorie; // the food's calorie
    private String name;    // the food's name

    // REQUIRES: calorieAmount is a non-negative double
    // EFFECTS: constructs a food object where the food's name is set to foodName
    //          and the food's calorie is set to calorieAmount
    public Food(String foodName, double calorieAmount) {
        this.calorie = calorieAmount;
        this.name = foodName;
    }

    public double getCalorie() {
        return this.calorie;
    }

    public String getName() {
        return this.name;
    }

    public void setCalorie(double amount) {
        this.calorie = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: Returns the string that represents the fields of this object with the format
    //          "Food Name: name
    //           Calories: calorie"
    public String viewFood() {
        return ("Food Name: " + getName()) + "\nCalories: " + Double.toString(getCalorie());
    }

    // MODIFIES: ths
    // EFFECTS: creates and returns a JSON object that represents this Food
    public JSONObject toJson() {
        JSONObject foodJson = new JSONObject();

        foodJson.put("calorie", calorie);
        foodJson.put("name", name);

        return foodJson;
    }
}