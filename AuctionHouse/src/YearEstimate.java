package AuctionHouse.src;

public class YearEstimate {
    private int lowEstimate;
    private int highEstimate;

    public YearEstimate(int lowEstimate, int highEstimate) {
        this.lowEstimate = lowEstimate;
        this.highEstimate = highEstimate;
    }

    public int getLowEstimate() {
        return lowEstimate;
    }

    public int getHighEstimate() {
        return highEstimate;
    }

    public int getMiddleEstimate() {
        return (lowEstimate + highEstimate) / 2;
    }

    public int getDifference() {
        return highEstimate - lowEstimate;
    }
}
