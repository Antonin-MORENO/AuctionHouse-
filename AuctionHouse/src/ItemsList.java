package AuctionHouse.src;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ItemsList {
    private ArrayList <Items> ItemsList;

    public ItemsList(){
        ItemsList = new ArrayList<>();
    }

    // add a furniture to the lost of furniture
    public void addFurniture(Items item) {
        ItemsList.add(item);
    }

    // get the total number of furniture in the list
    public int get_TotalItem() {
        return ItemsList.size();
    }



    // get the oldest furniture in the list
    public Items getOldestItem() {
        if (ItemsList.isEmpty()) return null;
    
        Items oldest = ItemsList.get(0);
        for (Items item : ItemsList) {
            if (item.get_yearsoforigins().getLowEstimate() < oldest.get_yearsoforigins().getLowEstimate()) {
                oldest = item;
            }
        }
        return oldest;
    }
    

    // get the newest furniture in the list 
    public Items getNewestItem() {
        if (ItemsList.isEmpty()) return null;
    
        Items newest = ItemsList.get(0);
        for (Items item : ItemsList) {
            if (item.get_yearsoforigins().getHighEstimate() > newest.get_yearsoforigins().getHighEstimate()) {
                newest = item;
            }
        }
        return newest;
    }



    // Get the oldest item by middle estimate
public Items getOldestItemMiddleEstimate() {
    if (ItemsList.isEmpty()) return null;

    Items oldest = ItemsList.get(0);
    for (Items item : ItemsList) {
        if (item.get_yearsoforigins().getMiddleEstimate() < oldest.get_yearsoforigins().getMiddleEstimate()) {
            oldest = item;
        }
    }
    return oldest;
}

// Get the newest item by middle estimate
public Items getNewestItemMiddleEstimate() {
    if (ItemsList.isEmpty()) return null;

    Items newest = ItemsList.get(0);
    for (Items item : ItemsList) {
        if (item.get_yearsoforigins().getMiddleEstimate() > newest.get_yearsoforigins().getMiddleEstimate()) {
            newest = item;
        }
    }
    return newest;
}

    // get the most exepensive furniture in the list
    public Items getMostExpensiveItem() {
        if (ItemsList.isEmpty()) return null;

        Items mostExpensive = ItemsList.get(0);
        for (Items item : ItemsList) {
            if (item.get_startingprice() > mostExpensive.get_startingprice()) {
                mostExpensive = item;
            }
        }
        return mostExpensive;
    }

    // get the least expensive furniture in the list  
    public Items getLeastExpensiveItem() {
        if (ItemsList.isEmpty()) return null;

        Items leastExpensive = ItemsList.get(0);
        for (Items item : ItemsList) {
            if (item.get_startingprice() < leastExpensive.get_startingprice()) {
                leastExpensive = item;
            }
        }
        return leastExpensive;
    }


    public double calculateAverageStartingPrice() {
        if (ItemsList.isEmpty()) return 0.0;

        double sum = 0;
        for (Items item : ItemsList) {
            sum += item.get_startingprice();
        }
        return sum / ItemsList.size();
    }



    public double calculateStandardDeviation() {
        if (ItemsList.isEmpty()) return 0.0;

        double average = calculateAverageStartingPrice();
        double sumSquaredDifferences = 0.0;

        for (Items item : ItemsList) {
            double priceDifference = item.get_startingprice() - average;
            sumSquaredDifferences += priceDifference * priceDifference;
        }

        return Math.sqrt(sumSquaredDifferences / ItemsList.size());
    }
    
    


    public void generate_stat_text_file(String filename) {
        FileWriter fw;
        try {       
            fw = new FileWriter(filename);
            fw.write("Items Inventory Statistics:\n");
            // Total number of items
            fw.write("Total items: " + get_TotalItem() + "\n");

            // Newest item
            Items newest = getNewestItem();
            fw.write("Newest item (High estimate) : " + newest.get_id() + " from " + newest.get_yearsoforigins().getHighEstimate() + " id : " + newest.get_id()+ "\n");
            
            // Oldest item
            Items oldest = getOldestItem();
            fw.write("Oldest item (Low estimate) : " + oldest.get_id() + " from " + oldest.get_yearsoforigins().getLowEstimate() + " id : " + oldest.get_id()+ "\n");


            // Newest item
            Items newestmiddle = getNewestItemMiddleEstimate();
            fw.write("Newest item (High estimate) : " + newest.get_id() + " from " + newestmiddle.get_yearsoforigins().getMiddleEstimate() + " id : " + newestmiddle.get_id()+ "\n");
            
            // Oldest item
            Items oldestmiddle = getOldestItemMiddleEstimate();
            fw.write("Oldest item (Low estimate) : " + oldest.get_id() + " from " + oldestmiddle.get_yearsoforigins().getMiddleEstimate() + " id : " + oldestmiddle.get_id()+ "\n");


            // Most expensive
            Items mostexpensive = getMostExpensiveItem();
            fw.write("Most expensive furniture : " + mostexpensive.get_id() + " which costs " + mostexpensive.get_startingprice() + "€, id : " + mostexpensive.get_id()+ "\n");

            // Least expensive
            Items leastexpensive = getLeastExpensiveItem();
            fw.write("Least expensive furniture : " + leastexpensive.get_id() + " which costs " + leastexpensive.get_startingprice() + "€, id : " + leastexpensive.get_id()+ "\n");

            // Average starting price
            fw.write("Average starting price: " + calculateAverageStartingPrice() + "€\n");


            // Standard deviation of starting price
            fw.write("Standard deviation of starting price: " + calculateStandardDeviation() + "€\n");

            int mintConditionCount = 0;
            int restoredCount = 0;
            int needsRestoringCount = 0;
            int goodConditionCount = 0;

            for (Items item : ItemsList) {
                String condition = item.get_condition();
                
                if (condition.equals("Mint condition")) {
                    mintConditionCount++;
                } else if (condition.equals("Restored")) {
                    restoredCount++;
                } else if (condition.equals("Needs restoring")) {
                    needsRestoringCount++;
                } else if (condition.equals("Good condition")) {
                    goodConditionCount++;
                }
            }

            
            fw.write("Breakdown of Furniture items by condition:\n");
            fw.write("Mint: " + mintConditionCount);
            fw.write("\nRestored: " + restoredCount);
            fw.write("\nNeeds Restoring: " + needsRestoringCount);
            fw.write("\nGoods condition : " + goodConditionCount);


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
