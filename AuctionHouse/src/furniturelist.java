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


    public double calculateAverageStartingPrice() {
        if (FurnitureList.isEmpty()) return 0.0;

        double sum = 0;
        for (Furniture item : FurnitureList) {
            sum += item.get_startingprice();
        }
        return sum / FurnitureList.size();
    }



    public double calculateStandardDeviation() {
        if (FurnitureList.isEmpty()) return 0.0;

        double average = calculateAverageStartingPrice();
        double sumSquaredDifferences = 0.0;

        for (Furniture item : FurnitureList) {
            double priceDifference = item.get_startingprice() - average;
            sumSquaredDifferences += priceDifference * priceDifference;
        }

        return Math.sqrt(sumSquaredDifferences / FurnitureList.size());
    }
    
    


    public void generate_stat_text_file(String filename) {
        FileWriter fw;
        try {       
            fw = new FileWriter(filename);
            fw.write("Furniture Inventory Statistics:\n");
            // Total number of items
            fw.write("Total items: " + get_TotalItem() + "\n");

            // Newest item
            Furniture newest = getNewestItem();
            fw.write("Newest item : " + newest.get_type() + " from " + newest.get_yearsoforigins() + " id : " + newest.get_id()+ "\n");
            
            // Oldest item
            Furniture oldest = getOldestItem();
            fw.write("Oldest item : " + oldest.get_type() + " from " + oldest.get_yearsoforigins() + " id : " + oldest.get_id()+ "\n");

            // Most expensive
            Furniture mostexpensive = getMostExpensiveItem();
            fw.write("Most expensive furniture : " + mostexpensive.get_type() + " which costs " + mostexpensive.get_startingprice() + "€, id : " + mostexpensive.get_id()+ "\n");

            // Least expensive
            Furniture leastexpensive = getLeastExpensiveItem();
            fw.write("Least expensive furniture : " + leastexpensive.get_type() + " which costs " + leastexpensive.get_startingprice() + "€, id : " + leastexpensive.get_id()+ "\n");

            // Average starting price
            fw.write("Average starting price: " + calculateAverageStartingPrice() + "€\n");


            // Standard deviation of starting price
            fw.write("Standard deviation of starting price: " + calculateStandardDeviation() + "€\n");

            int mintConditionCount = 0;
            int restoredCount = 0;
            int needsRestoringCount = 0;
            int goodConditionCount = 0;

            for (Furniture item : FurnitureList) {
                String condition = item.get_condition();
                
                if (condition.equals("Mint condition")) { // Correction de "condtion" à "condition"
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
