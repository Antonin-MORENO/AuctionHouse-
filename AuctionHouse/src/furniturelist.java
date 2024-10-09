package AuctionHouse.src;

import java.util.ArrayList;

public class furniturelist {
    private ArrayList <furniture> FurnitureList;

    public furniturelist(){
        FurnitureList = new ArrayList<>();
    }

    // add a furniture to the lost of furniture
    public void addFurniture(furniture item) {
        FurnitureList.add(item);
    }

    // get the total number of furniture in the list
    public int get_TotalItem() {
        return FurnitureList.size();
    }

    // get the oldest furniture in the list
    public furniture getOldestItem() {
        if (FurnitureList.isEmpty()) return null;

        furniture oldest = FurnitureList.get(0);
        for (furniture item : FurnitureList) {
            if (item.get_yearsoforigins() < oldest.get_yearsoforigins()) {
                oldest = item;
            }
        }
        return oldest;
    }

    // get the newest furniture in the list 
    public furniture getNewestItem() {
        if (FurnitureList.isEmpty()) return null;

        furniture newest = FurnitureList.get(0);
        for (furniture item : FurnitureList) {
            if (item.get_yearsoforigins() > newest.get_yearsoforigins()) {
                newest = item;
            }
        }
        return newest;
    }

    // get the most exepensive furniture in the list
    public furniture getMostExpensiveItem() {
        if (FurnitureList.isEmpty()) return null;

        furniture mostExpensive = FurnitureList.get(0);
        for (furniture item : FurnitureList) {
            if (item.get_startingprice() > mostExpensive.get_startingprice()) {
                mostExpensive = item;
            }
        }
        return mostExpensive;
    }

    // get the least expensive furniture in the list  
    public furniture getLeastExpensiveItem() {
        if (FurnitureList.isEmpty()) return null;

        furniture leastExpensive = FurnitureList.get(0);
        for (furniture item : FurnitureList) {
            if (item.get_startingprice() < leastExpensive.get_startingprice()) {
                leastExpensive = item;
            }
        }
        return leastExpensive;
    }


}

