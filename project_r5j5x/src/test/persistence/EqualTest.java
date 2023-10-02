package persistence;

import model.entry.Entry;
import model.entry.ListOfEntries;
import model.food.Food;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EqualTest {

    // SOURCE: inspired by JsonSerializationDemo
    public void checkLoeEqual(ListOfEntries loe1, ListOfEntries loe2) {
        outerLoop: while (true) {
            List<Entry> listOfEntry1 = loe1.getListOfEntry();
            List<Entry> listOfEntry2 = loe2.getListOfEntry();

            if (listOfEntry1.size() != listOfEntry2.size()) {
                assertEquals(listOfEntry1.size(), listOfEntry2.size());
                break;
            } else {
                for (int i = 0; i < listOfEntry1.size(); i++) {
                    Entry entry1 = listOfEntry1.get(i);
                    Entry entry2 = listOfEntry2.get(i);

                    assertEquals(entry1.viewDate(), entry2.viewDate());
                    assertEquals(entry1.viewWeight(), entry2.viewWeight());

                    List<Food> listOfFood1 = entry1.getListOfFood();
                    List<Food> listOfFood2 = entry2.getListOfFood();

                    if (listOfFood1.size() != listOfFood2.size()) {
                        assertEquals(listOfFood1.size(), listOfEntry2.size());
                        break outerLoop;
                    } else {
                        for (int i1 = 0; i1 < listOfFood1.size(); i1++) {
                            Food food1 = listOfFood1.get(i1);
                            Food food2 = listOfFood2.get(i1);

                            assertEquals(food1.viewFood(), food2.viewFood());
                        }
                    }
                }
            }
            break;
        }
    }
}
