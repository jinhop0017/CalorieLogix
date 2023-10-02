package ui;

import model.Event;
import model.EventLog;
import model.entry.Entry;
import model.entry.ListOfEntries;
import model.food.Food;
import persistence.Reader;
import persistence.Writer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the main gui of the application which is all the visual aspect of the application itself
// While this class doesn't represent all the windows that is present in the application, every single part of the
// necessary function of this application has to all first be at least called through this class
public class MainGui extends JFrame implements ActionListener {
    private ListOfEntries listOfEntries;
    private ListOfEntries listOfFilterEntries;
    private List<EntryPanel> listOfEntryPanels;
    private List<EntryPanel> tempListOfPanels;

    private static final String DESTINATION = "./data/ListOfEntries.json";
    private Writer writer;
    private Reader reader;

    private static final int EXPANDED_PANEL_WIDTH = 200;
    private static final int COLLAPSE_PANEL_WIDTH = 50;

    private static final int VIEW_BUTTON_HEIGHT = 50;
    private static final int VIEW_BUTTON_WIDTH = VIEW_BUTTON_HEIGHT;

    private ImageIcon menuIcon;
    private ImageIcon addEntryIcon;
    private ImageIcon saveEntryIcon;
    private ImageIcon loadEntryIcon;
    private ImageIcon filterEntryIcon;
    private ImageIcon resetFilterIcon;
    private ImageIcon splashScreenImage;

    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 800;

    private JPanel menuPanel;
    private JPanel menuPanelSmall;

    private JPanel entryViewPanel;
    private JScrollPane scrollPane;

    private JButton viewMenuButton;
    private JButton loadEntryButton;
    private JButton saveEntryButton;
    private JButton addEntryButton;
    private JButton filterEntryButton;
    private JButton resetFilterButton;

    private AddEntryFrame addEntryFrameWindow;
    private FilterEntryFrame filterEntryFrame;

    private JPanel filterButtonPanel;

    // EFFECTS: constructs the MainGui with all the required buttons, panels, setting up and running splashScreen,
    //          and the custom subclasses of JFrame which are used to open windows related to the function of the
    //          program
    public MainGui() {
        initIcons();
        resizeImageIcons();

        initSelf();

        setupFrames();

        setupLists();

        writer = new Writer(DESTINATION);
        reader = new Reader(DESTINATION);

        setupButtons();

        setupPanels();

        initScrollPane();

        initFilterButtonPanel();

        this.add(menuPanel, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the MainGui (which is this class, so it is self) by setting all the properties of the
    //          mainGui such as setting its size or LayOut
    //          Afterwards, it displays a splash screen that stays on for about 3 seconds and display all the elements
    //          of this app on its app screen.
    private void initSelf() {
        this.setTitle("CalorieLogix");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDescription() + " at " + event.getDate());
                }
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(APP_WIDTH, APP_HEIGHT);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        JLabel splashScreen = new JLabel(splashScreenImage);
        this.add(splashScreen);
        this.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.remove(splashScreen);
        this.setResizable(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the addEntryFrameWindow and filterEntryFrame objects
    //          Adds action listeners to appropriate buttons within these frames
    private void setupFrames() {
        this.addEntryFrameWindow = new AddEntryFrame();
        this.addEntryFrameWindow.getSendEntry().addActionListener(this);

        this.filterEntryFrame = new FilterEntryFrame();
        this.filterEntryFrame.getSendDate().addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the fields for all the lists and class the represents a list that
    //          this class uses such as listOfEntries or listOfPanels
    private void setupLists() {
        tempListOfPanels = new ArrayList<>();
        listOfEntryPanels = new ArrayList<>();
        listOfEntries = new ListOfEntries();
        listOfFilterEntries = new ListOfEntries();
    }

    // MODIFIES: this
    // EFFECTS: initializes all the panel fields used in this class entryViewPanel, menuPanel, and menuPanelSmall
    private void setupPanels() {
        entryViewPanel = new JPanel();
        initEntryViewPanel();

        menuPanel = new JPanel();
        initMenuPanel();

        menuPanelSmall = new JPanel();
        initSmallMenuPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes all the button fields used in this class and sets their icons and action listeners
    private void setupButtons() {
        loadEntryButton = new JButton("Load Entries");
        initLoadEntryButton();
        viewMenuButton = new JButton("Hide Menu");
        initViewMenuButton();
        addEntryButton = new JButton("Add Entry");
        initAddEntryButton();
        saveEntryButton = new JButton("Save Entry");
        initSaveEntryButton();
        resetFilterButton = new JButton("Reset Filter");
        initResetFilterButton();
        filterEntryButton = new JButton("Filter Entry");
        initFilterEntryButton();
    }

    // MODIFIES: this
    // EFFECTS: initializes all the image icons used in this class
    private void initIcons() {
        menuIcon = new ImageIcon("./data/MenuIcon.png");
        addEntryIcon = new ImageIcon("./data/AddEntryIcon.png");
        saveEntryIcon = new ImageIcon("./data/SaveEntryIcon.png");
        loadEntryIcon = new ImageIcon("./data/LoadEntryIcon.png");
        filterEntryIcon = new ImageIcon("./data/FilterEntryIcon.png");
        resetFilterIcon = new ImageIcon("./data/ResetFilterIcon.png");
        splashScreenImage = new ImageIcon("./data/SplashScreen.png");
    }

    // MODIFIES: this
    // EFFECTS: resizes the image icons to specified sizes so the icons and images can be an appropriate size
    private void resizeImageIcons() {
        menuIcon = resizeIcon(menuIcon, 30, 30);
        addEntryIcon = resizeIcon(addEntryIcon, 30, 30);
        saveEntryIcon = resizeIcon(saveEntryIcon, 30, 30);
        loadEntryIcon = resizeIcon(loadEntryIcon, 30, 30);
        filterEntryIcon = resizeIcon(filterEntryIcon, 30, 30);
        resetFilterIcon = resizeIcon(resetFilterIcon, 30, 30);
        splashScreenImage = resizeIcon(splashScreenImage, 784, 764);
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
    // EFFECTS: initializes the filterButtonPanel and adds filterEntryButton
    //          and resetFilterButton to the filterButtonPanel
    private void initFilterButtonPanel() {
        filterButtonPanel = new JPanel();
        filterButtonPanel.setLayout(new FlowLayout());
        filterButtonPanel.setBackground(Color.white);
        filterButtonPanel.add(filterEntryButton);
        filterButtonPanel.add(resetFilterButton);
        this.add(filterButtonPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes the menuPanel and adds the buttons for view menu, add entry, save entry, and load entry
    private void initMenuPanel() {
        menuPanel.setLayout(null);
        menuPanel.setPreferredSize(new Dimension(EXPANDED_PANEL_WIDTH, APP_HEIGHT));
        menuPanel.setBackground(Color.white);
        menuPanel.add(viewMenuButton);
        menuPanel.add(addEntryButton);
        menuPanel.add(saveEntryButton);
        menuPanel.add(loadEntryButton);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        menuPanel.setBorder(lineBorder);
    }

    // MODIFIES: this
    // EFFECTS: initializes the collapsed version of the menu panel
    private void initSmallMenuPanel() {
        menuPanel.setLayout(null);
        menuPanelSmall.setPreferredSize(new Dimension(COLLAPSE_PANEL_WIDTH, APP_HEIGHT));
        menuPanelSmall.setBackground(Color.white);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        menuPanelSmall.setBorder(lineBorder);

        menuPanelSmall.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the scrollPane for the entryViewPanel by selecting what to scroll and it's scroll speed
    private void initScrollPane() {
        scrollPane = new JScrollPane(entryViewPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
    }

    // MODIFIES: this
    // EFFECTS: initializes the entryViewPanel with a label to indicate that this is where we displasy the ListOfEntires
    private void initEntryViewPanel() {
        entryViewPanel.setBackground(Color.white);
        entryViewPanel.setLayout(new BoxLayout(entryViewPanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("List of Entries");
        entryViewPanel.add(label);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        entryViewPanel.setBorder(lineBorder);
    }

    // MODIFIES: this
    // EFFECTS: initializes the viewMenuButton with its properties
    private void initViewMenuButton() {
        viewMenuButton.setBounds(0, 0, EXPANDED_PANEL_WIDTH, VIEW_BUTTON_HEIGHT);
        viewMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        viewMenuButton.setBorderPainted(false);
        viewMenuButton.setContentAreaFilled(false);
        viewMenuButton.setIcon(menuIcon);
        viewMenuButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the loadEntryButton with its properties
    private void initLoadEntryButton() {
        loadEntryButton.setBounds(0, VIEW_BUTTON_HEIGHT * 3, EXPANDED_PANEL_WIDTH, VIEW_BUTTON_HEIGHT);
        loadEntryButton.setHorizontalAlignment(SwingConstants.LEFT);
        loadEntryButton.setBorderPainted(false);
        loadEntryButton.setContentAreaFilled(false);
        loadEntryButton.setIcon(loadEntryIcon);
        loadEntryButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the saveEntryButton with its properties
    private void initSaveEntryButton() {
        saveEntryButton.setBounds(0, VIEW_BUTTON_HEIGHT * 2, EXPANDED_PANEL_WIDTH, VIEW_BUTTON_HEIGHT);
        saveEntryButton.setHorizontalAlignment(SwingConstants.LEFT);
        saveEntryButton.setBorderPainted(false);
        saveEntryButton.setContentAreaFilled(false);
        saveEntryButton.setIcon(saveEntryIcon);
        saveEntryButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the addEntryButton with its properties
    private void initAddEntryButton() {
        addEntryButton.setBounds(0, VIEW_BUTTON_HEIGHT, EXPANDED_PANEL_WIDTH, VIEW_BUTTON_HEIGHT);
        addEntryButton.setHorizontalAlignment(SwingConstants.LEFT);
        addEntryButton.setBorderPainted(false);
        addEntryButton.setContentAreaFilled(false);
        addEntryButton.setIcon(addEntryIcon);
        addEntryButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the filterEntryButton with its properties
    private void initFilterEntryButton() {
        filterEntryButton.setBorderPainted(false);
        filterEntryButton.setContentAreaFilled(false);
        filterEntryButton.setIcon(filterEntryIcon);
        filterEntryButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the resetFilterButton with its properties
    private void initResetFilterButton() {
        resetFilterButton.setBorderPainted(false);
        resetFilterButton.setContentAreaFilled(false);
        resetFilterButton.setIcon(resetFilterIcon);
        resetFilterButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: handles various actions triggered by different buttons, such as loading, saving, adding entries,
    //          handling filter entry frame related actions, and handling entry panel related actions
    @Override
    public void actionPerformed(ActionEvent e) {
        Entry entryToRemove = null;

        if (e.getSource() == viewMenuButton) {
            toggleMenuSize();
        }

        if (e.getSource() == loadEntryButton) {
            loadEntries();
            updateEntryPanels();
        }

        if (e.getSource() == saveEntryButton) {
            saveEntries();
        }

        if (e.getSource() == addEntryButton) {
            addEntryFrameWindow = new AddEntryFrame();
            addEntryFrameWindow.setVisible(true);
            addEntryFrameWindow.getSendEntry().addActionListener(this);
        }

        if (filterEntryFrameHandler(e)) {
            return;
        }

        addEntryPanelHandler(e);

        entryPanelHandler(e);
    }

    // MODIFIES: this
    // EFFECTS: handles actions related to the adding the entry, such as adding food to an entry or the entry itself
    //          to the listOfEntries
    private void addEntryPanelHandler(ActionEvent e) {
        if (e.getSource() == addEntryFrameWindow.getSendEntry()) {
            Entry entry = addEntryFrameWindow.getEntry();

            for (Food food : addEntryFrameWindow.getListOfFood()) {
                entry.addFood(food);
            }

            listOfEntries.addEntry(entry);
            updateEntryPanels();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles actions related to the filter entry frame, such as filtering entries based on a date
    //          and updating panels accordingly, or resetting the filter
    //          returns true if the action of filtering the entry was handled, false otherwise
    private boolean filterEntryFrameHandler(ActionEvent e) {
        if (e.getSource() == resetFilterButton) {
            updateEntryPanels();
        }

        if (e.getSource() == filterEntryButton) {
            filterEntryFrame = new FilterEntryFrame();
            filterEntryFrame.setVisible(true);
            filterEntryFrame.getSendDate().addActionListener(this);
        }

        if (e.getSource() == filterEntryFrame.getSendDate()) {
            listOfFilterEntries = new ListOfEntries();

            for (Entry entry : listOfEntries.getListOfEntry()) {
                if (entry.getYear() == filterEntryFrame.getYear() && entry.getMonth() == filterEntryFrame.getMonth()) {
                    listOfFilterEntries.addEntry(entry);
                }
            }

            updateEntryPanelsFiltered();
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: handles various actions related to the entry panels, including removing entries, adding or removing
    //          food from an entry, and updating the EntryPanels accordingly
    private void entryPanelHandler(ActionEvent e) {
        Entry entryToRemove;
        for (EntryPanel entryPanel : listOfEntryPanels) {

            if (e.getSource() == entryPanel.getRemoveSelfButton()) {

                entryToRemove = entryPanel.getEntry();
                listOfEntries.getListOfEntry().remove(entryToRemove);
                updateEntryPanels();

                return;
            }

            if (e.getSource() == entryPanel.getFoodRemovedButton()) {
                updateEntryPanels();
                return;
            }

            if (e.getSource() == entryPanel.getFoodAddedButton()) {
                updateEntryPanels();
                return;
            }

            if (e.getSource() == entryPanel.getFoodViewedButton()) {
                refreshEntryPanels();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: refreshes the visibility of entry panels to have the correct display of EntryPanels
    public void refreshEntryPanels() {
        for (Component c : entryViewPanel.getComponents()) {
            if (c instanceof EntryPanel) {
                c.setVisible(false);
            }
        }

        for (Component c : entryViewPanel.getComponents()) {
            if (c instanceof EntryPanel) {
                c.setVisible(true);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the ListOfEntryPanels so that it is matching to the
    //          ListOfEntries and reloads entry panels accordingly
    private void updateEntryPanels() {
        listOfEntryPanels.clear();

        for (Entry entry : listOfEntries.getListOfEntry()) {
            listOfEntryPanels.add(new EntryPanel(entry));
        }
        reloadEntryPanels();
    }

    // MODIFIES: this
    // EFFECTS: reloads the entry panels by removing all the current EntryPanels from EntryViewPanel and adds all the
    //          EntryPanels that should be present back to the EntryViewPanel as well as adding all the required
    //          buttons of each EntryPanels to the AcitonListner
    public void reloadEntryPanels() {
        for (Component c : entryViewPanel.getComponents()) {
            if (c instanceof EntryPanel) {
                entryViewPanel.remove(c);
            }
        }

        for (EntryPanel e : listOfEntryPanels) {
            entryViewPanel.add(e);
            e.getRemoveSelfButton().addActionListener(this);
            e.getFoodRemovedButton().addActionListener(this);
            e.getFoodAddedButton().addActionListener(this);
            e.getFoodViewedButton().addActionListener(this);
        }

        entryViewPanel.repaint();
        toggleMenuSize();
        toggleMenuSize();
    }

    // MODIFIES: this
    // EFFECTS: updates the ListOfEntryPanels so that it is matching to the
    //          ListOfFilteredEntries and reloads entry panels accordingly
    private void updateEntryPanelsFiltered() {
        listOfEntryPanels.clear();

        for (Entry entry : listOfFilterEntries.getListOfEntry()) {
            listOfEntryPanels.add(new EntryPanel(entry));
        }
        reloadEntryPanelsFiltered();
    }

    // MODIFIES: this
    // EFFECTS: reloads the entry panels by removing all the current EntryPanels from EntryViewPanel and adds all the
    //          EntryPanels that should be present back to the EntryViewPanel as well as adding all the required
    //          buttons of each EntryPanels to the AcitonListner
    public void reloadEntryPanelsFiltered() {
        for (Component c : entryViewPanel.getComponents()) {
            if (c instanceof EntryPanel) {
                entryViewPanel.remove(c);
            }
        }

        for (EntryPanel e : listOfEntryPanels) {
            entryViewPanel.add(e);
            e.getRemoveSelfButton().addActionListener(this);
            e.getFoodRemovedButton().addActionListener(this);
            e.getFoodAddedButton().addActionListener(this);
        }
        entryViewPanel.repaint();
        toggleMenuSize();
        toggleMenuSize();
    }

    // MODIFIES: this
    // EFFECTS: toggles the size of the menu panel between expanded and collapsed states
    private void toggleMenuSize() {
        if (menuPanel.isVisible()) {
            menuPanel.setVisible(false);
            menuPanel.remove(viewMenuButton);
            this.remove(menuPanel);

            viewMenuButton.setText("");
            viewMenuButton.setBounds(0, 0, VIEW_BUTTON_WIDTH, VIEW_BUTTON_HEIGHT);
            menuPanelSmall.add(viewMenuButton);
            menuPanelSmall.setVisible(true);
            this.add(menuPanelSmall, BorderLayout.WEST);

        } else if (menuPanelSmall.isVisible()) {
            menuPanelSmall.setVisible(false);
            menuPanelSmall.remove(viewMenuButton);
            this.remove(menuPanelSmall);

            viewMenuButton.setText("Hide Menu");
            viewMenuButton.setBounds(0, 0, EXPANDED_PANEL_WIDTH, VIEW_BUTTON_HEIGHT);
            menuPanel.add(viewMenuButton);
            menuPanel.setVisible(true);
            this.add(menuPanel, BorderLayout.WEST);
        }
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
}