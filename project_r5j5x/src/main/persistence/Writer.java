package persistence;

import model.entry.ListOfEntries;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Wrtier class that converts ListOfEntries object into JSON object that represents ListOfEntries data
// SOURCE: this entire class is copied and modified from JsonWriter
public class Writer {
    private static final int INDENT = 4;
    private PrintWriter writer;
    private String filePath;

    // EFFECTS: constructs Writer object in order to write the save file
    //          and making the argument filePath as its file path to write the save file
    public Writer(String filePath) {
        this.filePath = filePath;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer and picks the destination to write the save file with the field filePath
    //          representing where to write the save file to
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(filePath));
    }

    // MODIFIES: this
    // EFFECTS: makes a JSON object that represents the listOfEntries and saves it to a file
    public void writeSave(ListOfEntries listOfEntries) {
        JSONObject loeJson = listOfEntries.toJson();
        saveToFile(loeJson.toString(INDENT));
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON object that represents ListOfEntries into a file
    private void saveToFile(String saveFile) {
        writer.print(saveFile);
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }
}