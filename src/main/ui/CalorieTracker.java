package ui;

import model.entry.Entry;
import model.entry.ListOfEntries;
import model.food.Food;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Calorie tracker application
public class CalorieTracker {
    private ListOfEntries listOfEntries;

    private Scanner input;
    private Scanner inputDouble;
    private Scanner inputInt;

    private int inputYear;
    private int inputMonth;
    private int inputDay;

    private double inputCalorie;

    private double inputWeight;
    private double inputWeightGoal;

    private static final String DESTINATION = "./data/ListOfEntries.json";
    private Writer writer;
    private Reader reader;

    // EFFECTS: when this object is created, run the app
    public CalorieTracker() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: creates a bunch of scanner object for the future inputs and runs the main menu
    private void runApp() {
        listOfEntries = new ListOfEntries();
        input = new Scanner(System.in);
        inputDouble = new Scanner(System.in);
        inputInt = new Scanner(System.in);

        writer = new Writer(DESTINATION);
        reader = new Reader(DESTINATION);

        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: runs the main menu with functioning code and interface
    //          where user's input will execute a corresponding method available to the main menu
    private void mainMenu() {
        String choice;
        boolean loop = true;

        while (loop) {
            showMainMenu();

            choice = input.next();

            loop = runMainMenu(choice, loop);
        }
    }

    // MODIFIES: this
    // EFFECTS: A functioning code for the main menu where user's input will
    //          execute a corresponding method available to the main menu
    private boolean runMainMenu(String choice, boolean loop) {
        if (choice.equals("1")) {
            viewEntry();
        } else if (choice.equals("2")) {
            addEntry();
        } else if (choice.equals("3")) {
            editEntry();
        } else if (choice.equals("4")) {
            removeEntry();
        } else if (choice.equals("5")) {
            viewAllEntries();
        } else if (choice.equals("6")) {
            saveEntries();
        } else if (choice.equals("7")) {
            loadEntries();
        } else if (choice.equals("8")) {
            System.out.println("exited");
            loop = false;
        }
        return loop;
    }

    // EFFECTS: saves the ListOfEntries by converting it into a JSON object
    //          that represents the data of ListOfEntries
    // SOURCE: copied from JsonSerializationDemo
    private void saveEntries() {
        try {
            writer.open();
            writer.writeSave(listOfEntries);
            writer.close();
            System.out.println("Saved the entries" + " to " + DESTINATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + DESTINATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: reads the JSON object that represents the data of ListOfEntries
    //          and creates a ListOfEntries object based on the data of JSON object
    // SOURCE: copied from JsonSerializationDemo
    private void loadEntries() {
        try {
            listOfEntries = reader.read();
            System.out.println("Loaded Entries" + " from " + DESTINATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + DESTINATION);
        }
    }

    // EFFECTS: Displays the main menu interface to the console
    private static void showMainMenu() {
        System.out.println("Select option:");
        System.out.println("\t1 - View an Entry");
        System.out.println("\t2 - Add Entry");
        System.out.println("\t3 - Edit Entry Elements");
        System.out.println("\t4 - Remove Entry Elements");
        System.out.println("\t5 - View all Entries");
        System.out.println("\t6 - Save Entries");
        System.out.println("\t7 - Load Entries");
        System.out.println("\t8 - Exit");
    }

    // EFFECTS: displays the entry viewing menu interface to the console
    private static void showViewMenu() {
        System.out.println("What do you wish to view?\n" + "1 - Entry Date\n" + "2 - Weight Information\n"
                + "3 - Food log\n" + "4 - The entire entry\n" + "5 - Exit");
    }

    // MODIFIES: this
    // EFFECTS: searches for the entry using matching dates from the arugment and displays variety of view options
    //          Returns to the main menu if entry with matching date not found
    private void viewEntry() {
        String choice;

        Entry targetEntry;

        setDateElements();
        targetEntry = listOfEntries.getEntry(inputYear, inputMonth, inputDay); // Searches for the entry with given date

        if (targetEntry != null) {
            runViewMenu(targetEntry);
        } else {
            System.out.println("Unable to find entry");
        }
    }

    // EFFECTS: Runs the code needed for the viewing menu to function with variety of options to
    //          choose from for viewing elements of the entry such as viewing the entry's weight or date
    private void runViewMenu(Entry targetEntry) {
        boolean loop = true;
        String choice;
        while (loop) {
            showViewMenu();
            choice = input.next();
            if (choice.equals("1")) {
                viewEntryDate(targetEntry);
            } else if (choice.equals("2")) {
                viewEntryWeight(targetEntry);
            } else if (choice.equals("3")) {
                viewEntryFood(targetEntry);
            } else if (choice.equals("4")) {
                viewEntireEntry(targetEntry);
            } else if (choice.equals("5")) {
                System.out.println("Exited to main menu");
                loop = false;
            } else {
                System.out.println("Pick a valid option from 1-5");
            }
        }
    }

    // EFFECTS: prints all the date information of the entry being "year/month/date"
    public void viewEntryDate(Entry targetEntry) {
        System.out.println(targetEntry.viewDate());
    }

    // EFFECTS: prints all the weight related information of the entry
    public void viewEntryWeight(Entry targetEntry) {
        System.out.println(targetEntry.viewWeight());
    }

    // EFFECTS: prints all the food related information of the entry
    public void viewEntryFood(Entry targetEntry) {
        for (Food food : targetEntry.getListOfFood()) {
            System.out.println(food.viewFood());
        }
        System.out.println("Total calories consumed: " + Double.toString(targetEntry.getTotalCalorie()));
    }

    // EFFECTS: prints all the information of an entry that are important to the user
    public void viewEntireEntry(Entry targetEntry) {
        viewEntryDate(targetEntry);
        viewEntryWeight(targetEntry);
        viewEntryFood(targetEntry);
    }

    // EFFECTS: Prints all the information of all the entries that are important to the user
    private void viewAllEntries() {
        for (Entry entry : listOfEntries.getListOfEntry()) {
            viewEntireEntry(entry);
            System.out.println("----------------------");
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs and adds the entry to the listOfEntries
    //          with the argument's year, month, day, weight, and weightGoal
    private void addEntry() {
        Entry targetEntry;

        setDateElements();
        setInputWeight();
        setInputWeightGoal();

        targetEntry = new Entry(inputYear, inputMonth, inputDay, inputWeight, inputWeightGoal);

        addFood(targetEntry);

        listOfEntries.addEntry(targetEntry);
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: adds a Food with custom name and calorie that the user is going to pick to a given Entry
    //               and asks to keep adding Food afterwards or stop and
    //               proceeds with the user's answer
    private void addFood(Entry targetEntry) {
        boolean loop = true;
        boolean loop2 = true;
        String foodName;
        String choice;

        while (loop) {
            System.out.println("Enter the name of the food you ate today\nDO NOT PUT SPACE, use UNDERSCORE instead");
            foodName = input.next();
            setInputCalorie();
            targetEntry.addFood(foodName, inputCalorie);
            loop2 = true;
            while (loop2) {
                System.out.println("Would you like to add more food?\n1 - Yes\n2 - No"); // Option to keep adding Food
                choice = input.next();
                if (choice.equals("1")) {
                    loop2 = false;
                } else if (choice.equals("2")) {
                    loop = false;
                    loop2 = false;
                } else {
                    System.out.println("Pick a valid option between 1 and 2");
                }
            }
        }
    }

    // EFFECTS: prints the editing menu interface to the console
    private static void showEditMenu() {
        System.out.println("What do you wish to edit?\n" + "1 - Entry Date\n"
                + "2 - Weight Information\n" + "3 - Food log\n" + "4 - Exit");
    }

    // MODIFIES: this
    // EFFECTS: Searches an entry with matching date that the user is going to input and open an edit menu to edit the
    //          selected entry
    //          Returns to main menu if no matching entry found
    private void editEntry() {
        String choice;

        Entry targetEntry;

        setDateElements();
        targetEntry = listOfEntries.getEntry(inputYear, inputMonth, inputDay);

        if (targetEntry != null) {
            runEditMenu(targetEntry);
        } else {
            System.out.println("Unable to find entry");
        }
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: runs the code needed for the entry edit menu to function and displays the edit menu to give the user
    //          variety of options to choose from to edit elements of the given Entry
    private void runEditMenu(Entry targetEntry) {
        boolean loop = true;
        String choice;

        while (loop) {
            showEditMenu();
            choice = input.next();
            if (choice.equals("1")) {
                editEntryDate(targetEntry);
            } else if (choice.equals("2")) {
                editEntryWeight(targetEntry);
            } else if (choice.equals("3")) {
                editWhichFood(targetEntry);
            } else if (choice.equals("4")) {
                System.out.println("Exited to main menu");
                loop = false;
            } else {
                System.out.println("Pick a valid option from 1-4");
            }
        }
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: changes the given Entry's date that consists of year, month, and day
    private void editEntryDate(Entry targetEntry) {
        setDateElements();

        targetEntry.setYear(inputYear);
        targetEntry.setMonth(inputMonth);
        targetEntry.setDay(inputDay);
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: changes all the weight related information on given Entry which are current weight and weight goal
    private void editEntryWeight(Entry targetEntry) {
        setInputWeight();
        targetEntry.setWeight(inputWeight);

        setInputWeightGoal();
        targetEntry.setWeightGoal(inputWeightGoal);
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: displays the options for editing the Entry's Food information which is to remove or change them and
    //          runs the corresponding method based on user's input
    private void editWhichFood(Entry targetEntry) {
        boolean loop = true;
        String choice;

        while (loop) {
            System.out.println("Would you like to add new food or change current food?\n"
                    + "1 - Add New Food\n2 - Change Current Food");
            choice = input.next();
            if (choice.equals("1")) {
                addFood(targetEntry);
                loop = false;
            } else if (choice.equals("2")) {
                editEntryFood(targetEntry);
                loop = false;
            } else {
                System.out.println("Invalid option, please choose between 1 or 2");
            }
        }
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: looks for the Food from Entry that matches the user's input and calls the method that changes the
    //          Food's name and calorie to corresponding user's input
    //          afterward, it asks to change more entries or stop and
    //          proceeds with the user's answer
    private void editEntryFood(Entry targetEntry) {
        boolean loop = true;
        boolean loop2;
        String choice;

        while (loop) {
            System.out.println("Enter the name of the food you like to change"
                    + "\nDO NOT PUT SPACE, use UNDERSCORE instead");

            editFood(targetEntry);

            loop2 = true;
            while (loop2) {
                System.out.println("Would you like to edit more food entries?\n1 - Yes\n2 - No");
                choice = input.next();
                if (choice.equals("1")) {
                    loop2 = false;
                } else if (choice.equals("2")) {
                    loop = false;
                    loop2 = false;
                } else {
                    System.out.println("Pick a valid option between 1 and 2");
                }
            }
        }
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: looks for the Food from Entry that matches the user's input and changes the Food's
    //          name and calorie to corresponding user's input
    //          if the matching Food is not found, ask if the user wants to keep editing food or stop and
    //          proceeds with the user's answer
    private void editFood(Entry targetEntry) {
        Food targetFood;
        boolean loop;
        String foodName;

        loop = true;
        while (loop) {
            foodName = input.next();
            targetFood = targetEntry.getFood(foodName);

            if (targetFood != null) {
                System.out.println("What would you like to set the new food name to be?");
                foodName = input.next();
                setInputCalorieEdit();
                targetFood.setCalorie(inputCalorie);
                targetFood.setName(foodName);
                loop = false;
            } else {
                System.out.println("Food with matching name not found");
                loop = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: searches for Entry that matches with user's input of year, month, day and passes the Entry to
    //          a method that removes the found Entry or a Food from the Entry
    //          Goes back to main menu when no matching Entry found
    private void removeEntry() {
        String choice;

        Entry targetEntry;

        boolean loop = true;

        setDateElements();
        targetEntry = listOfEntries.getEntry(inputYear, inputMonth, inputDay);

        if (targetEntry != null) {
            removeWhichEntry(targetEntry);
        } else {
            System.out.println("Unable to find entry");
        }
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: asks if the user wants to remove the given Entry or food from Given Entry
    //          if picked remove the given Entry, it removes the given Entry
    //          if picked remove food from given Entry, it calls the method to remove food from given Entry
    private void removeWhichEntry(Entry targetEntry) {
        boolean loop = true;
        String choice;

        while (loop) {
            System.out.println("Would you like to remove the entry or remove food from entry?\n"
                    + "1 - Remove Entry\n2 - Remove Food from Entry");
            choice = input.next();
            if (choice.equals("1")) {
                listOfEntries.removeEntry(inputYear, inputMonth, inputDay);
                loop = false;
            } else if (choice.equals("2")) {
                removeFood(targetEntry);
                loop = false;
            } else {
                System.out.println("Invalid option, please choose between 1 or 2");
            }
        }
    }

    // MODIFIES: this, targetEntry
    // EFFECTS: searches for the Food by matching the Food's name with user's given input and removes
    //          the Food from given Entry
    //          if unable to find Food, run the method that asks the user to retry or stop removing Food and
    //          proceed with the user's answer
    private void removeFood(Entry targetEntry) {
        boolean loop = true;

        String foodName;
        Food targetFood;

        while (loop) {
            System.out.println("Enter the name of the food you want to remove");
            foodName = input.next();

            targetFood = targetEntry.getFood(foodName);
            if (targetFood != null) {
                loop = deleteFood(targetEntry, loop, foodName);
            } else {
                loop = searchFailed(loop);
            }
        }
    }

    // MODIFIES: loop
    // EFFECTS: returns true if the user wants to continue to search for the matching Food
    //          returns false if the user wants to stop searching for the matching food
    private boolean searchFailed(boolean loop) {
        boolean loop2;
        String choice;
        loop2 = true;
        while (loop2) {
            System.out.println("Food with matching name not found\nWould you like to retry or exit?"
                    + "\n1 - Retry\n2 - Exit");
            choice = input.next();

            if (choice.equals("1")) {
                loop2 = false;
            } else if (choice.equals("2")) {
                loop = false;
                loop2 = false;
            } else {
                System.out.println("Pick a valid option between 1 and 2");
            }
        }
        return loop;
    }

    // MODIFIES: this, targetEntry, loop
    // EFFECTS: asks if the user wants to remove more food from given targetEntry and removes the Food that
    //          matches with the given foodName which will always be the case and returns true
    //          if the user picks no, it still removes the food and this method returns false
    private boolean deleteFood(Entry targetEntry, boolean loop, String foodName) {
        String choice;
        boolean loop2;
        loop2 = true;
        while (loop2) {
            System.out.println("Would you like to remove more food?\n1 - Yes\n2 - No");
            choice = input.next();
            if (choice.equals("1")) {
                loop2 = false;
            } else if (choice.equals("2")) {
                loop = false;
                loop2 = false;
            } else {
                System.out.println("Pick a valid option between 1 and 2");
            }
        }
        targetEntry.removeFood(foodName);
        return loop;
    }

    // MODIFIES: this
    // EFFECTS: calls the method that sets this field's year, month, day to the user's input for variety of uses
    private void setDateElements() {
        setInputYear();
        setInputMonth();
        setInputDay();
    }

    // MODIFIES: this
    // EFFECTS: sets this field's year to the user's input for future usage,
    //          if the user's input is invalid for setting the year,
    //          then ask for the user's input again until it's input becomes valid for setting the year
    private void setInputYear() {
        boolean loop = true;

        while (loop) {
            System.out.println("Select the year of the entry");
            inputYear = inputInt.nextInt();

            if (inputYear < 0) {
                System.out.println("Year must be greater than or equal to 0\n");
            } else {
                loop = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets this field's month to the user's input for future usage,
    //          if the user's input is invalid for setting the month,
    //          then ask for the user's input again until it's input becomes valid for setting the month
    private void setInputMonth() {
        boolean loop = true;

        while (loop) {
            System.out.println("Select the month of the entry");
            inputMonth = inputInt.nextInt();

            if (inputMonth < 1 || inputMonth > 12) {
                System.out.println("Month must be between 1-12 (inclusive)\n");
            } else {
                loop = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets this field's day to the user's input for future usage,
    //          if the user's input is invalid for setting the day,
    //          then ask for the user's input again until it's input becomes valid for setting the day
    private void setInputDay() {
        boolean loop = true;

        while (loop) {
            System.out.println("Select the day of the entry");
            inputDay = inputInt.nextInt();

            if (inputDay < 1 || inputDay > 31) {
                System.out.println("Day must be between 1-31 (inclusive)\n");
            } else {
                loop = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets this field's calorie to the user's input for future usage,
    //          if the user's input is invalid for setting the calorie,
    //          then ask for the user's input again until it's input becomes valid for setting the calorie
    private void setInputCalorie() {
        boolean loop = true;

        while (loop) {
            System.out.println("Input the food's calorie");
            inputCalorie = inputDouble.nextDouble();

            if (inputCalorie < 0) {
                System.out.println("Calorie must be greater than 0\n");
            } else {
                loop = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets this field's calorie to the user's input for future usage,
    //          if the user's input is invalid for setting the calorie,
    //          then ask for the user's input again until it's input becomes valid for setting the calorie
    //          the difference between this method and setInputCalorie() is that they print slightly different
    //          statements
    private void setInputCalorieEdit() {
        boolean loop = true;

        while (loop) {
            System.out.println("Input the food's new calorie");
            inputCalorie = inputDouble.nextDouble();

            if (inputCalorie < 0) {
                System.out.println("Calorie must be greater than 0\n");
            } else {
                loop = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets this field's weight to the user's input for future usage,
    //          if the user's input is invalid for setting the weight,
    //          then ask for the user's input again until it's input becomes valid for setting the weight
    private void setInputWeight() {
        boolean loop = true;

        while (loop) {
            System.out.println("Input your current weight in any units");
            inputWeight = inputDouble.nextDouble();

            if (inputWeight < 0) {
                System.out.println("Weight must be greater than 0\n");
            } else {
                loop = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets this field's weightGoal to the user's input for future usage,
    //          if the user's input is invalid for setting the weightGoal,
    //          then ask for the user's input again until it's input becomes valid for setting the weightGoal
    private void setInputWeightGoal() {
        boolean loop = true;

        while (loop) {
            System.out.println("Input your Weight goal in any units");
            inputWeightGoal = inputDouble.nextDouble();

            if (inputWeightGoal < 0) {
                System.out.println("Weight goal must be greater than 0\n");
            } else {
                loop = false;
            }
        }
    }
}