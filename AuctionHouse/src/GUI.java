package AuctionHouse.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GUI extends JFrame {
    private DefaultListModel<String> inventoryModel;
    private JList<String> inventoryList;
    private JButton moreInfoButton;
    private JButton editButton;
    private JButton generateStatsButton;
    private ItemsList itemsList; 

    public GUI(String title, ItemsList itemsList) {
        super(title);
        this.itemsList = itemsList; 
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Initialize list model and JList
        inventoryModel = new DefaultListModel<>();
        inventoryList = new JList<>(inventoryModel);
        JScrollPane listScrollPane = new JScrollPane(inventoryList);

        // Add components to the frame
        add(new JLabel("Inventory List:"), BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);

        // Add Buttons
        moreInfoButton = new JButton("More Info");
        editButton =  new JButton("Edit");
        generateStatsButton = new JButton("Generate Statistics");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateStatsButton);
        buttonPanel.add(moreInfoButton);
        buttonPanel.add(editButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to buttons
        moreInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMoreInfo();
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSelectedItem();
            }
        });
        
        createMenuBar();


    }



        // Create a menu bar for sorting options
        private void createMenuBar() {
            JMenuBar menuBar = new JMenuBar();
            JMenu sortMenu = new JMenu("Sort");
    
            // Sort by Price
            JMenuItem sortByPrice = new JMenuItem("By Price");
            sortByPrice.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sortItemsByPrice();
                }
            });
    
            // Sort by Year Estimate
            JMenuItem sortByYear = new JMenuItem("By Year Estimate");
            sortByYear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sortItemsByYear();
                }
            });
    
            sortMenu.add(sortByPrice);
            sortMenu.add(sortByYear);
            menuBar.add(sortMenu);
    
            setJMenuBar(menuBar);
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

    // Method to show more details about the selected item
    private void showMoreInfo() {
        int selectedIndex = inventoryList.getSelectedIndex();
        if (selectedIndex >= 0) {
            // Retrieve the selected item from itemsList
            Items selectedItem = itemsList.getAllItems().get(selectedIndex);

            // Display the details in a dialog
            String details = String.format(
                    "Type: %s\nID: %d\nPrice: %.2f€\nOwner: %s\nCondition: %s\nYear Estimate: %d-%d",
                    selectedItem.getClass().getSimpleName(),
                    selectedItem.get_id(),
                    selectedItem.get_startingprice(),
                    selectedItem.get_ownername(),
                    selectedItem.get_condition(),
                    selectedItem.get_yearsoforigins().getLowEstimate(),
                    selectedItem.get_yearsoforigins().getHighEstimate()
            );

            JOptionPane.showMessageDialog(this, details, "Item Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item first.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }


    // Method to edit the selected item's price and condition
    private void editSelectedItem() {
        int selectedIndex = inventoryList.getSelectedIndex();
        if (selectedIndex >= 0) {
            // Retrieve the selected item
            Items selectedItem = itemsList.getAllItems().get(selectedIndex);

            // Prompt for new price
            String newPriceStr = JOptionPane.showInputDialog(this, "Enter new price:", selectedItem.get_startingprice());
            if (newPriceStr == null) return; // Cancel if input is null

            // Validate and update price
            try {
                double newPrice = Double.parseDouble(newPriceStr);
                selectedItem.set_price(newPrice);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Prompt for new condition from predefined choices
            String[] validConditions = {"Mint condition", "Restored", "Needs restoring"};
            String newCondition = (String) JOptionPane.showInputDialog(
                    this,
                    "Select new condition:",
                    "Edit Condition",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    validConditions,
                    selectedItem.get_condition()
            );

            // Check if user canceled
            if (newCondition != null) {
                selectedItem.set_condition(newCondition);

                // Refresh the list content
                setListContent(itemsList.getAllItems());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item first.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }


    // Sorting methods
    private void sortItemsByPrice() {
        Collections.sort(itemsList.getAllItems(), new Comparator<Items>() {
            @Override
            public int compare(Items o1, Items o2) {
                return Double.compare(o1.get_startingprice(), o2.get_startingprice());
            }
        });
        setListContent(itemsList.getAllItems());
    }

    private void sortItemsByYear() {
        Collections.sort(itemsList.getAllItems(), new Comparator<Items>() {
            @Override
            public int compare(Items o1, Items o2) {
                return Integer.compare(o1.get_yearsoforigins().getLowEstimate(), o2.get_yearsoforigins().getLowEstimate());
            }
        });
        setListContent(itemsList.getAllItems());
    }

    public static void main(String[] args) {
        // Create and populate ItemsList
        ItemsList itemsList = loadItemsList();

        // Create and configure the main frame
        GUI mainFrame = new GUI("Inventory Viewer", itemsList);
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
