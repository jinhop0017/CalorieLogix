package ui;

import model.Event;
import model.EventLog;
import model.entry.Entry;
import model.food.Food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents the window that pops up when choosing to add Entry to ListOfEntries
public class AddEntryFrame extends JFrame implements ActionListener {
    public static final int LABEL_X_POS = 10;
    public static final int LABEL_GAP = 100;
    public static final int LABEL_Y_POS = 5;
    public static final int VERTICAL_GAP = 30;
    public static final int APP_WIDTH = 400;
    public static final int APP_HEIGHT = 400;
    private JButton submitButton;
    private JButton sendEntry;
    private JButton addFoodButton;

    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextField weightField;
    private JTextField weightGoalField;

    private Entry entry;

    private AddFoodFrame addFoodFrame;

    private List<Food> listOfFood;

    // EFFECTS: constructs the AddEntryFrame object and sets
    //          up the GUI components of the AddEntryFrame
    public AddEntryFrame() {
        this.addFoodFrame = new AddFoodFrame();
        this.addFoodFrame.getSendFood().addActionListener(this);

        listOfFood = new ArrayList<>();

        setupTextField();

        initTextFields();

        submitButton = new JButton("submit");
        submitButton.setBounds(0, APP_HEIGHT - 150, 100, 100);
        submitButton.addActionListener(this);

        addFoodButton = new JButton("add food");
        addFoodButton.setBounds(0, LABEL_Y_POS + VERTICAL_GAP * 5, 100, 25);
        addFoodButton.addActionListener(this);

        sendEntry = new JButton();

        this.setTitle("Add Entry");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        this.setResizable(false);
        this.setLayout(null);

        this.add(addFoodButton);
        this.add(submitButton);
        setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes all the JTextField field of this class
    private void setupTextField() {
        yearField = new JTextField();
        monthField = new JTextField();
        dayField = new JTextField();
        weightField = new JTextField();
        weightGoalField = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: sets up a textFields of this class with the given properties and label at the specified position
    private void initTextFields() {
        textFieldSetup(yearField, LABEL_Y_POS, "Year");

        textFieldSetup(monthField, LABEL_Y_POS + VERTICAL_GAP, "Month");

        textFieldSetup(dayField, LABEL_Y_POS + VERTICAL_GAP * 2, "Day");

        textFieldSetup(weightField, LABEL_Y_POS + VERTICAL_GAP * 3, "Current Weight");

        textFieldSetup(weightGoalField, LABEL_Y_POS + VERTICAL_GAP * 4, "Weight Goal");
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

    public JButton getSendEntry() {
        return sendEntry;
    }

    public Entry getEntry() {
        return entry;
    }

    public int getYear() {
        return Integer.valueOf(yearField.getText());
    }

    public int getMonth() {
        return Integer.valueOf(monthField.getText());
    }

    public int getDay() {
        return Integer.valueOf(dayField.getText());
    }

    public Double getWeight() {
        return Double.valueOf(weightField.getText());
    }

    public Double getWeightGoal() {
        return Double.valueOf(weightGoalField.getText());
    }

    public List<Food> getListOfFood() {
        return listOfFood;
    }

    // MODIFIES: this
    // EFFECTS: handles various action events, including creating an entry, adding food,
    //          and creating the addFoodFrame
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton) {
            entry = new Entry(getYear(), getMonth(), getDay(), getWeight(), getWeightGoal());
            sendEntry.doClick();
            addFoodFrame.dispose();
            dispose();
        }

        if (e.getSource() == addFoodFrame.getSendFood()) {
            this.listOfFood.add(addFoodFrame.getFood());
        }

        if (e.getSource() == addFoodButton) {
            addFoodFrame = new AddFoodFrame();
            addFoodFrame.setVisible(true);
            addFoodFrame.getSendFood().addActionListener(this);
        }
    }
}