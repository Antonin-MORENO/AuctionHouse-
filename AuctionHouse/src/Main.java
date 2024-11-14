package AuctionHouse.src;

public class Main {
    public static void main(String[] args) {
        // Create an instance of furniturelist
        ItemsList list = new ItemsList();

        // Create furniture objects
        Coins coin = new Coins(
            "Gold", // material
            "Rome", // PlaceOfOrigins
            "10 aureus", // value
            "Henry Long", // owner_name
            "Mint condition", // condition
            new YearEstimate(500, 600), // year_of_origins (exemple avec une année estimée)
            200.0 // starting_price
        );

        // Création d'un objet Books
        Books book = new Books(
            "War and Peace", // title
            "Leo Tolstoy", // authorname
            "First Edition", // edition
            "Novel", // genre
            "David Gray", // owner_name
            "Mint condition", // condition
            new YearEstimate(1850, 1900), // year_of_origins
            200.0 // starting_price
        );

        // Création d'un objet Cars
        Cars car = new Cars(
            "Robert Brown", // owner_name
            "Mint condition", // condition
            new YearEstimate(1965, 1970), // year_of_origins
            15000.0, // starting_price
            "Ford", // make
            "Mustang", // model
            true // serviced
        );

        // Création d'un objet Furniture
        Furniture furniture = new Furniture(
            "Chair", // object_type
            "Victorian", // style
            "Thomas Chippendale", // maker_name
            45.0, // lenght
            80.0, // height
            50.0, // depth
            new YearEstimate(1800, 1850), // year_of_origins
            "John Doe", // owner_name
            "Mint condition", // condition
            1000.0 // starting_price
        );

        // Add furniture items to the list
        list.addFurniture(coin);
        list.addFurniture(furniture);
        list.addFurniture(car);
        list.addFurniture(book);

        // Query about the list
        System.out.println("Total furniture items in the list: " + list.get_TotalItem());

        System.out.println("Oldest furniture item: " + list.getOldestItem().get_id() + " from " + list.getOldestItem().get_yearsoforigins());

        System.out.println("Newest furniture item: " + list.getNewestItem().get_id() + " from " + list.getNewestItem().get_yearsoforigins());

        System.out.println("Most expensive furniture item: " + list.getMostExpensiveItem().get_id() + " with starting price $" + list.getMostExpensiveItem().get_startingprice());

        System.out.println("Least expensive furniture item: " + list.getLeastExpensiveItem().get_id() + " with starting price $" + list.getLeastExpensiveItem().get_startingprice());
                
        ItemsList furnitureList = new ItemsList();
        
        String filepath = "C:\\Users\\anton\\OneDrive\\Documents\\heriotwatt\\software\\coursework\\AuctionHouse\\src\\sample.csv";
        
        Loadfile.loadFurnitureFromFile(filepath, furnitureList);

        furnitureList.generate_stat_text_file("furniture_stats.txt");
    
    
    }
}
