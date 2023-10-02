package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the window that pops up when choosing to filter Entries
public class FilterEntryFrame extends JFrame implements ActionListener {
    public static final int LABEL_X_POS = 10;
    public static final int LABEL_GAP = 100;
    public static final int LABEL_Y_POS = 5;
    public static final int VERTICAL_GAP = 30;
    public static final int APP_WIDTH = 400;
    public static final int APP_HEIGHT = 400;
    private JButton sumbitButton;
    private JButton sendDate;

    private int year;
    private int month;

    private JTextField yearField;
    private JTextField monthField;

    // EFFECTS: constructs the FilterEntryFrame object, initializes and sets
    //          up the GUI components of the FilterEntryFrame
    public FilterEntryFrame() {

        yearField = new JTextField();
        monthField = new JTextField();

        initTextFields();

        sumbitButton = new JButton("sort entries");
        sumbitButton.setBounds(0, APP_HEIGHT - 150, 100, 100);
        sumbitButton.addActionListener(this);

        sendDate = new JButton();

        this.setTitle("Sort Entry");
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
        textFieldSetup(yearField, LABEL_Y_POS + VERTICAL_GAP, "Year");
        textFieldSetup(monthField, LABEL_Y_POS + VERTICAL_GAP * 2, "Month");
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

    public JButton getSumbitButton() {
        return sumbitButton;
    }

    public JButton getSendDate() {
        return sendDate;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    // MODIFIES: this
    // EFFECTS: handles the action performed when the submit button is clicked, sets the year and month, and triggers
    //          the sendDate button's click action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sumbitButton) {
            year = Integer.valueOf(yearField.getText());
            month = Integer.valueOf(monthField.getText());
            sendDate.doClick();
        }
    }
}