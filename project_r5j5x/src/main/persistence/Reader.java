package persistence;

import model.entry.Entry;
import model.entry.ListOfEntries;
import model.food.Food;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Reader class that reads a JSON object that represents the ListOfEntries data
// and creates it into actual ListOfEntries object
public class Reader {
    private String filePath;

    // EFFECTS: constructs a reader object with filePath arugment as the file path
    //          that the reader is reading the data from
    // SOURCE: Copied and modified from JsonSerializationDemo
    public Reader(String filePath) {
        this.filePath = filePath;
    }

    // EFFECTS: retrieves the JSON object that represents ListOfEntries data and
    //          creates and returns ListOfEntries object that matches the JSON data
    public ListOfEntries read() throws IOException {
        String loeData = readFile(filePath);
        JSONObject loeJson = new JSONObject(loeData);
        return loadLoe(loeJson);
    }

    // EFFECTS: reads source file as string and returns it
    // SOURCE: Copied from JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
            return contentBuilder.toString();
        }

    }

    // EFFECTS: Takes a given JSON object loeJson from the argument and creates and returns
    //          ListOfEntries object that matches the JSON data
    private ListOfEntries loadLoe(JSONObject loeJson) {
        ListOfEntries listOfEntries = new ListOfEntries();
        JSONArray loeJsonArray = new JSONArray();

        loeJsonArray = loeJson.getJSONArray("entries");
        for (Object entryJson : loeJsonArray) {
            jsonToEntry(listOfEntries, (JSONObject) entryJson);
        }

        return listOfEntries;
    }

    // MODIFIES: entryJson
    // EFFECTS: Takes a given JSON object entryJson from the argument and creates
    //          an Entry object that matches the data of JSON object entryJson and adds it
    //          to the given ListOfEntries from the argument Entries
    private void jsonToEntry(ListOfEntries listOfEntries, JSONObject entryJson) {
        int entryYear = entryJson.getInt("year");
        int entryMonth = entryJson.getInt("month");
        int entryDay = entryJson.getInt("day");

        double entryWeight = entryJson.getDouble("weight");
        double entryWeightGoal = entryJson.getDouble("weightGoal");

        Entry entry = new Entry(entryYear, entryMonth, entryDay, entryWeight, entryWeightGoal);

        JSONArray lofJsonArray = entryJson.getJSONArray("listOfFood");
        for (Object foodJson : lofJsonArray) {
            jsonToFood((JSONObject) foodJson, entry);
        }

        listOfEntries.addEntry(entry);
    }

    // MODIFIES: entry
    // EFFECTS: Takes a given JSON object foodJson from the argument and creates
    //          a Food object that matches the data of JSON object foodJson and adds the Food
    //          to the given Entry from the argument entry
    private void jsonToFood(JSONObject foodJson, Entry entry) {
        double foodCalorie = foodJson.getDouble("calorie");
        String foodName = foodJson.getString("name");

        Food food = new Food(foodName, foodCalorie);

        entry.addFood(food);
    }
}
