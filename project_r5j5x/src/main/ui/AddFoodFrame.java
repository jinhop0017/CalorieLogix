package ui;

import model.food.Food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the window that pops up when choosing to add Food to entries
public class AddFoodFrame extends JFrame implements ActionListener {
    public static final int LABEL_X_POS = 10;
    public static final int LABEL_GAP = 100;
    public static final int LABEL_Y_POS = 5;
    public static final int VERTICAL_GAP = 30;
    public static final int APP_WIDTH = 400;
    public static final int APP_HEIGHT = 400;
    private JButton sumbitButton;
    private JButton sendFood;

    private JTextField foodNameField;
    private JTextField foodCalorieField;

    private Food food;

    // EFFECTS: constructs the AddFoodFrame object and sets
    //          up the GUI components of the AddFoodFrame
    public AddFoodFrame() {

        foodNameField = new JTextField();
        foodCalorieField = new JTextField();

        initTextFields();

        sumbitButton = new JButton("add food");
        sumbitButton.setBounds(0, APP_HEIGHT - 150, 100, 100);
        sumbitButton.addActionListener(this);

        sendFood = new JButton();

        this.setTitle("Add Food");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        this.setResizable(false);
        this.setLayout(null);

        this.add(sumbitButton);
        setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: sets up a textFields of this class with the given properties and label at the specified position
    private void initTextFields() {
        textFieldSetup(foodNameField, LABEL_Y_POS + VERTICAL_GAP * 3, "Food Name");

        textFieldSetup(foodCalorieField, LABEL_Y_POS + VERTICAL_GAP * 4, "Food Calorie");
    }

    // REQUIRES: textField != null, labelYPos >= 0, label != null
    // MODIFIES: textField, this
    // EFFECTS: Sets up a text field with the given textField, label, and specified Y position
    private void textFieldSetup(JTextField textField, int labelYPos, String label) {
        textField.setBounds(LABEL_X_POS + LABEL_GAP, labelYPos, 100, 25);

        JLabel yearLabel = new JLabel(label);
        yearLabel.setBounds(LABEL_X_POS, labelYPos, 200, 25);

        this.add(yearLabel);
        this.add(textField);
    }

    public JButton getSendFood() {
        return sendFood;
    }

    public Double getWeight() {
        return Double.valueOf(foodNameField.getText());
    }

    public Food getFood() {
        return food;
    }

    // MODIFIES: this
    // EFFECTS: handles the action performed when the submit button is clicked, creates a Food object with its fields,
    //          and triggers the sendFood button's click action and gets dispose of itself so it closes after sending
    //          clicking submit
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sumbitButton) {
            food = new Food(foodNameField.getText(), Double.valueOf(foodCalorieField.getText()));
            sendFood.doClick();
            dispose();
        }
    }
}