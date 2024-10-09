package AuctionHouse.src;

public class furniture {
     // Instance variable

  private static int idcounter = 0; 
  private int id; 

  private String object_type;
  private String style;
  private String maker_name;
  private double lenght;
  private double height;
  private double depth;
  private int year_of_origins;
  private String owner_name;
  private String condition;
  private double starting_price;


  public furniture(String object_type, String style, String maker_name, double lenght, double height, double depth, int year_of_origins, String owner_name, String condition, double starting_price) {
    
    this.id = ++idcounter;
    this.object_type = object_type;
    this.style = style;
    this.maker_name = maker_name;
    this.lenght = lenght;
    this.height = height;
    this.depth = depth;
    this.year_of_origins = year_of_origins;
    this.owner_name = owner_name;
    this.condition = condition;
    this.starting_price = starting_price;
  }

  public int get_id(){
    return id;
  }

  public String get_type() {
    return object_type;
  }

  public String get_style() {
    return style;
  }

  public String get_makername() {
    return maker_name;
  }

  public double get_length() {
    return lenght;
  }

  public double get_height() {
    return height;
  }

  public double get_depth() {
    return depth;
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