package AuctionHouse.src;


public abstract class Items {
    // Atributes 

    protected static int idcounter = 0; 
    protected int id; 

    protected int year_of_origins;
    protected String owner_name;
    protected String condition;
    protected double starting_price;


    // Constructor 

    public Items(String owner_name, String condition, int year_of_origins, double starting_price){

        this.id = ++idcounter;
        this.year_of_origins = year_of_origins;
        this.owner_name = owner_name;
        this.condition = condition;
        this.starting_price = starting_price;
    }

    public int get_id(){
        return id;
    }
    
    public int get_yearsoforigins() {
        return year_of_origins;
    }

    public String get_ownername() {
        return owner_name;
    }

    public String get_condition() {
        return condition;
    }

    public double get_startingprice() {
        return starting_price;
    }
    
}
