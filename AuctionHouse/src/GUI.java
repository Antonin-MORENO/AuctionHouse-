package AuctionHouse.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class GUI extends JFrame {
    private DefaultListModel<String> inventoryModel;
    private JList<String> inventoryList;
    private JButton moreInfoButton;
    private JButton editButton;
    private JButton generateStatsButton;
    private JButton saveDataButton;
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
        saveDataButton = new JButton("Save data");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveDataButton);
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

        generateStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateStatistics();
            }
        });


        saveDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
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
                    sortItems(SortingCrit.Price);
                }
            });
    
            // Sort by Year Estimate
            JMenuItem sortByYear = new JMenuItem("By Year Estimate");
            sortByYear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sortItems(SortingCrit.Year);
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
                    "Type: %s\nID: %d\nPrice: %.2f€\nOwner: %s\nCondition: %s\nYear Estimate: %d-%d\n",
                    selectedItem.getClass().getSimpleName(),
                    selectedItem.get_id(),
                    selectedItem.get_startingprice(),
                    selectedItem.get_ownername(),
                    selectedItem.get_condition(),
                    selectedItem.get_yearsoforigins().getLowEstimate(),
                    selectedItem.get_yearsoforigins().getHighEstimate()
            );

            if (selectedItem instanceof Coins) {
                Coins coin = (Coins) selectedItem;
                details += String.format(
                        "Material: %s\nOrigin: %s\nValue: %s\n",
                        coin.get_material(),
                        coin.get_PlaceOfOrigins(),
                        coin.get_value()
                );
            } else if (selectedItem instanceof Furniture) {
                Furniture furniture = (Furniture) selectedItem;
                details += String.format(
                        "Type: %s\nStyle: %s\nMaker name: %s\nlength: %.2f\nHeight: %.2f\nDepth: %.2f\n",
                        furniture.get_type(),
                        furniture.get_style(),
                        furniture.get_makername(),
                        furniture.get_length(),
                        furniture.get_height(),
                        furniture.get_depth()
                );
            } else if (selectedItem instanceof Cars) {
                Cars car = (Cars) selectedItem;
                details += String.format(
                        "Make: %s\nModel: %s\nServiced: %b\n",
                        car.get_make(),
                        car.get_model(),
                        car.get_serviced()
                );
            } else if (selectedItem instanceof Books) {
                Books book = (Books) selectedItem;
                details += String.format(
                        "Title: %s\nAuthor: %s\nEdition: %s\nPages: %d\n",
                        book.get_title(),
                        book.get_authorname(),
                        book.get_edition(),
                        book.get_genre()
                );
            }

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


    // Sorting method
    private void sortItems(SortingCrit criteria) {
        itemsList.getAllItems().sort(new ItemsComparator(criteria));
        setListContent(itemsList.getAllItems());
        
    }
    

    private void generateStatistics() {
        String filename = "Statistics.txt";
        itemsList.generate_stat_text_file(filename);
        JOptionPane.showMessageDialog(this, "Statistics saved to " + filename, "Statistics Generated", JOptionPane.INFORMATION_MESSAGE);
    }


    private void saveData() {
    String fileName = "updated_inventory.csv";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        for (Items item : itemsList.getAllItems()) {
            
            if (item instanceof Coins) {
                Coins coin = (Coins) item;
                String line = String.format(Locale.US,"Coins,%s,%d,%d,%s,%.2f,%s,%s,%s\n",
                        coin.get_condition(),
                        coin.get_yearsoforigins().getLowEstimate(),
                        coin.get_yearsoforigins().getHighEstimate(),
                        coin.get_ownername(),
                        coin.get_startingprice(),
                        coin.get_material(),
                        coin.get_PlaceOfOrigins(),
                        coin.get_value());
                writer.write(line);
            } else if (item instanceof Furniture) {
                Furniture furniture = (Furniture) item;
                String line = String.format(Locale.US,"Furniture,%s,%d,%d,%s,%.2f,%s,%s,%.2f,%.2f,%.2f\n",
                        furniture.get_condition(),
                        furniture.get_yearsoforigins().getLowEstimate(),
                        furniture.get_yearsoforigins().getHighEstimate(),
                        furniture.get_ownername(),
                        furniture.get_startingprice(),
                        furniture.get_type(),
                        furniture.get_style(),
                        furniture.get_length(),
                        furniture.get_height(),
                        furniture.get_depth());
                writer.write(line);
            } else if (item instanceof Cars) {
                Cars car = (Cars) item;
                String line = String.format(Locale.US,"Cars,%s,%d,%d,%s,%.2f,%s,%s,%b\n",
                        car.get_condition(),
                        car.get_yearsoforigins().getLowEstimate(),
                        car.get_yearsoforigins().getHighEstimate(),
                        car.get_ownername(),
                        car.get_startingprice(),
                        car.get_make(),
                        car.get_model(),
                        car.get_serviced());
                writer.write(line);
            } else if (item instanceof Books) {
                Books book = (Books) item;
                String line = String.format(Locale.US,"Books,%s,%d,%d,%s,%.2f,%s,%s,%s,%s\n",
                        book.get_condition(),
                        book.get_yearsoforigins().getLowEstimate(),
                        book.get_yearsoforigins().getHighEstimate(),
                        book.get_ownername(),
                        book.get_startingprice(),
                        book.get_title(),
                        book.get_authorname(),
                        book.get_edition(),
                        book.get_genre());
                writer.write(line);
            }
        }
        JOptionPane.showMessageDialog(this, "Data saved to " + fileName, "Save Successful", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
    }
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
