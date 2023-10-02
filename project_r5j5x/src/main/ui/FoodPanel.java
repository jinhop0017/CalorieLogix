package ui;

import model.food.Food;

import javax.swing.*;

// Represents the FoodPanel that has the information of the food
public class FoodPanel extends JPanel {

    // REQUIRES: food != null
    // EFFECTS: initialzes the food class with the matching labels as the information of the give food
    public FoodPanel(Food food) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Food name: " + food.getName()));
        this.add(new JLabel("Food calories: " + food.getCalorie()));
        this.add(new JLabel("-----------------------"));
    }
}