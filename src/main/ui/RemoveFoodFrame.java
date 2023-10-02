package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the window that pops up when choosing to remove what food from the entry that removedFood button was
// clicked on
public class RemoveFoodFrame extends JFrame implements ActionListener {
    public static final int LABEL_X_POS = 10;
    public static final int LABEL_GAP = 100;
    public static final int LABEL_Y_POS = 5;
    public static final int VERTICAL_GAP = 30;
    public static final int APP_WIDTH = 400;
    public static final int APP_HEIGHT = 400;
    private JButton sumbitButton;
    private JButton sendFoodName;

    private String foodName;

    private JTextField foodNameField;

    // EFFECTS: constructs the RemoveFoodFrame object, initializes and sets up the GUI components of the RemoveFoodFrame
    public RemoveFoodFrame() {

        foodNameField = new JTextField();

        initTextFields();

        sumbitButton = new JButton("remove food");
        sumbitButton.setBounds(0, APP_HEIGHT - 150, 100, 100);
        sumbitButton.addActionListener(this);

        sendFoodName = new JButton();

        this.setTitle("Remove Food");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        this.setResizable(false);
        this.setLayout(null);

        this.add(sumbitButton);
        setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the foodNameField text field
    private void initTextFields() {
        textFieldSetup(foodNameField, LABEL_Y_POS + VERTICAL_GAP * 3, "Food Name");
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

    public JButton getSendFoodName() {
        return sendFoodName;
    }

    public String getFoodName() {
        return foodName;
    }

    public Double getWeight() {
        return Double.valueOf(foodNameField.getText());
    }

    // MODIFIES: this
    // EFFECTS: handles the action performed when the submit button is clicked, sets the foodName and triggers the
    //          sendFoodName button's click action and disposes of this frame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sumbitButton) {
            foodName = foodNameField.getText();
            sendFoodName.doClick();
            dispose();
        }
    }
}