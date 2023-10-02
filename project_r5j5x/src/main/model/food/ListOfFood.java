package model.food;

import model.Event;
import model.EventLog;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents list of food that the user consumed throughout the whole day
public class ListOfFood {
    private List<Food> listOfFood; // the list of food

    public ListOfFood() {
        listOfFood = new ArrayList<>();
    }

    public List<Food> getListOfFood() {
        return listOfFood;
    }

    // MODIFIES: this
    // EFFECTS: adds the food from the argument to the list of food
    public void addFood(Food food) {
        listOfFood.add(food);
        EventLog.getInstance().logEvent(new Event("Added Food with name: " + food.getName()
                + ", and calorie: " + food.getCalorie()));
    }

    // MODIFIES: this
    // EFFECTS: removes the first food from the listOfFood that matches with the argument's food name
    //          returns true if it was able to remove the food
    //          returns false if food with given name wasn't found
    public boolean removeFood(String targetName) {
        for (Food food : listOfFood) {
            if (Objects.equals(food.getName(), targetName)) {
                listOfFood.remove(food);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes the food from a picked index in listOfFood
    //          returns true if it was able to remove the food
    //          returns false if the index went out of the listOfFood's range
    public boolean removeFood(int i) {
        if (0 <= i && i < listOfFood.size()) {
            listOfFood.remove(i);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: finds the first food from listOfFood that has the matching name as
    //          the argument targetName and changes the food's name to the
    //          argument newName
    //          returns true if it was able to change the food's name
    //          returns false if the index went out of the listOfFood's range
    public boolean changeFoodName(String targetName, String newName) {
        for (Food food : listOfFood) {
            if (Objects.equals(food.getName(), targetName)) {
                food.setName(newName);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: finds the food with given index from listOfFood and changes the food's name
    //          to given newName
    //          returns true if it was able to change the food's name
    //          returns false if the index went out of the listOfFood's range
    public boolean changeFoodName(int i, String newName) {
        if (0 <= i && i < listOfFood.size()) {
            listOfFood.get(i).setName(newName);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: newCalorie is a non-negative double
    // MODIFIES: this
    // EFFECTS: finds the first food from listOfFood that has the matching name as
    //          the argument targetName and changes the food's calorie to the
    //          given newCalorie
    //          returns true if it was able to change the food's calorie
    //          returns false if food with given name wasn't found
    public boolean changeFoodCalorie(String targetName, double newCalorie) {
        for (Food food : listOfFood) {
            if (Objects.equals(food.getName(), targetName)) {
                food.setCalorie(newCalorie);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: newCalorie is a non-negative double
    // MODIFIES: this
    // EFFECTS: finds the food with given index from listOfFood and changes the food's calorie to
    //          given calorie
    //          returns true if it was able to change the food's calorie
    //          returns false if the index went out of the listOfFood's range
    public boolean changeFoodCalorie(int i, double newCalorie) {
        if (0 <= i && i < listOfFood.size()) {
            listOfFood.get(i).setCalorie(newCalorie);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns the first food from listOfFood that has the matching name as
    //          the argument targetName
    //          returns null if food with given name wasn't found
    public Food getFood(String targetName) {
        for (Food food : listOfFood) {
            if (Objects.equals(food.getName(), targetName)) {
                return food;
            }
        }
        return null;
    }

    // EFFECTS: finds the food with given index from listOfFood and returns the found food
    //          returns null if the index went out of the listOfFood's range
    public Food getFood(int i) {
        if (0 <= i && i < listOfFood.size()) {
            return listOfFood.get(i);
        } else {
            return null;
        }
    }

    // EFFECTS: returns the total combined calories of the foods in listOfFood
    public double getTotalCalorie() {
        double totalCalorie = 0;

        for (Food food : listOfFood) {
            totalCalorie += food.getCalorie();
        }

        return totalCalorie;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a JSON array representing this ListOfFood and puts all JSON objects that
    //          represent the current Foods in ListOfFood into the JSON array
    public JSONArray toJson() {
        JSONArray lofJson = new JSONArray();

        for (Food food : listOfFood) {
            lofJson.put(food.toJson());
        }

        return lofJson;
    }
}