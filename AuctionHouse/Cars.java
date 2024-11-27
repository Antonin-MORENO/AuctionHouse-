package AuctionHouse;

public class Cars extends Items {
    // atributes
    private String make;
    private String model;
    private boolean serviced;

    // constructor
    public Cars(String owner_name, String condition, YearEstimate year_of_origins, double starting_price, String make,
            String model, boolean serviced) {
        super(owner_name, condition, year_of_origins, starting_price);

        this.make = make;
        this.model = model;
        this.serviced = serviced;

    }

    // get methods
    public String get_make() {
        return make;
    }

    public String get_model() {
        return model;
    }

    public boolean get_serviced() {
        return serviced;
    }
}
