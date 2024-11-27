package AuctionHouse;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class ItemsList {
    private ArrayList<Items> ItemsList;

    // constructor
    public ItemsList() {
        ItemsList = new ArrayList<>();
    }

    // add a item to the lost of item
    public void addFurniture(Items item) {
        ItemsList.add(item);
    }

    // get the total number of items in the list
    public int get_TotalItem() {
        return ItemsList.size();
    }

    // get the oldest items in the list
    public Items getOldestItem() {
        if (ItemsList.isEmpty())
            return null;

        Items oldest = ItemsList.get(0);
        for (Items item : ItemsList) {
            if (item.get_yearsoforigins().getLowEstimate() < oldest.get_yearsoforigins().getLowEstimate()) {
                oldest = item;
            }
        }
        return oldest;
    }

    // get the newest items in the list
    public Items getNewestItem() {
        if (ItemsList.isEmpty())
            return null;

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
        if (ItemsList.isEmpty())
            return null;

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
        if (ItemsList.isEmpty())
            return null;

        Items newest = ItemsList.get(0);
        for (Items item : ItemsList) {
            if (item.get_yearsoforigins().getMiddleEstimate() > newest.get_yearsoforigins().getMiddleEstimate()) {
                newest = item;
            }
        }
        return newest;
    }

    // get the most exepensive items in the list
    public Items getMostExpensiveItem() {
        if (ItemsList.isEmpty())
            return null;

        Items mostExpensive = ItemsList.get(0);
        for (Items item : ItemsList) {
            if (item.get_startingprice() > mostExpensive.get_startingprice()) {
                mostExpensive = item;
            }
        }
        return mostExpensive;
    }

    // get the least expensive items in the list
    public Items getLeastExpensiveItem() {
        if (ItemsList.isEmpty())
            return null;

        Items leastExpensive = ItemsList.get(0);
        for (Items item : ItemsList) {
            if (item.get_startingprice() < leastExpensive.get_startingprice()) {
                leastExpensive = item;
            }
        }
        return leastExpensive;
    }

    // calculate average starting price
    public double calculateAverageStartingPrice() {
        if (ItemsList.isEmpty())
            return 0.0;

        double sum = 0;
        for (Items item : ItemsList) {
            sum += item.get_startingprice();
        }
        return sum / ItemsList.size();
    }

    // calculate standard deviation
    public double calculateStandardDeviation() {
        if (ItemsList.isEmpty())
            return 0.0;

        double average = calculateAverageStartingPrice();
        double sumSquaredDifferences = 0.0;

        for (Items item : ItemsList) {
            double priceDifference = item.get_startingprice() - average;
            sumSquaredDifferences += priceDifference * priceDifference;
        }

        return Math.sqrt(sumSquaredDifferences / ItemsList.size());
    }

    // Get top 3 items by year estimate difference
    public ArrayList<Items> getTop3ItemsByEstimateDifference() {
        ArrayList<Items> sortedItems = new ArrayList<>(ItemsList);

        Collections.sort(sortedItems, new Comparator<Items>() {
            @Override
            public int compare(Items item1, Items item2) {
                double diff1 = item1.get_yearsoforigins().getHighEstimate()
                        - item1.get_yearsoforigins().getLowEstimate();
                double diff2 = item2.get_yearsoforigins().getHighEstimate()
                        - item2.get_yearsoforigins().getLowEstimate();
                return Double.compare(diff2, diff1);
            }
        });

        return new ArrayList<>(sortedItems.subList(0, Math.min(3, sortedItems.size())));
    }

    // ItemsList
    public ArrayList<Items> getAllItems() {
        return ItemsList;
    }

    // generate stat text file method
    public void generate_stat_text_file(String filename) {
        FileWriter fw;
        try {
            fw = new FileWriter(filename);
            fw.write("Items Inventory Statistics:\n");
            // Total number of items
            fw.write("Total items: " + get_TotalItem() + "\n");

            // Newest item
            Items newest = getNewestItem();
            fw.write("Newest item (High estimate) : " + newest.get_id() + " from "
                    + newest.get_yearsoforigins().getHighEstimate() + " id : " + newest.get_id() + "\n");

            // Oldest item
            Items oldest = getOldestItem();
            fw.write("Oldest item (Low estimate) : " + oldest.get_id() + " from "
                    + oldest.get_yearsoforigins().getLowEstimate() + " id : " + oldest.get_id() + "\n");

            // Newest item
            Items newestmiddle = getNewestItemMiddleEstimate();
            fw.write("Newest item (Middle estimate) : " + newest.get_id() + " from "
                    + newestmiddle.get_yearsoforigins().getMiddleEstimate() + " id : " + newestmiddle.get_id() + "\n");

            // Oldest item
            Items oldestmiddle = getOldestItemMiddleEstimate();
            fw.write("Oldest item (Middle estimate) : " + oldest.get_id() + " from "
                    + oldestmiddle.get_yearsoforigins().getMiddleEstimate() + " id : " + oldestmiddle.get_id() + "\n");

            // Most expensive
            Items mostexpensive = getMostExpensiveItem();
            fw.write("Most expensive item : " + mostexpensive.get_id() + " which costs "
                    + mostexpensive.get_startingprice() + "€, id : " + mostexpensive.get_id() + "\n");

            // Least expensive
            Items leastexpensive = getLeastExpensiveItem();
            fw.write("Least expensive item : " + leastexpensive.get_id() + " which costs "
                    + leastexpensive.get_startingprice() + "€, id : " + leastexpensive.get_id() + "\n");

            // Average starting price
            fw.write("Average starting price: " + calculateAverageStartingPrice() + "€\n");

            // Standard deviation of starting price
            fw.write("Standard deviation of starting price: " + calculateStandardDeviation() + "€\n");

            int mintConditionCount = 0;
            int restoredCount = 0;
            int needsRestoringCount = 0;

            for (Items item : ItemsList) {
                String condition = item.get_condition();

                if (condition.equals("Mint condition")) {
                    mintConditionCount++;
                } else if (condition.equals("Restored")) {
                    restoredCount++;
                } else if (condition.equals("Needs restoring")) {
                    needsRestoringCount++;
                }
            }

            fw.write("Breakdown of items by condition:\n");
            fw.write("Mint: " + mintConditionCount);
            fw.write("\nRestored: " + restoredCount);
            fw.write("\nNeeds Restoring: " + needsRestoringCount);

            fw.write("\nTop 3 items with the largest difference between high and low estimates:\n");
            ArrayList<Items> top3Items = getTop3ItemsByEstimateDifference();
            for (Items item : top3Items) {
                double difference = item.get_yearsoforigins().getHighEstimate()
                        - item.get_yearsoforigins().getLowEstimate();
                fw.write("Item ID: " + item.get_id() + ", Difference: " + difference + "\n");
            }

            fw.close();

        }
        // message and stop if file not found
        catch (FileNotFoundException fnf) {
            System.out.println(filename + " not found ");
            System.exit(0);

        }
        // stack trace here because we don't expect to come here
        catch (IOException ioe) {
            ioe.printStackTrace(); // goes to standard output
            System.exit(1);
        }
    }

}
