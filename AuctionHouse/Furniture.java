package AuctionHouse;

public class Furniture extends Items {
  // Attributes

  private String object_type;
  private String style;
  private String maker_name;
  private double lenght;
  private double height;
  private double depth;

  // constructor
  public Furniture(String object_type, String style, String maker_name, double lenght, double height, double depth,
      YearEstimate year_of_origins, String owner_name, String condition, double starting_price) {
    super(owner_name, condition, year_of_origins, starting_price);
    this.object_type = object_type;
    this.style = style;
    this.maker_name = maker_name;
    this.lenght = lenght;
    this.height = height;
    this.depth = depth;

  }

  // get method
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

}