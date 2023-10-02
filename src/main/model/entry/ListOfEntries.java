package model.entry;

import model.Event;
import model.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents list of entries to put all the entries written by user
public class ListOfEntries {
    private List<Entry> listOfEntry;

    // EFFECTS: constructs a new empty ListOfEntries object
    public ListOfEntries() {
        listOfEntry = new ArrayList<>();
    }

    public List<Entry> getListOfEntry() {
        return listOfEntry;
    }

    // EFFECTS: adds the argument entry to the listOfEntry
    public void addEntry(Entry entry) {
        listOfEntry.add(entry);
        EventLog.getInstance().logEvent(new Event("Added Entry for " + entry.viewDate()));
    }

    // REQUIRES: year must be an integer greater or equal to 0
    //           month must be an integer between 1-12 (inclusive)
    //           day must be an integer between 1-31 (inclusive)
    // EFFECTS: returns an entry that has the matching date as the argument
    //          year, month, day
    //          returns null if it wasn't able to find an entry that matches
    //          the date
    public Entry getEntry(int year, int month, int day) {

        for (Entry entry : listOfEntry) {
            if (entry.getYear() == year && entry.getMonth() == month && entry.getDay() == day) {
                return entry;
            }
        }
        return null;
    }

    // REQUIRES: year must be an integer greater or equal to 0
    //           month must be an integer between 1-12 (inclusive)
    //           day must be an integer between 1-31 (inclusive)
    // EFFECTS: removes an entry that has the matching date as the argument
    //          year, month, day and returns true
    //          returns false if it wasn't able to find an entry that matches
    //          the date
    public boolean removeEntry(int year, int month, int day) {
        Entry tempEntry = getEntry(year, month, day);

        if (tempEntry != null) {
            listOfEntry.remove(tempEntry);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: year must be an integer greater or equal to 0
    //           month must be an integer between 1-12 (inclusive)
    //           day must be an integer between 1-31 (inclusive)
    //           endDay must be an integer between 1-31 (inclusive) and has to be
    //           greater or equal to startDay
    // EFFECTS: gets the total combined calories from entries between startDay to endDay (inclusive)
    public double getTotalCalorie(int year, int month, int startDay, int endDay) {
        double totalCalorie = 0;

        for (int i = startDay; i < (endDay + 1); i++) {
            Entry targetEntry = getEntry(year, month, i);
            if (null != targetEntry) {
                totalCalorie += targetEntry.getTotalCalorie();
            }
        }

        return totalCalorie;
    }


    // MODIFIES: this
    // EFFECTS: creates and returns JSON object that represents this ListOfEntries
    //          and puts JSON array into the JSON object where JSON array stores
    //          all the JSON object that represents all current entries in ListOfEntries
    // SOURCE: copied and modified from JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject loeJson = new JSONObject();
        loeJson.put("entries", entriesToJson());
        return loeJson;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a JSON array to put all
    //          JSON objects that represents current Entries from this ListOfEntries
    // SOURCE: copied and modified from JsonSerializationDemo
    public JSONArray entriesToJson() {
        JSONArray entryJson = new JSONArray();

        for (Entry entry : listOfEntry) {
            entryJson.put(entry.toJson());
        }

        return entryJson;
    }
}