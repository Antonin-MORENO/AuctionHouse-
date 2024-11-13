package AuctionHouse.src;

public class Main {
    public static void main(String[] args) {
        // Create an instance of furniturelist
        ItemsList list = new ItemsList();

        // Create furniture objects
        Furniture chair = new Furniture("Chair", "Modern", "John Smith", 1.2, 0.8, 0.6, 2020, "Alice", "Mint condition", 150.0);
        Furniture table = new Furniture("Table", "Victorian", "Emily Brown", 2.0, 1.0, 1.5, 1890, "Bob", "Restored", 1000.0);
        Furniture sofa = new Furniture("Sofa", "Contemporary", "Jane Doe", 3.0, 1.5, 2.0, 2005, "Charlie", "Mint condition", 600.0);
        Furniture bed = new Furniture("Bed", "Modern", "David Miller", 2.5, 1.2, 2.2, 1750, "David", "needs restoring", 2000.0);

        // Add furniture items to the list
        list.addFurniture(chair);
        list.addFurniture(table);
        list.addFurniture(sofa);
        list.addFurniture(bed);

        // Query about the list
        System.out.println("Total furniture items in the list: " + list.get_TotalItem());

        System.out.println("Oldest furniture item: " + list.getOldestItem().get_id() + " from " + list.getOldestItem().get_yearsoforigins());

        System.out.println("Newest furniture item: " + list.getNewestItem().get_id() + " from " + list.getNewestItem().get_yearsoforigins());

        System.out.println("Most expensive furniture item: " + list.getMostExpensiveItem().get_id() + " with starting price $" + list.getMostExpensiveItem().get_startingprice());

        System.out.println("Least expensive furniture item: " + list.getLeastExpensiveItem().get_id() + " with starting price $" + list.getLeastExpensiveItem().get_startingprice());
                
        ItemsList furnitureList = new ItemsList();
        
        String filepath = "C:\\Users\\anton\\OneDrive\\Documents\\heriotwatt\\software\\coursework\\AuctionHouse\\src\\furniture_data_missing_fields.csv";
        
        Loadfile.loadFurnitureFromFile(filepath, furnitureList);

        furnitureList.generate_stat_text_file("furniture_stats.txt");
    
    
    }
}
