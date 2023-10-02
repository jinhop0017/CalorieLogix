# CalorieLogix

## Calorie / Weight Tracker
- This project is the **Calorie/Weight Tracker app**.
What this application does is that the user is able to put 
calories that the user consumed throughout the day, and it will give 
the user the total calorie that they consumed that day. 
The user can also put their weight so they can see how their weight has changed
over time. Moreover, the user can view their previous entries so they can keep
on track of previous weights and calories consumed. This app also has goals
that the user wants to achieve such as daily calorie consumption goal or weight
goal that they are trying to get to.

- As for who will want to use this app, it's great for people who want to
reach or maintain a certain weight, as well as those who are curious about their
calorie consumption. Basically, this app is for those that care, curious, or
want to do something in relation to their calorie consumption information.

- This project is an interest to me because ever since the end of my first year in
uni, I have gained a lot of weight and I wanted to track calories so that I know
the daily calorie consumption to make me lose weight, and maintain it so I can
actually lose weight. And while thinking of a project idea, this problem
or goal that I am trying to achieve popped up into my mind and 
I want to develop this app not just to put on my personal profile, 
but to also use it myself.

## User stories
- As a user, I want to add the calories that I consumped throughout the day to a list of calories consumed.
- As a user, I want to view the lists of calories consumed and edit it.
- As a user, I want to see the total calories I consumed today.
- As a user, I want to add a weight goal that I am trying to achieve.
- As a user, I want to add my current weight to the list of weights so I know my progress of weight overtime.
- As a user, I want to be able to save my list of entries created so I can keep track and use the entries over time
- As a user, I want to be able to load the list of entries created so I can view and build on entries I have created
- As a user, I want to be able to filter the entries by it's year and month.

### - How to add Entry to ListOfEntries (first required action)
1. Click the "Add Entry" button on the left side of the app. 
2. Enter the required information for the entry which are year, month, date, current weight, and weight goal of the 
Entry
at the appropriate text fields provided. 
3. This part is optional but if you want to add the foods you ate to the entry, you
click the "add food" button.
4. Type the food's name and calorie at the appropriate text fields and click "add food"
button to add the Food to the ListOfFood. You can also add more if you want. 
5. Afterwards, if you are satisfied with your entry, then click sumbit button to add the Entry that you have just 
created into the ListOfEntries which the Entries that you have created can be viewed on the center part of the screen
on panels that represent the Entries you have added so far.

### - How to filter Entries by it's year and month (second required action)
1. Click the "Filter Entry" button.
2. Type the year and month of the entries that you want to view on the appropriate text fields
3. If you are happy with your year and month, click "sort entries" button. 
4. Afterwards, you can see that the center of the app only displays the panels that represent the
entries that satisfies the date conditions that you have just typed. 
5. If you want to reset the filter, you click the "Reset Filter" button to reset the filter
and view all the entries that you have added.

### - Where to find the visual component of the project
1. Go to the "data" folder and you will be able to find all the visual components. 
2. As for where to see those visual components, start the application and you will see
the splash screen.
3. Wait for about 3 seconds and you will see the main menu of the app which you will
locate the majority of the visual component which consists of mostly icons.
4. Look at the top of the app and you will see the icon for "Filter Entry" and 
"Reset Filter"
5. If you look at the left side of the app, you will see 4 icons that corresponds with
"Hide Menu", "Add Entry", "Save Entry", and "Load Entries".
6. Once you have Entries present in ListOfEntries either by loading the Entries or adding
the Entry, panels will appear that represent the Entries you have added with the final remaining sets of the
visual component which consists of only icons.
7. If you look at the top of the panel, you can see the remove icon.
8. If you see the bottom of the panel, you will see 3 icons that corresponds with 3 the buttons
"Add Food to Entry", "Remove Food from Entry", and "View All Food Ate".
9. If you click the "View All Food Ate" button, the icon will change from an arrow pointing sideways to an arrow
pointing downwards which is the final visual component that wasn't seen.
10. Just as something extra, if you click the "Hide Menu" button on the left side of the main menu, you will be able to
hide all the visual icons that was present on the left of the main menu besdies the "Hide Menu" icon. 

### - How to save the state of the application to file
1. Press the save button on the left side of the main menu. Done.
2. If you are unable to see the save button, go to the left of the main menu and click the icon that has 3 horizontal
black bars on it. This will display all the buttons that are on the left side of the main menu which includes the save
button.
3. Press the save button on the left side of the main menu. Done.

### - How to load the state of the application from a file
1. Press the load button on the left side of the main menu. Done.
2. If you are unable to see the load button, go to the left of the main menu and click the icon that has 3 horizontal
   black bars on it. This will display all the buttons that are on the left side of the main menu which includes the
load button.
3. Press the load button on the left side of the main menu. Done.

# Example of log
- Added Food with name: test, and calorie: 123.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Entry for 2004/9/4 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: ice cream, and calorie: 1231.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: chocolate, and calorie: 121.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Entry for 2004/9/5 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: 1, and calorie: 2.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Entry for 2004/9/12 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: 1, and calorie: 1.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: 1, and calorie: 1.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: 1, and calorie: 1.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: 1, and calorie: 1.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: 1, and calorie: 1.0 at Wed Aug 09 18:40:29 PDT 2023
- Added Entry for 2005/9/3 at Wed Aug 09 18:40:29 PDT 2023
- Added Food with name: test1, and calorie: 2.0 at Wed Aug 09 18:40:35 PDT 2023
- Added Food with name: 1, and calorie: 1.0 at Wed Aug 09 18:40:47 PDT 2023
- Added Entry for 2005/9/12 at Wed Aug 09 18:40:47 PDT 2023
- Added Entry for 2005/9/3 at Wed Aug 09 18:41:02 PDT 2023
- Added Entry for 2005/9/12 at Wed Aug 09 18:41:02 PDT 2023
- Added Entry for 2004/9/4 at Wed Aug 09 18:41:12 PDT 2023
- Added Entry for 2004/9/5 at Wed Aug 09 18:41:12 PDT 2023
- Added Entry for 2004/9/12 at Wed Aug 09 18:41:12 PDT 2023

# What I would have improved
Overall, I was quite surprised how nice my UML diagram looked. I thought it would have been a lot messier and lots of
room for refactoring considering that the classes in my program seemed kind of complex due to things like
list of list of x but overall, it was nice and neat. However, if I had to refactor something, that would be to reduce
the association for the AddEntryFrame class. The reason is that when looking at the diagram, the association arrows
were pointing to "AddFoodFrame", "Entry", and "Food". In here, I would refactor the code so that it removes the
association with the "Food" class. The reason for this is that both "AddFoodFrame" class and "Entry" class has 
associations or associations of associations with the "Food" class. Thus, another association arrow to the "Food" class
would be redundant. Especially because the association of "AddFoodFrame" exists to add Food to the Entry, so having
another association with Food is unnecessary as the "Food" association was there to also add Food to the Entry.

Another refactoring I would have done is to create an abstract class is to somehow add an abstract class or an interface
for "AddEntryFrame", "RemoveFoodFrame", and "AddFoodFrame" to extend. This is because all the 3 classes function pretty
similar, where all of them even has a shared method of "textFieldSetup" that creates a side label for the textField with
given textField, string, and the y-position of the textField. It sets up the x-position and the gap between the 
textField and it's label automatically. All of them also share additional similarity like how they apply changes to the
MainGui or the way they are initialized. Therefore, I would have added an abstract class or an interface that all those
3 classes would have extended for better organization, design, or reduce redundancy such as not having to create the
same method for all 3 of the classes.