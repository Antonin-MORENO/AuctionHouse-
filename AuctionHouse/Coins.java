package AuctionHouse;

public class Coins extends Items {

    // Atributes 

    private String material;
    private String PlaceOfOrigins;
    private String value;

    public Coins(String material, String PlaceOfOrigins, String value, String owner_name, String condition, YearEstimate year_of_origins, double starting_price){
        super(owner_name, condition, year_of_origins, starting_price);

        this.material = material;
        this.PlaceOfOrigins = PlaceOfOrigins;
        this.value = value;
    }

    public String get_material(){
        return material;
    }

    public String get_PlaceOfOrigins(){
        return PlaceOfOrigins;
    }

    public String get_value(){
        return value;
    }




    
}
