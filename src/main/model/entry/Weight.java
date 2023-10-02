package model.entry;

// Represents the current weight of the user and their weight goal in pounds
// However, it doesn't have to be in pounds as the user can decide
// whatever unit they want their weights to be
public class Weight {
    private double weight;
    private double weightGoal;

    // REQUIRES: weight and weightGoal needs to be a double greater than and not equal to 0
    // EFFECTS: constructs a weight object with its weight set to argument's weight and
    //          its weightGoal set to argument's weightGoal
    public Weight(double weight, double weightGoal) {
        this.weight = weight;
        this.weightGoal = weightGoal;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getWeightGoal() {
        return this.weightGoal;
    }

    public void setWeight(double amount) {
        this.weight = amount;
    }

    public void setWeightGoal(double amount) {
        this.weightGoal = amount;
    }

    // EFFECTS: Returns the string that represents the fields of this object with the format
    //          "Current Weight: weight
    //           Current Weight Goal: weightGoal"
    public String viewWeight() {
        return ("Current Weight: " + Double.toString(getWeight())
                + "\nWeight Goal: " + Double.toString(getWeightGoal()));
    }
}