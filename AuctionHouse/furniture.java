package AuctionHouse;

public class furniture {
    // Instance variable

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


public furniture(String object_type, String style, String maker_name, double lenght, double height, double depth,
 int year_of_origins, String owner_name, String condition, double starting_price) {
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
  
}