package Main;

import javax.swing.WindowConstants;

import AuctionHouse.*;
import GUI.GUI;

public class Main {
        public static void main(String[] args) {
        // Create and populate ItemsList
        ItemsList itemsList = GUI.loadItemsList();

        // Create and configure the main frame
        GUI mainFrame = new GUI("Inventory Viewer", itemsList);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setListContent(itemsList.getAllItems()); // Populate the list with items
        mainFrame.setVisible(true);
    }
    
}
