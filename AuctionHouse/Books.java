package AuctionHouse;

public class Books extends Items {

    // Attributes
    private String title;
    private String authorname;
    private String edition;
    private String genre;

    // Constructor
    public Books(String title, String authorname, String edition, String genre, String owner_name, String condition,
            YearEstimate year_of_origins, double starting_price) {
        super(owner_name, condition, year_of_origins, starting_price);

        this.title = title;
        this.authorname = authorname;
        this.edition = edition;
        this.genre = genre;

    }

    // get methods
    public String get_title() {
        return title;
    }

    public String get_authorname() {
        return authorname;
    }

    public String get_edition() {
        return edition;
    }

    public String get_genre() {
        return genre;
    }
}
