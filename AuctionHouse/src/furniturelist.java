package AuctionHouse.src;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Furniturelist {
    private ArrayList <Furniture> FurnitureList;

    public Furniturelist(){
        FurnitureList = new ArrayList<>();
    }

    // add a furniture to the lost of furniture
    public void addFurniture(Furniture item) {
        FurnitureList.add(item);
    }

    // get the total number of furniture in the list
    public int get_TotalItem() {
        return FurnitureList.size();
    }

    // get the oldest furniture in the list
    public Furniture getOldestItem() {
        if (FurnitureList.isEmpty()) return null;

        Furniture oldest = FurnitureList.get(0);
        for (Furniture item : FurnitureList) {
            if (item.get_yearsoforigins() < oldest.get_yearsoforigins()) {
                oldest = item;
            }
        }
        return oldest;
    }

    // get the newest furniture in the list 
    public Furniture getNewestItem() {
        if (FurnitureList.isEmpty()) return null;

        Furniture newest = FurnitureList.get(0);
        for (Furniture item : FurnitureList) {
            if (item.get_yearsoforigins() > newest.get_yearsoforigins()) {
                newest = item;
            }
        }
        return newest;
    }

    // get the most exepensive furniture in the list
    public Furniture getMostExpensiveItem() {
        if (FurnitureList.isEmpty()) return null;

        Furniture mostExpensive = FurnitureList.get(0);
        for (Furniture item : FurnitureList) {
            if (item.get_startingprice() > mostExpensive.get_startingprice()) {
                mostExpensive = item;
            }
        }
        return mostExpensive;
    }

    // get the least expensive furniture in the list  
    public Furniture getLeastExpensiveItem() {
        if (FurnitureList.isEmpty()) return null;

        Furniture leastExpensive = FurnitureList.get(0);
        for (Furniture item : FurnitureList) {
            if (item.get_startingprice() < leastExpensive.get_startingprice()) {
                leastExpensive = item;
            }
        }
        return leastExpensive;
    }


    public void generate_stat_text_file(String filename) {
        FileWriter fw;
        try {       
            fw = new FileWriter(filename);
            fw.write("Furniture Inventory Statistics:\n");
            // Total number of items
            fw.write("Total items: " + get_TotalItem() + "\n");
    
            // Oldest 
            fw.close();


        }
        //message and stop if file not found
        catch (FileNotFoundException fnf){
            System.out.println(filename + " not found ");
            System.exit(0);
    
        }
        //stack trace here because we don't expect to come here
        catch (IOException ioe){
            ioe.printStackTrace(); //goes to standard output
            System.exit(1);
        }
    }

}
