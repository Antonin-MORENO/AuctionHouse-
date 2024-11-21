package AuctionHouse.src;


public abstract class Items {
    // Atributes 

    protected static int idcounter = 0; 
    protected int id; 

    protected YearEstimate yearEstimate;
    protected String owner_name;
    protected String condition;
    protected double starting_price;


    // Constructor 

    public Items(String owner_name, String condition, YearEstimate yearEstimate, double starting_price){

        this.id = ++idcounter;
        this.yearEstimate = yearEstimate;
        this.owner_name = owner_name;
        this.condition = condition;
        this.starting_price = starting_price;
    }

    public int get_id(){
        return id;
    }
    
    public YearEstimate get_yearsoforigins() {
        return yearEstimate;
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

    public void set_price(double price) {
        this.starting_price = price;
    }

    public void set_condition(String condition) {
        this.condition = condition;
    }
    
}
