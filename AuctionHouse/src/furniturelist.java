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

    
}

