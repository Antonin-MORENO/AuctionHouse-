package AuctionHouse;

public class YearEstimate {
    // atributes
    private int lowEstimate;
    private int highEstimate;

    // constructor
    public YearEstimate(int lowEstimate, int highEstimate) {
        this.lowEstimate = lowEstimate;
        this.highEstimate = highEstimate;
    }

    // get method
    public int getLowEstimate() {
        return lowEstimate;
    }

    public int getHighEstimate() {
        return highEstimate;
    }

    public int getMiddleEstimate() {
        return Math.round((lowEstimate + highEstimate) / 2.0f);
    }

    public int getDifference() {
        return highEstimate - lowEstimate;
    }
}
