package ui;

import model.entry.Entry;
import model.food.Food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the Panel that represents the Entry itself with additional buttons to modify the entry such as removing
// the Entry or adding more Food to the Entry
public class EntryPanel extends JPanel implements ActionListener {
    private Entry entry;
    private JButton viewFood;
    private JButton removeSelfButton;
    private JButton removeFoodButton;
    private JButton addFoodButton;
    private JButton foodRemovedButton;
    private JButton foodAddedButton;
    private JButton foodViewedButton;

    private JLabel dateLabel;
    private JLabel weightLabel;
    private JLabel weightGoalLabel;
    private JLabel totalCalorieLabel;

    private JPanel foodPanels;

    private AddFoodFrame addFoodFrame;

    private RemoveFoodFrame removeFoodFrame;

    private ImageIcon deleteEntryIcon;
    private ImageIcon addFoodIcon;
    private ImageIcon removeFoodIcon;
    private ImageIcon collapsedListIcon;
    private ImageIcon expandedListIcon;

    // REQUIRES: entry != null
    // EFFECTS: constructs the EntryPanel object with given Entry and initializes and sets
    //          up the GUI components of the EntryPanel
    public EntryPanel(Entry entry) {
        initIcons();
        resizeImageIcons();

        foodPanels = new JPanel();
        foodPanels.setLayout(new BoxLayout(foodPanels, BoxLayout.Y_AXIS));

        this.addFoodFrame = new AddFoodFrame();

        this.entry = entry;

        this.foodViewedButton = new JButton();
        this.removeSelfButton = new JButton("Remove Entry");
        initRemoveSelfButton();
        this.addFoodButton = new JButton("Add Food to Entry");
        initAddFoodButton();
        this.removeFoodButton = new JButton("Remove Food from Entry");
        initRemoveFoodButton();
        this.viewFood = new JButton("View All Food Ate");
        initViewFoodButton();

        labelSetup();

        removeFoodFrame = new RemoveFoodFrame();
        removeFoodFrame.getSendFoodName().addActionListener(this);

        foodRemovedButton = new JButton();
        foodAddedButton = new JButton();

        initFoodPanel();

        createEntryPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes all the labels of this class
    private void labelSetup() {
        this.dateLabel = new JLabel(this.entry.viewDate());
        this.weightLabel = new JLabel("Current Weight: " + Double.toString(this.entry.getWeight()));
        this.weightGoalLabel = new JLabel("Weight Goal: " + Double.toString(this.entry.getWeightGoal()));
        this.totalCalorieLabel = new JLabel("Total calories consumed: " + this.entry.getTotalCalorie());
    }

    // MODIFIES: this
    // EFFECTS: initializes the viewFood button with icon, alignment, and action listener
    private void initViewFoodButton() {
        viewFood.setHorizontalAlignment(SwingConstants.LEFT);
        viewFood.setBorderPainted(false);
        viewFood.setContentAreaFilled(false);
        viewFood.setIcon(collapsedListIcon);
        viewFood.addActionListener(this);
        viewFood.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    }

    // MODIFIES: this
    // EFFECTS: initializes the removeSelfButton button with icon, alignment, and action listener
    private void initRemoveSelfButton() {
        removeSelfButton.setHorizontalAlignment(SwingConstants.LEFT);
        removeSelfButton.setBorderPainted(false);
        removeSelfButton.setContentAreaFilled(false);
        removeSelfButton.setIcon(deleteEntryIcon);
        removeSelfButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeSelfButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the removeFoodButton button with icon, alignment, and action listener
    private void initRemoveFoodButton() {
        removeFoodButton.setHorizontalAlignment(SwingConstants.LEFT);
        removeFoodButton.setBorderPainted(false);
        removeFoodButton.setContentAreaFilled(false);
        removeFoodButton.setIcon(removeFoodIcon);
        removeFoodButton.addActionListener(this);
        removeFoodButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    }

    // MODIFIES: this
    // EFFECTS: initializes the AddFoodButton button with icon, alignment, and action listener
    private void initAddFoodButton() {
        addFoodButton.setHorizontalAlignment(SwingConstants.LEFT);
        addFoodButton.setBorderPainted(false);
        addFoodButton.setContentAreaFilled(false);
        addFoodButton.setIcon(addFoodIcon);
        addFoodButton.addActionListener(this);
        addFoodButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    }

    // MODIFIES: this
    // EFFECTS: initializes all the ImageIcon of this class
    private void initIcons() {
        deleteEntryIcon = new ImageIcon("./data/DeleteEntryIcon.png");
        addFoodIcon = new ImageIcon("./data/AddFoodIcon.png");
        removeFoodIcon = new ImageIcon("./data/RemoveFoodIcon.png");
        collapsedListIcon = new ImageIcon("./data/CollapsedListIcon.png");
        expandedListIcon = new ImageIcon("./data/ExpandedListIcon.png");
    }

    // MODIFIES: this
    // EFFECTS: resizes the image icons to specified sizes so the icons and images can be an appropriate size
    private void resizeImageIcons() {
        deleteEntryIcon = resizeIcon(deleteEntryIcon, 30, 30);
        addFoodIcon = resizeIcon(addFoodIcon, 20, 20);
        removeFoodIcon = resizeIcon(removeFoodIcon, 20, 20);
        collapsedListIcon = resizeIcon(collapsedListIcon, 20, 20);
        expandedListIcon = resizeIcon(expandedListIcon, 20, 20);
    }

    public JButton getFoodViewedButton() {
        return foodViewedButton;
    }

    public Entry getEntry() {
        return entry;
    }

    // MODIFIES: this
    // EFFECTS: initializes the foodPanels by removing all the FoodPanel in foodPanels and adding all the FoodPanels
    //          in foodPanels that represents all the food from the entry
    public void initFoodPanel() {
        for (Component component : foodPanels.getComponents()) {
            if (component instanceof FoodPanel) {
                foodPanels.remove(component);
            }
        }

        for (Food food : entry.getListOfFood()) {
            foodPanels.add(new FoodPanel(food));
        }

        foodPanels.setVisible(false);
    }

    // REQUIRES: imageIcon cannot be null, width > 0, height > 0
    // MODIFIES: this
    // EFFECTS: resizes the given image icon to the specified width and height given in the
    //          parameter and returns the resized icon
    private ImageIcon resizeIcon(ImageIcon imageIcon, int width, int height) {
        Image menuIconImage = imageIcon.getImage();
        Image resizedMenuIcon = menuIconImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(resizedMenuIcon);
        return imageIcon;
    }

    // MODIFIES: this
    // EFFECTS: sets up the layout and components of the EntryPanel
    public void createEntryPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        this.add(removeSelfButton);

        this.add(dateLabel);
        this.add(weightLabel);
        this.add(weightGoalLabel);
        this.add(totalCalorieLabel);

        this.add(addFoodButton);
        this.add(removeFoodButton);
        this.add(viewFood);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        this.add(foodPanels);
    }

    public JButton getRemoveSelfButton() {
        return removeSelfButton;
    }

    public JButton getFoodRemovedButton() {
        return foodRemovedButton;
    }

    public JButton getFoodAddedButton() {
        return foodAddedButton;
    }

    // MODIFIES: this
    // EFFECTS: handles various action events, such as toggling food visibility, removing food, and adding food
    @Override
    public void actionPerformed(ActionEvent e) {
        viewFoodToggle(e);

        removeFood(e);

        if (e.getSource() == removeFoodFrame.getSendFoodName()) {
            for (Food food : entry.getListOfFood()) {
                if (food.getName().equals(removeFoodFrame.getFoodName())) {
                    entry.getListOfFood().remove(food);
                    initFoodPanel();
                    foodRemovedButton.doClick();
                    return;
                }
            }
        }

        if (e.getSource() == addFoodButton) {
            addFoodFrame = new AddFoodFrame();
            addFoodFrame.setVisible(true);
            addFoodFrame.getSendFood().addActionListener(this);
        }

        if (e.getSource() == addFoodFrame.getSendFood()) {
            entry.addFood(addFoodFrame.getFood());
            initFoodPanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the removal of food from the entry
    private void removeFood(ActionEvent e) {
        if (e.getSource() == removeFoodButton) {
            removeFoodFrame = new RemoveFoodFrame();
            removeFoodFrame.setVisible(true);
            foodPanels.setVisible(true);
            removeFoodFrame.getSendFoodName().addActionListener(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles the visibility of food panels and updates the viewFood button icon to match the
    //          collapsed/expanded state of the food panels
    private void viewFoodToggle(ActionEvent e) {
        if (e.getSource() == viewFood) {
            if (foodPanels.isVisible()) {
                viewFood.setIcon(collapsedListIcon);
                foodPanels.setVisible(false);
            } else if (foodPanels.isVisible() == false) {
                viewFood.setIcon(expandedListIcon);
                foodPanels.setVisible(true);
            }
            foodViewedButton.doClick();
        }
    }
}