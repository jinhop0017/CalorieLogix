package model.entry;

import model.Event;
import model.EventLog;
import model.food.Food;
import model.food.ListOfFood;
import org.json.JSONObject;

import java.util.List;

// Represents an entry for a single day that includes the date entry was created, list of
// food that the user consumed that single day, and the user's current weight and their weight goal
public class Entry {
    private Date date;
    private Weight weight;
    private ListOfFood listOfFood;

    // REQUIRES: weight and weightGoal needs to be a double greater than and not equal to 0
    //           month must be an integer between 1-12 (inclusive)
    //           day must be an integer between 1-31 (inclusive)
    // EFFECTS: constructs an Entry object with
    //          the date that the Entry is for set as date created with argument year, month, day
    //          and the user's current weight set as argument weight
    //          and the user's weightGoal set as argument weightGoal
    public Entry(int year, int month, int day, double weight, double weightGoal) {
        this.date = new Date(year, month, day);
        this.weight = new Weight(weight, weightGoal);
        listOfFood = new ListOfFood();
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonth();
    }

    public int getDay() {
        return date.getDay();
    }

    public Date getDate() {
        return date;
    }

    // EFFECTS: returns a string that represents the date of this object with the formating of
    //          year/month/day
    public String viewDate() {
        return date.viewDate();
    }

    public void setYear(int year) {
        date.setYear(year);
    }

    public void setMonth(int month) {
        date.setMonth(month);
    }

    public void setDay(int day) {
        date.setDay(day);
    }

    public double getWeight() {
        return weight.getWeight();
    }

    public double getWeightGoal() {
        return weight.getWeightGoal();
    }

    public Weight getObjWeight() {
        return this.weight;
    }

    // EFFECTS: Returns the string that represents the fields of this object with the format
    //          "Current Weight: weight
    //           Current Weight Goal: weightGoal"
    public String viewWeight() {
        return weight.viewWeight();
    }

    public void setWeight(double weight) {
        this.weight.setWeight(weight);
    }

    public void setWeightGoal(double weightGoal) {
        this.weight.setWeightGoal(weightGoal);
    }

    // REQUIRES: calorie must be a double that is greater than and not equal to 0
    // MODIFIES: this
    // EFFECTS: adds food that the user ate for the day with argument foodName representing
    //          the name of the food and
    //          argument calorie representing the calorie of the food
    public void addFood(String foodName, double calorie) {
        listOfFood.addFood(new Food(foodName, calorie));
    }

    // MODIFIES: this
    // EFFECTS: adds given Food to listOfFood
    public void addFood(Food food) {
        listOfFood.addFood(food);
    }

    // MODIFIES: this
    // EFFECTS: removes the first food from the listOfFood that matches with the argument's food name
    //          returns true if it was able to remove the food
    //          returns false if food with given name wasn't found
    public boolean removeFood(String targetName) {
        return listOfFood.removeFood(targetName);
    }

    // MODIFIES: this
    // EFFECTS: removes the food from a picked index in listOfFood
    //          returns true if it was able to remove the food
    //          returns false if the index went out of the listOfFood's range
    public boolean removeFood(int i) {
        return listOfFood.removeFood(i);
    }

    // MODIFIES: this
    // EFFECTS: finds the first food from listOfFood that has the matching name as
    //          the argument targetName and changes the food's name to the
    //          argument newName
    //          returns true if it was able to change the food's name
    //          returns false if the index went out of the listOfFood's range
    public boolean changeFoodName(String targetName, String newName) {
        return listOfFood.changeFoodName(targetName, newName);
    }

    // MODIFIES: this
    // EFFECTS: finds the food with given index from listOfFood and changes the food's name
    //          to given newName
    //          returns true if it was able to change the food's name
    //          returns false if the index went out of the listOfFood's range
    public boolean changeFoodName(int i, String newName) {
        return listOfFood.changeFoodName(i, newName);
    }

    // REQUIRES: newCalorie is a non-negative double
    // MODIFIES: this
    // EFFECTS: finds the first food from listOfFood that has the matching name as
    //          the argument targetName and changes the food's calorie to the
    //          given newCalorie
    //          returns true if it was able to change the food's calorie
    //          returns false if food with given name wasn't found
    public boolean changeFoodCalorie(String targetName, double newCalorie) {
        return listOfFood.changeFoodCalorie(targetName, newCalorie);
    }

    // REQUIRES: newCalorie is a non-negative double
    // MODIFIES: this
    // EFFECTS: finds the food with given index from listOfFood and changes the food's calorie to
    //          given calorie
    //          returns true if it was able to change the food's calorie
    //          returns false if the index went out of the listOfFood's range
    public boolean changeFoodCalorie(int i, double newCalorie) {
        return listOfFood.changeFoodCalorie(i, newCalorie);
    }

    // EFFECTS: returns the first food from listOfFood that has the matching name as
    //          the argument targetName
    //          returns null if food with given name wasn't found
    public Food getFood(String targetName) {
        return listOfFood.getFood(targetName);
    }

    // EFFECTS: finds the food with given index from listOfFood and returns the found food
    //          returns null if the index went out of the listOfFood's range
    public Food getFood(int i) {
        return listOfFood.getFood(i);
    }

    public ListOfFood getObjLof() {
        return listOfFood;
    }

    public List<Food> getListOfFood() {
        return listOfFood.getListOfFood();
    }

    // EFFECTS: returns the total combined calories of the foods in listOfFood
    public double getTotalCalorie() {
        return listOfFood.getTotalCalorie();
    }


    // MODIFIES: this
    // EFFECTS: creates and returns a JSON object that represents this Entry
    public JSONObject toJson() {
        JSONObject entryJson = new JSONObject();

        entryJson.put("year", getYear());
        entryJson.put("month", getMonth());
        entryJson.put("day", getDay());

        entryJson.put("weight", getWeight());
        entryJson.put("weightGoal", getWeightGoal());

        entryJson.put("listOfFood", listOfFood.toJson());

        return entryJson;
    }
}