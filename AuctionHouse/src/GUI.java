package AuctionHouse.src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private DefaultListModel<String> inventoryModel;
    private JList<String> inventoryList;

    private JButton moreInfoButton;

    public GUI(String title) {
        super(title);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Initialize list model and JList
        inventoryModel = new DefaultListModel<>();
        inventoryList = new JList<>(inventoryModel);
        JScrollPane listScrollPane = new JScrollPane(inventoryList);

        // Add components to the frame
        add(new JLabel("Inventory List:"), BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);

        // Add More Info button
        moreInfoButton = new JButton("More Info");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(moreInfoButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    // Method to populate the list with items
    public void setListContent(ArrayList<Items> items) {
        inventoryModel.clear();
        for (Items item : items) {
            String type = item.getClass().getSimpleName(); // Get the class name (e.g., Coins, Furniture)
            String displayText = String.format("Type: %s | ID: %d | Price: %.2f€ | Owner: %s",
                    type, item.get_id(), item.get_startingprice(), item.get_ownername());
            inventoryModel.addElement(displayText);
        }
    }

    public static void main(String[] args) {
        // Create and populate ItemsList
        ItemsList itemsList = loadItemsList();

        // Create and configure the main frame
        GUI mainFrame = new GUI("Inventory Viewer");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setListContent(itemsList.getAllItems()); // Populate the list with items
        mainFrame.setVisible(true);
    }

    // Method to load items from a CSV file
    private static ItemsList loadItemsList() {
        ItemsList itemsList = new ItemsList();
        String csvFilePath = "AuctionHouse\\src\\sample.csv"; 
        Loadfile.loadFurnitureFromFile(csvFilePath, itemsList);
        return itemsList;
    }
}
